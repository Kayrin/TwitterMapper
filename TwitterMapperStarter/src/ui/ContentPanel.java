package ui;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import query.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;

import static util.Util.imageFromURL;

public class ContentPanel extends JPanel {
    private JSplitPane topLevelSplitPane;
    private JSplitPane leftSplitPane;
    private JSplitPane querySplitPane;
    private JPanel newQueryPanel;
    private JPanel existingQueryList;
    private TweetPanel currentTweetPanel;
    private JMapViewer map;

    private Application app;

    public ContentPanel(Application app) {
        this.app = app;

        map = new JMapViewer();
        map.setMinimumSize(new Dimension(100, 50));
        setLayout(new BorderLayout());
        currentTweetPanel = new TweetPanel("User", "nickname", imageFromURL("http://png-2.findicons.com/files/icons/1995/web_application/48/smiley.png"),
                "Tweet's content will be displayed here", new Date(), "Location");
        newQueryPanel = new NewQueryPanel(app);

        // NOTE: We wrap existingQueryList in a container so it gets a pretty border.
        JPanel layerPanelContainer = new JPanel();
        existingQueryList = new JPanel();
        existingQueryList.setLayout(new javax.swing.BoxLayout(existingQueryList, javax.swing.BoxLayout.Y_AXIS));
        layerPanelContainer.setLayout(new BorderLayout());
        layerPanelContainer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Current Queries"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        layerPanelContainer.add(existingQueryList, BorderLayout.NORTH);


        leftSplitPane = new JSplitPane(0);
        leftSplitPane.setDividerLocation(200);
        leftSplitPane.setTopComponent(currentTweetPanel);
        leftSplitPane.setBottomComponent(newQueryPanel);
        querySplitPane = new JSplitPane(0);
        querySplitPane.setDividerLocation(350);
        querySplitPane.setTopComponent(leftSplitPane);
        querySplitPane.setBottomComponent(layerPanelContainer);


        topLevelSplitPane = new JSplitPane(1);
        topLevelSplitPane.setDividerLocation(300);
        topLevelSplitPane.setLeftComponent(querySplitPane);
        topLevelSplitPane.setRightComponent(map);

        add(topLevelSplitPane, "Center");
        revalidate();

        repaint();
    }

    // Add a new query to the set of queries and update the UI to reflect the new query.
    public void addQuery(Query query) {
        JPanel newQueryPanel = new JPanel();
        newQueryPanel.setLayout(new GridBagLayout());

        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(query.getColor());
        colorPanel.setPreferredSize(new Dimension(30, 30));

//        JButton pauseButton = new JButton("||");
//        pauseButton.setPreferredSize(new Dimension(30, 20));
//        pauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //app.terminateQuery(query);
//                //revalidate();
//            }
//        });

        JButton removeButton = new JButton("x");
        removeButton.setPreferredSize(new Dimension(40, 20));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.terminateQuery(query);
                query.terminate();
                existingQueryList.remove(newQueryPanel);
                revalidate();
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        newQueryPanel.add(colorPanel, c);

        c = new GridBagConstraints();
        JCheckBox checkbox = new JCheckBox(query.getQueryString());
        checkbox.setSelected(true);
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.updateVisibility();
            }
        });
        query.setCheckBox(checkbox);
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        newQueryPanel.add(checkbox, c);
//        newQueryPanel.add(pauseButton);
        newQueryPanel.add(removeButton);

        existingQueryList.add(newQueryPanel);
        validate();
    }

    public JMapViewer getViewer() {
        return map;
    }

    public void updateTweetPanel(String user, String nick, BufferedImage image, String text, Date date, String location){
        currentTweetPanel.updateContent(user, nick, image, text, date, location);
    }

}

