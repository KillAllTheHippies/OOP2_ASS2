package view;

/**
 * Created by Jamie on 27/04/15.
 */

import controller.ITwitterGUI;
import controller.TwitterController;
import model.TwitterUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TwitterFrame extends JFrame implements ITwitterGUI{

    private JButton addButton;
    private JButton closeButton;
    private JButton showTweetsButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTable usersTable;
    private TwitterTableModel tableModel;

    public TwitterFrame(String title) {
        super(title);

        // Give the controller a reference to this gui
        TwitterController.getInstance().setGuiReference(this);

        // Content of our JFrame
        JPanel mainPanel = new JPanel();

        // set border layout
        mainPanel.setLayout(new BorderLayout());

        // create the side and bottom panels and add them to the layout
        JPanel sidePanel = createSideButtonPanel();
        mainPanel.add(sidePanel, BorderLayout.EAST);
        JPanel bottomPanel = createBottomButtonPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // create the table which is in a scrollPane
        JScrollPane tableScrollPane = createTableScrollPane();
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);


        this.getContentPane().add(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private JScrollPane createTableScrollPane() {

        // Create a table and a scrollpane, add patients to an arraylist, connect the arraylist
        // to the table model, connect the table model to the table, put the table in a scrollpane,
        // and return the scrollpane
        usersTable = new JTable();


        ArrayList<TwitterUser> tempUsers = TwitterController.getInstance().getDataModel();

        //TwitterUser t1 = new TwitterUser("Billy", "Nowhere");


        //tempUsers.add(t1);



        this.tableModel = new TwitterTableModel(tempUsers);
        usersTable.setModel(tableModel);


        JScrollPane tableScrollPane = new JScrollPane(usersTable);
        return tableScrollPane;
    }

    @Override
    public void refreshGUI() {
        // To make a table refresh itself call fireTableDataChanged() method of the table model.
        tableModel.fireTableDataChanged();
    }


    /**
     * This method creates the bottom JPanel, puts buttons on it and returns the fully constructed JPanel
     */
    private JPanel createBottomButtonPanel() {
        // Instantiate panel, button & listener, add listener to button,
        // add button to panel, return panel.
        JPanel buttonPanel = new JPanel();
        closeButton = new JButton("Close");
        ButtonsActionListener buttonListener =
                new ButtonsActionListener(this);

        closeButton.addActionListener(buttonListener);

        buttonPanel.add(closeButton);

        return buttonPanel;
    }

    private JPanel createSideButtonPanel() {
        // instantiate the buttons
        this.addButton = new JButton("Add User");
        this.deleteButton = new JButton("Delete User");
        this.updateButton = new JButton("Update User");
        this.showTweetsButton = new JButton("Show Tweets");


        // Instantiate the listener for the buttons,
        // passing it in a reference to this class (SurgeryFrame)
        // and assign it to the buttons
        ButtonsActionListener buttonListener = new ButtonsActionListener(this);
        addButton.addActionListener(buttonListener);
        deleteButton.addActionListener(buttonListener);
        showTweetsButton.addActionListener(buttonListener);
        updateButton.addActionListener(buttonListener);

        // Create panel, assign layout, add components.
        JPanel sideButtonPanel = new JPanel();
        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS));
        sideButtonPanel.add(addButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(deleteButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(updateButton);
        sideButtonPanel.add(Box.createVerticalStrut(5));
        sideButtonPanel.add(showTweetsButton);

        return sideButtonPanel;
    }


    //Inner class implementation of ActionListener
    private class ButtonsActionListener implements ActionListener {
        //This is to allow this inner class to refer to its
        //containing class (i.e. TwitterFrame)
        private TwitterFrame outerClass;

        public ButtonsActionListener(TwitterFrame outerClass) {
            this.outerClass = outerClass;
        }

        public void actionPerformed(ActionEvent e)
        {
            //Listener for button clicks.
            JButton sourceButton = (JButton) e.getSource();

            if (sourceButton.equals(addButton)) {

                // Launch a dialog and set its size when the add button is clicked
                AddUserDialog addPatDlg =
                        new AddUserDialog(this.outerClass, "Add User");

                //addPatDlg.setSize(600, 200);

                addPatDlg.setVisible(true);

            } else if (sourceButton.equals(closeButton)) {

                dispose();

            } else if (sourceButton.equals(showTweetsButton)) {

               System.out.println("Show Tweets Clicked");

            } else if (sourceButton.equals(updateButton)) {

                System.out.println("Update Clicked");

            }

             //This is the code that responds to the delete button
            else {

                // check if row is selected
                if (usersTable.getSelectedRow() == -1) {

                    JOptionPane.showMessageDialog(outerClass, "You need to select a table row",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                // If table row is selected then launch delete dialog
                else {

                    String message = "Are you sure you want to delete this user??";
                    int answer =
                            JOptionPane.showConfirmDialog(outerClass, message);
                    if (answer == JOptionPane.YES_OPTION) {
                        TwitterController.getInstance().deleteUser(usersTable.getSelectedRow());
                    } else if (answer == JOptionPane.NO_OPTION) {
                        System.out.println("User Deletion Cancelled");
                    }

                }

            }

        }
    }

}
