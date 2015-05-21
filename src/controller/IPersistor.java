package controller;

import model.Tweet;
import model.TwitterUser;

import java.util.ArrayList;

/**
 * Created by Jamie on 20/05/15.
 */
public interface IPersistor {
    public void write(ArrayList<TwitterUser> dataModel);
    public ArrayList<TwitterUser> read();
    public void addTweetToUser(String name, Tweet tweet);
    public void deleteUser(String name);
}
