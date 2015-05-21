package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jamie on 20/05/15.
 */
public class AutoResizingJTextArea extends JTextArea {

    private int padding = 25;


    // Override the paintComponent method so I can dynamically
    // assign the width and height of the textarea
    // This method will be called any time the window is resized.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(new Dimension(this.getParent().getWidth() -  padding , this.getParent().getHeight() - padding));
    }
}
