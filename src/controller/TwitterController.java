package controller;


import model.Tweet;
import model.TwitterUser;

import java.util.ArrayList;

public class TwitterController {
    //THIS IS THE STATIC PART
    //This is the static variable which will point at the
    //instance of TwitterController once created.
    private static TwitterController instance = null;

    //This is the static method which "manages" the static
    //instance. A static method is required to access a static
    //variable.
    //If the instance is not created it will be created. If it is
    //already created then we don't need to create another instance.
    //Either way the one and only instance gets returned.
    public static TwitterController getInstance() {
        if (instance == null) {
            instance = new TwitterController();
        }
        return instance;
    }

    /////EVERYTHING BELOW THIS IS THE "INSTANCE PART"

    // ArrayList to store the newly added users
    private ArrayList<TwitterUser> newlyAddedUsers;
    //Reference to the data model

    private ArrayList<TwitterUser> dataModel;
    //private ArrayList<Tweet> tweets;
    //Reference to the GUI
    //Any GUI which implements this interface can be
    //communicated with by this controller.
    private ITwitterGUI gui;

    //Add a reference to the persistor.
    private IPersistor persistor;

    //Default constructor
    //Making this private means that it can only be called
    //from inside this class (i.e. Only our getInstance()
    //method can call this now. Nobody outside this class
    //can create an instance of it.
    private TwitterController() {

        this.newlyAddedUsers = new ArrayList<TwitterUser>();
        //Initialise the data model
        //this.dataModel = new ArrayList<TwitterUser>();
        //this.tweets = new ArrayList<Tweet>();
    }

    public void setDataModel(ArrayList<TwitterUser> dataModel) {
        this.dataModel = dataModel;
    }

    public ArrayList<TwitterUser> getDataModel() {
        return this.dataModel;
    }

    public void setGuiReference(ITwitterGUI gui) {
        this.gui = gui;
    }

    public ITwitterGUI getGuiReference() {
        return this.gui;
    }

    public void setPersistor(IPersistor persistor) {
        this.persistor = persistor;
    }

    public IPersistor getPersistor() {
        return this.persistor;
    }


    //This method will be called by the VIEW layer and pass
    //the information filled in in the Add User dialog of the
    //GUI.
    public void createUser(String name, String country) {
        TwitterUser u = new TwitterUser(name, country);
        // Update the datamodel
        this.dataModel.add(u);
        // Add the user to the arraylist of new users
        this.newlyAddedUsers.add(u);
        // Refresh the GUI
        this.gui.refreshGUI();
    }

    public void deleteUser(int index) {
        this.dataModel.remove(index);
        this.gui.refreshGUI();
    }

    public void updateUser(int index, String name, String country) {
        // Get the user from the datamodel
        TwitterUser tempUser = dataModel.get(index);
        // Update the name and country of the user
        tempUser.setUserName(name);
        tempUser.setCountry(country);
        // Replace the old user with the updated user.
        this.dataModel.set(index, tempUser);
        // Refresh the GUI
        this.gui.refreshGUI();
    }

    public void addTweetToUser(int index, Tweet tweet) {
        // Get the user to add the tweet to.
        TwitterUser tempUser = TwitterController.getInstance().getDataModel().get(index);
        // Add the tweet to the user.
        tempUser.addTweet(tweet);
        // Update the data model.
        TwitterController.getInstance().getDataModel().set(index, tempUser);
    }

    public void save() {
        this.persistor.write(this.newlyAddedUsers);
        this.newlyAddedUsers.clear();
    }

}
