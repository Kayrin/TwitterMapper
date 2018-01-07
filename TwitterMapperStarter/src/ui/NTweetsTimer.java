package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.ContentPanel;

public class NTweetsTimer {
    private Timer timer;

    private ActionListener taskPerformer;


    public NTweetsTimer(JPanel queryList, ContentPanel contentPanel) {
        taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component c : queryList.getComponents()) {
                    if (c instanceof QueryPanel) {
                        ((QueryPanel) c).label.setText(Integer.toString(((QueryPanel) c).getNTweets()));
                        contentPanel.setTotal(((QueryPanel) c).getTotalNTweets());
                        contentPanel.setTotalTweetsLabel("Number of Tweets : " + Integer.toString(contentPanel.getTotalTweets()));
                    }
                }
            }
        };
        timer = new Timer(1000, taskPerformer);
        timer.start();
    }
}
