package view;


import controller.TwitterController;
import model.TwitterUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpdateUserDialog extends JDialog {

    private JPanel mainPanel;
    private DialogHorizPanel namePanel, countryPanel;
    private JButton btnUpdateUser, btnClose;
    // create constants for the labels
    private static final String USERNAME_LABEL = "User Name: ";
    private static final String COUNTRY_LABEL = "Country: ";
    private TwitterUser user;
    private int index;

    public UpdateUserDialog(Frame owner, String title, int index, TwitterUser user) {

        super(owner, title);
        this.user = user;
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



        this.setSize(350,180);
        this.setResizable(false);


    }

    public JPanel createCenterPanel() {


        // create the panel
        JPanel centerPanel = new JPanel();

        // set a layout

        // Set vertical box layout on the main panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));


        // Create our 2 horizontal panels (custom component extended from JPanel)
        // and pass in the text for the label
        this.namePanel = new DialogHorizPanel(USERNAME_LABEL);
        this.countryPanel = new DialogHorizPanel(COUNTRY_LABEL);

        // Populate the text fields
        this.namePanel.setTextFieldText(this.user.getUserName());
        this.countryPanel.setTextFieldText(this.user.getCountry());



        // add the panels to the center panel
        centerPanel.add(namePanel);
        centerPanel.add(countryPanel);



        return centerPanel;
    }

    public JPanel createBottomPanel() {

        JPanel panel = new JPanel();

        btnUpdateUser = new JButton("Update User");
        btnUpdateUser.addActionListener(new ButtonsActionListener(this));

        btnClose = new JButton("Close");
        btnClose.addActionListener(new ButtonsActionListener(this));

        panel.add(btnUpdateUser);
        panel.add(btnClose);

        return panel;
    }

    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. AddUserDialog)
        private UpdateUserDialog outerClass;

        public ButtonsActionListener(UpdateUserDialog outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e) {
            //We know that the source of any ActionEvent
            //in this program MUST be a JButton seeing as
            //we only added an instance of this listener to
            //JButton objects
            JButton sourceButton = (JButton) e.getSource();

            if (sourceButton.equals(btnUpdateUser)) {
                TwitterController.getInstance().updateUser(index, namePanel.getTextFieldText(), countryPanel.getTextFieldText());
                dispose();
            } else if (sourceButton.equals(btnClose)) {

                dispose();
            }


        }
    }
}