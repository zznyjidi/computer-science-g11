package interfaces;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONObject;

import online.LeaderBoard;

public class LeaderBoardPane extends JScrollPane {

    private JPanel leaderBoardPanel;
    private List<JPanel> leaderBoardEntries = new ArrayList<>();

    private JSONArray leaderBoardInfo;

    public LeaderBoardPane(int level) {
        // Fetch LeaderBoardInfo
        leaderBoardInfo = LeaderBoard.fetch(level);

        // JPanel that list the scores
        leaderBoardPanel = new JPanel();
        leaderBoardPanel.setLayout(new BoxLayout(leaderBoardPanel, BoxLayout.PAGE_AXIS));

        // Create Entries form Json
        for (Object scoreObject : leaderBoardInfo) {
            JPanel leaderBoardEntry = LeaderBoardEntry.fromJson((JSONObject) scoreObject);
            // Set Max Size for Entries
            // https://stackoverflow.com/questions/18405660/how-to-set-component-size-inside-container-with-boxlayout
            leaderBoardEntry.setMaximumSize(new Dimension(500, 68));
            leaderBoardEntry.setMinimumSize(new Dimension(300, 68));
            // Add Entry to Panel
            leaderBoardEntries.add(leaderBoardEntry);
            leaderBoardPanel.add(leaderBoardEntry);
        }

        setViewportView(leaderBoardPanel);
    }
}
