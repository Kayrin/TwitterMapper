package ui;

import twitter4j.Status;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * A panel to display the current tweet
 */
public class TweetPanel extends JPanel{
    private final JTextArea textField = new JTextArea();
    private String text;

    public TweetPanel(String text){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.text = text;
        textField.setText(text);
        textField.setEditable(false);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        add(textField);
    }

    public void updateContent(String text){
        this.text = text;
        textField.setText(text);
    }
}
