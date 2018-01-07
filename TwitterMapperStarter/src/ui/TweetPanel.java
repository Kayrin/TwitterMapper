package ui;

import twitter4j.Status;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A panel to display the current tweet
 */
public class TweetPanel extends JPanel{
    private BufferedImage profilePic;
    private JLabel picLabel;
    private final JTextArea textField = new JTextArea();
    private JLabel dateLabel;
    private final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private JLabel locationLabel;
    private String text;

    public TweetPanel(BufferedImage image, String text, Date date, String location){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        profilePic = image;
        picLabel = new JLabel(new ImageIcon(profilePic));

        this.text = text;
        textField.setText(text);
        textField.setEditable(false);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);

        this.dateLabel = new JLabel(dateFormat.format(date));
        this.locationLabel = new JLabel(location);

        add(picLabel);
        add(textField);
        add(dateLabel);
        add(locationLabel);
    }

    public void updateContent(BufferedImage image, String text, Date date, String location){
        this.text = text;

        picLabel.setIcon(new ImageIcon(image));
        textField.setText(text);
        dateLabel.setText(dateFormat.format(date));
        locationLabel.setText(location);
    }
}
