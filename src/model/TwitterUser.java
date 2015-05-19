package model;

import model.Tweet;

import java.util.ArrayList;

/**
 * Created by Jamie on 26/04/15.
 */
public class TwitterUser {
    private String userName;
    private String country;
    private ArrayList<Tweet> tweets;

    public TwitterUser(String userName, String country ) {
        this.userName = userName;
        this.country = country;
        this.tweets = new ArrayList<Tweet>();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }
}
