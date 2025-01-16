package interfaces.list;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

import global.Settings;

public class LeaderBoardEntry extends JPanel {

    // Components
    JLabel nicknameLabel;
    JLabel timeLabel;
    JLabel scoreLabel;

    // Replay Source
    JSONObject replayFile;

    /**
     * Create LeaderBoardEntry from JSONObject
     * @param replayFile JSONObject of the replay file for the entry
     * @return LeaderBoardEntry
     */
    public static LeaderBoardEntry fromJson(JSONObject replayFile) {
        JSONObject infoSection = replayFile.getJSONObject("info");
        JSONObject playerSection = replayFile.getJSONObject("player");
        // Set Nickname
        String nickname = playerSection.getString("nickname");
        if (nickname.equals("")) {
            nickname = "Unknown";
        }
        // Set Time
        int time = infoSection.getInt("time");
        int[] timeParts = new int[] {
            time / 6000,
            (time % 6000) / 100,
            (time % 100) % 100
        };
        String timeString = String.format(
            Settings.timerDisplayFormat, 
            timeParts[0], timeParts[1], timeParts[2]
        );
        // Set Score
        int score = infoSection.getInt("score");

        LeaderBoardEntry entry = new LeaderBoardEntry(nickname, timeString, score);
        entry.replayFile = replayFile;
        return entry;
    }

    public LeaderBoardEntry(String nickname, String timeString, int score) {
        // Prepare Panel & Layout
        super();
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints componentInfo = new GridBagConstraints();
        componentInfo.insets = new Insets(10, 15, 10, 15);
        componentInfo.weightx = 1;
        componentInfo.weighty = 1;

        // Nickname Label
        nicknameLabel = new JLabel(nickname);
        nicknameLabel.setFont(new Font("Consolas", Font.BOLD, 37));
        componentInfo.gridx = 0;
        componentInfo.gridy = 0;
        componentInfo.anchor = GridBagConstraints.LINE_START;
        add(nicknameLabel, componentInfo);

        // Time Label
        timeLabel = new JLabel(timeString);
        timeLabel.setFont(new Font("Consolas", Font.BOLD, 27));
        componentInfo.gridx = 1;
        componentInfo.gridy = 0;
        componentInfo.anchor = GridBagConstraints.FIRST_LINE_END;
        add(timeLabel, componentInfo);

        // Score Label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        componentInfo.gridx = 1;
        componentInfo.gridy = 0;
        componentInfo.anchor = GridBagConstraints.LAST_LINE_END;
        add(scoreLabel, componentInfo);

        revalidate();
        repaint();
    }

    // Default Testing Entry
    public LeaderBoardEntry() {
        this("LoooooooongNickname", "12:34:567", 3);
    }
    // Draw Rounded Boarder
    // https://stackoverflow.com/questions/15025092/border-with-rounded-corners-transparency
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(30, 30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint Background
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        // Paint Boarder
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }

    public JSONObject getReplayJSON() {
        return replayFile;
    }
}
