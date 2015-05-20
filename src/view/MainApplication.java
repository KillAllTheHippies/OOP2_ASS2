package view;

import controller.DatabasePersistor;
import controller.IPersistor;
import controller.TwitterController;
import model.TwitterUser;

import java.util.ArrayList;

/**
 * Created by Jamie on 27/04/15.
 */
public class MainApplication {

    public static void main(String[] args) {

        // Create the persistor
        IPersistor persistor = new DatabasePersistor();
        TwitterController.getInstance().setPersistor(persistor);

        // Create the model
        ArrayList<TwitterUser> dataModel = persistor.read();

        // Connect the controller to the model
        TwitterController.getInstance().setDataModel(dataModel);

        // Create an instance of our main application frame which builds the UI
        TwitterFrame tf = new TwitterFrame("Jamie's OOP2 Assignment 2");
        tf.setSize(700, 300);
        tf.setVisible(true);

        // Connect the controller to the view
        TwitterController.getInstance().setGuiReference(tf);
    }
}
