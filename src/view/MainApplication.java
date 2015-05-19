package view;

import model.TwitterUser;

/**
 * Created by Jamie on 27/04/15.
 */
public class MainApplication {

    public static void main(String[] args) {

        // Create an instance of our main application frame which builds the UI
        TwitterFrame tf = new TwitterFrame("Jamie's OOP2 Assignment 2");
        tf.setSize(700, 300);
        tf.setVisible(true);

    }
}
