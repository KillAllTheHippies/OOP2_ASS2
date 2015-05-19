package view;

/**
 * Created by Jamie on 27/04/15.
 */

import model.TwitterUser;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class TwitterTableModel extends DefaultTableModel {


    private ArrayList<TwitterUser> theUsers;

    private static final int NO_OF_COLS = 2;

    // Constants for column indices
    private static final int USERNAME_COL = 0;
    private static final int COUNTRY_COL = 1;




    public TwitterTableModel(ArrayList<TwitterUser> theUsers) {
        super();
        this.theUsers = theUsers;
    }

    // Now override the methods from the superclass DefaultTableModel
    //that we need to populate the table

    public int getColumnCount()
    {
        return NO_OF_COLS;
    }

    public String getColumnName(int columnIndex)
    {
        if (columnIndex == USERNAME_COL)
            return "User Name";
        else if(columnIndex == COUNTRY_COL)
            return "Country";
        else return "";
    }

    public int getRowCount()
    {
        if (this.theUsers != null)

            return this.theUsers.size();

        else

            return 0;
    }


    public Object getValueAt(int row, int col)
    {


        // Get the selected User.
        TwitterUser currentUser = this.theUsers.get(row);


            if (col == USERNAME_COL)
                return currentUser.getUserName();

            else if (col == COUNTRY_COL)
                return currentUser.getCountry();

            else
                return null;


    }


}

