package view;


import controller.TwitterController;
import model.Tweet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class AddTweetDialog extends JDialog {

    private JPanel mainPanel;
    private JTextArea txtTweet;
    private JButton btnTweet, btnCancel;
    private int index;



    public AddTweetDialog(Frame owner, String title, int index) {

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
        this.txtTweet = new JTextArea("Enter tweet", 6, 30);
        txtTweet.setLineWrap(true);

        //  create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.gray, 1);

        // set the border to the textfield
        txtTweet.setBorder(border);

        // add the textarea to the center panel
        centerPanel.add(txtTweet);

        return centerPanel;
    }

    public JPanel createBottomPanel() {

        JPanel panel = new JPanel();

        btnCancel = new JButton("Close");
        btnCancel.addActionListener(new ButtonsActionListener(this));

        btnTweet = new JButton("Tweet!");
        btnTweet.addActionListener(new ButtonsActionListener(this));

        panel.add(btnTweet);
        panel.add(btnCancel);

        return panel;
    }


    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. AddUserDialog)
        private AddTweetDialog outerClass;

        public ButtonsActionListener(AddTweetDialog outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //We know that the source of any ActionEvent
            //in this program MUST be a JButton seeing as
            //we only added an instance of this listener to
            //JButton objects
            JButton sourceButton = (JButton) e.getSource();

            if (sourceButton.equals(btnTweet)) {
                // Get the text from the textArea
                String tweet = txtTweet.getText();

                // Create a new Tweet object and initialise the date
                Date d = new Date(System.currentTimeMillis());
                Tweet newTweet = new Tweet(tweet, d);

                // Add the tweet to the user
                TwitterController.getInstance().addTweetToUser(index, newTweet);

                dispose();
            } else if (sourceButton.equals(btnCancel)) {

                dispose();
            }


        }
    }
}