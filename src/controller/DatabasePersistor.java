package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                //Give me the data in column 'name' at the row
                //at which the ResultSet is currently pointing at.
                String currentName = rs.getString("Name");
                String currCountry = rs.getString("Country");

                //Re-create a TwitterUser object and initialize it
                //with the raw data we have just extracted from
                //the row in the database.
                TwitterUser t =
                        new TwitterUser(currentName, currCountry);
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



}