package view;

import javax.swing.*;
import java.awt.*;


public class DialogHorizPanel extends JPanel {


    private JLabel label;
    private JTextField textField;
    // define the width and height of the label
    // and the padding
    private int w = 60, h = 25, padding = 25;


    public DialogHorizPanel(String s) {
        label = new JLabel(s);
        textField = new JTextField();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // set the size of the label
        label.setPreferredSize(new Dimension(w, h));

        this.add(label);
        this.add(textField);

    }

    /**
     * I need to override the PaintComponent() method because it is called
     * when the components are put on the GUI, and thus I can get the width of the parent container
     * which I need to make my text field stretch dynamically
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set the width of the text field to the width of parent container
        // minus the width of the label plus some padding
        textField.setSize(new Dimension(this.getParent().getWidth() - (w + padding), h));

    }


    // For future implementation
    public String getTextFieldText() {
        return textField.getText();
    }

    // For future implementation
    public void setTextFieldText(String s) {

        textField.setText(s);
    }
}
