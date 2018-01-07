package ui;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import query.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.NTweetsTimer;

public class ContentPanel extends JPanel {
    private JSplitPane topLevelSplitPane;
    private JSplitPane querySplitPane;
    private JPanel newQueryPanel;
    private JPanel existingQueryList;
    private JMapViewer map;
    private NTweetsTimer timer;
    private int totalTweets = 0;
    private JLabel totalTweetsLabel;




    private Application app;

    public ContentPanel(Application app) {
        this.app = app;

        map = new JMapViewer();
        map.setMinimumSize(new Dimension(100, 50));
        setLayout(new BorderLayout());
        newQueryPanel = new NewQueryPanel(app);

        totalTweetsLabel = new JLabel("Number of Tweets : " + Integer.toString(totalTweets));

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
        layerPanelContainer.add(totalTweetsLabel);

        timer = new NTweetsTimer(existingQueryList, this);

        querySplitPane = new JSplitPane(0);
        querySplitPane.setDividerLocation(150);
        querySplitPane.setTopComponent(newQueryPanel);
        querySplitPane.setBottomComponent(layerPanelContainer);


        topLevelSplitPane = new JSplitPane(1);
        topLevelSplitPane.setDividerLocation(200);
        topLevelSplitPane.setLeftComponent(querySplitPane);
        topLevelSplitPane.setRightComponent(map);

        add(topLevelSplitPane, "Center");
        revalidate();

        repaint();
    }

    // Add a new query to the set of queries and update the UI to reflect the new query.
    public void addQuery(Query query) {
        QueryPanel newQueryPanel = query.getPanel();
        newQueryPanel.setLayout(new GridBagLayout());


        newQueryPanel.label = new JLabel(Integer.toString(newQueryPanel.getNTweets()));


        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(query.getColor());
        colorPanel.setPreferredSize(new Dimension(30, 30));

        JButton pauseButton = new JButton("||");
        pauseButton.setPreferredSize(new Dimension(30, 20));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //app.terminateQuery(query);
                //revalidate();
            }
        });

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
        newQueryPanel.add(pauseButton);
        newQueryPanel.add(removeButton);
        newQueryPanel.add(newQueryPanel.label);

        existingQueryList.add(newQueryPanel);
        validate();
    }

    public JMapViewer getViewer() {
        return map;
    }

    public int getTotalTweets() { return totalTweets; }

    public void setTotal(int total) { totalTweets = total; }

    public void setTotalTweetsLabel(String str) { totalTweetsLabel.setText(str);}
}

