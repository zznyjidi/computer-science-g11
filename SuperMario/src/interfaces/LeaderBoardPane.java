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

    // Components
    private JPanel leaderBoardPanel;
    private List<JPanel> leaderBoardEntries = new ArrayList<>();

    // Replay List
    private List<JSONObject> leaderBoardInfo;
    private int selectedReplay = -1;

    /**
     * Fetch Leader Board From Server
     * @param level level id
     * @return JScrollPane for LeaderBoard
     */
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
        leaderBoardPanel = new JPanel();
        leaderBoardPanel.setLayout(new BoxLayout(leaderBoardPanel, BoxLayout.PAGE_AXIS));
        setViewportView(leaderBoardPanel);
    }

    /**
     * Recreate List with new replay List
     * @param replays List of JSONObject of Replay Files
     */
    public void updateList(List<JSONObject> replays) {
        selectedReplay = -1;
        leaderBoardPanel.removeAll();
        leaderBoardEntries.clear();

        leaderBoardInfo = replays;
        // Create Entries form Json
        for (Object scoreObject : leaderBoardInfo) {
            newEntry((JSONObject) scoreObject);
        }
        leaderBoardPanel.revalidate();
        leaderBoardPanel.repaint();
    }

    /**
     * Add New LeaderBoard Entry at the End
     * @param ReplayFile JSONObject of Replay File
     * @return Index of the Entry in the List
     */
    public int newEntry(JSONObject ReplayFile) {
        JPanel leaderBoardEntry = LeaderBoardEntry.fromJson(ReplayFile);
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

        leaderBoardPanel.revalidate();
        leaderBoardPanel.repaint();
        return leaderBoardEntries.indexOf(leaderBoardEntry);
    }

    /**
     * Select a Entry in the Pane
     * @param index index of the Entry to Select
     */
    public void selectEntry(int index) {
        // Restore old Panel
        try {
            // Default Panel Color
            // https://stackoverflow.com/questions/9991204/get-default-background-color-of-swing-component
            leaderBoardEntries.get(selectedReplay).setBackground(UIManager.getColor("Panel.background"));
        } catch (IndexOutOfBoundsException e) {
        }
        // Set new Selected Panel
        selectedReplay = index;
        leaderBoardEntries.get(index).setBackground(new Color(0x4ce5fc));
    }

    public int getSelectedReplay() {
        return selectedReplay;
    }

    public JSONObject getSelectedReplayJson() {
        return (JSONObject) leaderBoardInfo.get(selectedReplay);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        selectEntry(leaderBoardEntries.indexOf(event.getSource()));
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
