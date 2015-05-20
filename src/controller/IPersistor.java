package controller;

import model.TwitterUser;

import java.util.ArrayList;

/**
 * Created by Jamie on 20/05/15.
 */
public interface IPersistor {
    public void write(ArrayList<TwitterUser> dataModel);
    public ArrayList<TwitterUser> read();
}