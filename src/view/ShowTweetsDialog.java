package view;


import controller.TwitterController;
import model.Tweet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ShowTweetsDialog extends JDialog {

    private JPanel mainPanel;
    private JTextArea txtTweets;
    private JButton btnClose;
    private int index;



    public ShowTweetsDialog(Frame owner, String title, int index) {

        super(owner, title);
        this.index = index;

        mainPanel = new JPanel();

        // set border layout on the panel
        mainPanel.setLayout(new BorderLayout());

        // Add bottom panel
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add center panel
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);




        // add the main panel to the dialog
        this.getContentPane().add(mainPanel);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);



        this.setSize(350, 180);
        this.setResizable(false);


    }

    public JPanel createCenterPanel() {


        // create the panel
        JPanel centerPanel = new JPanel();


        // Create our text area
        this.txtTweets = new JTextArea( 6, 30);
        txtTweets.setLineWrap(true);
        txtTweets.setEditable(false);

        // Populate the text area with tweets
        displayTweets();

        //  create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.gray, 1);

        // set the border to the textfield
        txtTweets.setBorder(border);

        // add the textarea to the center panel
        centerPanel.add(txtTweets);




        return centerPanel;
    }

    public void displayTweets() {
        //Get the tweets from the datamodel
        ArrayList<Tweet> tweets = TwitterController.getInstance().getDataModel().get(index).getTweets();

        // Create a string from the tweet text and the date sent and append them to the textarea
        for (Tweet t : tweets) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String tweetString = dateFormat.format(t.getDateTimeSent()) + ": " + t.getTweetText() + "\n" ;
            txtTweets.append(tweetString);
        }


    }

    public JPanel createBottomPanel() {

        JPanel panel = new JPanel();

        btnClose = new JButton("Close");
        btnClose.addActionListener(new ButtonsActionListener(this));

        panel.add(btnClose);

        return panel;
    }


    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. AddUserDialog)
        private ShowTweetsDialog outerClass;

        public ButtonsActionListener(ShowTweetsDialog outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //We know that the source of any ActionEvent
            //in this program MUST be a JButton seeing as
            //we only added an instance of this listener to
            //JButton objects
            JButton sourceButton = (JButton) e.getSource();
            if (sourceButton.equals(btnClose)) {

                dispose();
            }


        }
    }
}