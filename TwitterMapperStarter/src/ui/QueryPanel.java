package ui;

import javax.swing.*;
import ui.ContentPanel;

public class QueryPanel extends JPanel {
    // The number of Tweets corresponding to this query
    private int nTweets = 0;
    // The total number of tweets
    private static int totalNTweets = 0;

    public JLabel label;

    public QueryPanel() {
        super();
    }

    public void incrementTweets() {
        nTweets++;
        totalNTweets++;
    }

    public int getNTweets() { return nTweets; }

    public int getTotalNTweets() { return  totalNTweets; }

}
