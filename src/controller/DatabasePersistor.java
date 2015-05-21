package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Tweet;
import model.TwitterUser;

public class DatabasePersistor implements IPersistor {

    private Connection dbConnection;

    public DatabasePersistor()
    {
        try
        {
            //Start the driver for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/twitterschema?"+"user=root&password=");
            System.out.println("Database Connection : "+dbConnection);
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println(cnf.getMessage());
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
    }

    @Override
    public void write(ArrayList<TwitterUser> users)
    {
        for(TwitterUser currUser : users)
        {
            try
            {
                PreparedStatement prepStmt =
                        dbConnection.prepareStatement("INSERT into users values (?, ?)");

                prepStmt.setString(1, currUser.getUserName());
                prepStmt.setString(2, currUser.getCountry());


                prepStmt.executeUpdate();
                prepStmt.close();
            }
            catch(SQLException sqlEx)
            {
                System.out.println(sqlEx.getMessage());
            }
        }

    }



    @Override
    public ArrayList<TwitterUser> read() {
        //Create an empty list
        ArrayList<TwitterUser> usersList = new ArrayList<TwitterUser>();
        try
        {
            Statement getAllUsersStmt = dbConnection.createStatement();

            ResultSet rs =
                    getAllUsersStmt.executeQuery("SELECT * from USERS");

            while(rs.next())
            {
                // Give me the data in column 'name' at the row
                // at which the ResultSet is currently pointing at.
                String currentName = rs.getString("Name");
                String currCountry = rs.getString("Country");

                // Re-create a TwitterUser object and initialize it
                // with the raw data we have just extracted from
                // the row in the database.
                TwitterUser t =
                        new TwitterUser(currentName, currCountry);

                // Populate the user with their tweets
                t.setTweets(getUserTweets(currentName));
                // Add the user to the arraylist which will be returned by this method
                usersList.add(t);
            }
            getAllUsersStmt.close();
            rs.close();

        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }

        return usersList;
    }

    public ArrayList<Tweet> getUserTweets(String userName)
    {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try
        {
            PreparedStatement getUsersTweets =
                    dbConnection.prepareStatement("SELECT * FROM TWEETS WHERE name=?");
            getUsersTweets.setString(1, userName);
            ResultSet tweetSet = getUsersTweets.executeQuery();
            //Process the ResultSet
            while(tweetSet.next())
            {
                String strTweet = tweetSet.getString("Tweet");
                String date = tweetSet.getString("DateTime");
                Calendar cal = 	Calendar.getInstance();
                cal.setTimeInMillis(Long.parseLong(date));

                Tweet tweet = new Tweet(strTweet, cal.getTime());
                tweets.add(tweet);
            }
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        return tweets;

    }

    public void addTweetToUser(String name, Tweet tweet) {
        try
        {
            PreparedStatement insertTweetRow =
                    dbConnection.prepareStatement("INSERT into TWEETS values (?,?,?)");
            insertTweetRow.setString(1, name);
            insertTweetRow.setString(2, tweet.getTweetText());

            Date tweetDate = tweet.getDateTimeSent();
            long timeInMillis = tweetDate.getTime();

            insertTweetRow.setString(3, Long.toString(timeInMillis));

            insertTweetRow.executeUpdate();
            insertTweetRow.close();
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
    }
    public void deleteUser(String name) {
        try
        {
            // Delete the user from the database where name matches.
            PreparedStatement deleteUserRow =
                    dbConnection.prepareStatement("DELETE from USERS WHERE Name='" + name + "'");
            deleteUserRow.executeUpdate();
            deleteUserRow.close();
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        // Delete the tweets from the database that match the name
        try
        {
            // Delete the user from the database where name matches.
            PreparedStatement deleteTweets =
                    dbConnection.prepareStatement("DELETE from TWEETS WHERE Name='" + name + "'");
            deleteTweets.executeUpdate();
            deleteTweets.close();
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }

    }

}