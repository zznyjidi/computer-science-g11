package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.json.JSONObject;

import online.LeaderBoard;

public class LeaderBoardPane extends JScrollPane implements MouseListener {

    private JPanel leaderBoardPanel;
    private List<JPanel> leaderBoardEntries = new ArrayList<>();

    private List<JSONObject> leaderBoardInfo;

    private int selectedReplay = -1;

    public static LeaderBoardPane fromLevel(int level) {
        // Fetch LeaderBoardInfo
        List<JSONObject> leaderBoardInfo = new ArrayList<>();
        for (Object replayObject : LeaderBoard.fetch(level)) {
            leaderBoardInfo.add((JSONObject) replayObject);
        }

        return new LeaderBoardPane(leaderBoardInfo);
    }

    public LeaderBoardPane(List<JSONObject> replays) {
        this();
        updateList(replays);
    }

    public LeaderBoardPane() {
        // JPanel that list the scores
        leaderBoardPanel = new JPanel();
        leaderBoardPanel.setLayout(new BoxLayout(leaderBoardPanel, BoxLayout.PAGE_AXIS));
        setViewportView(leaderBoardPanel);
    }

    public void updateList(List<JSONObject> replays) {
        selectedReplay = -1;
        leaderBoardPanel.removeAll();
        leaderBoardEntries.clear();

        leaderBoardInfo = replays;
        // Create Entries form Json
        for (Object scoreObject : leaderBoardInfo) {
            JPanel leaderBoardEntry = LeaderBoardEntry.fromJson((JSONObject) scoreObject);
            // Set Max Size for Entries
            // https://stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
            leaderBoardEntry.setMaximumSize(new Dimension(500, 68));
            leaderBoardEntry.setMinimumSize(new Dimension(300, 68));
            // Clickable Panel
            // https://stackoverflow.com/questions/9967006/how-to-call-a-function-when-i-click-on-a-jpanel-java
            leaderBoardEntry.addMouseListener(this);
            // Add Entry to Panel
            leaderBoardEntries.add(leaderBoardEntry);
            leaderBoardPanel.add(leaderBoardEntry);
        }
        leaderBoardPanel.revalidate();
        leaderBoardPanel.repaint();
    }

    public int getSelectedReplay() {
        return selectedReplay;
    }

    public JSONObject getSelectedReplayJson() {
        return (JSONObject) leaderBoardInfo.get(selectedReplay);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Restore old Panel
        try {
            // Default Panel Color
            // https://stackoverflow.com/questions/9991204/get-default-background-color-of-swing-component
            leaderBoardEntries.get(selectedReplay).setBackground(UIManager.getColor("Panel.background"));
        } catch (IndexOutOfBoundsException e) {
        }
        // Set new Selected Panel
        selectedReplay = leaderBoardEntries.indexOf(event.getSource());
        ((JPanel) event.getSource()).setBackground(new Color(0x4ce5fc));;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
