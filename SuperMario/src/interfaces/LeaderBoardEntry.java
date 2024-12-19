package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

public class LeaderBoardEntry extends JPanel {
    JLabel nicknameLabel;
    JLabel timeLabel;
    JLabel scoreLabel;

    public LeaderBoardEntry(JSONObject replayFile) {
        setLayout(new GridBagLayout());
        GridBagConstraints componentInfo = new GridBagConstraints();
        componentInfo.ipadx = 4;
        componentInfo.ipady = 2;

        // Nickname Label
        nicknameLabel = new JLabel("Nickname");
        componentInfo.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nicknameLabel, componentInfo);

        // timeLabel
        timeLabel = new JLabel("mm:ss:mss");
    }
}
