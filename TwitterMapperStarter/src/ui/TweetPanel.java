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
    private JLabel userLabel;
    private JLabel nickLabel;
    private final JTextArea textField = new JTextArea();
    private JLabel dateLabel;
    private final Format dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private JLabel locationLabel;
    private String text;

    public TweetPanel(String user, String nick, BufferedImage image, String text, Date date, String location){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        profilePic = image;
        picLabel = new JLabel(new ImageIcon(profilePic));

        JPanel up = new JPanel();
        up.setLayout(new BoxLayout(up,BoxLayout.X_AXIS));
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.LINE_AXIS));
        imagePanel.add(picLabel);
        up.add(imagePanel);
        up.add(Box.createRigidArea(new Dimension(10, 0)));

        this.dateLabel = new JLabel(dateFormat.format(date));
        this.locationLabel = new JLabel(location);
        this.userLabel = new JLabel(user);
        this.nickLabel = new JLabel("@" + nick);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(userLabel);
        infoPanel.add(nickLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(dateLabel);
        up.add(infoPanel);
        up.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));

        JPanel textPanelContainer = new JPanel();
        textPanelContainer.setLayout(new BoxLayout(textPanelContainer, BoxLayout.LINE_AXIS));
        this.text = text;
        textField.setText(text);
        textField.setEditable(false);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        textPanelContainer.add(textField);
        textPanelContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.add(up);
        this.add(textPanelContainer);

    }

    public void updateContent(String user, String nick, BufferedImage image, String text, Date date, String location){
        this.text = text;

        picLabel.setIcon(new ImageIcon(image));
        userLabel.setText(user);
        nickLabel.setText("@" + nick);
        textField.setText(text);
        dateLabel.setText(dateFormat.format(date));
        locationLabel.setText(location);
    }
}
