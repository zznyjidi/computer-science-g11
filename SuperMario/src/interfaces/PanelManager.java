package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import global.Database;
import hud.ScoreDisplay;
import level.LevelPanel;
import replay.ReplayFile;

public class PanelManager {
    private Map<Integer, JPanel> panels = new HashMap<>();
    private Map<String, JPanel> taggedPanels = new HashMap<>();
    JLayeredPane layeredPane = new JLayeredPane();

    public void switchPanel(JPanel panel, Integer layer) {
        try {
            layeredPane.remove(panels.get(layer));
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        try {
            panels.put(layer, panel);
            layeredPane.add(panels.get(layer), layer);
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void useLevel(int level) {
        if (taggedPanels.get("level") == null) {
            if (Database.levelPanel == null)
                Database.levelPanel = new LevelPanel();
            taggedPanels.put("level", Database.levelPanel);
        }
        LevelPanel levelPanel = (LevelPanel) taggedPanels.get("level");
        switchPanel(levelPanel, JLayeredPane.DEFAULT_LAYER);
        levelPanel.newLevel(level);
    }

    public void playLevel(int level) {
        Database.replayMode = false;
        useLevel(level);
    }

    public void replayLevel(File replayFile) throws FileNotFoundException {
        JSONObject replayJson = ReplayFile.load(replayFile);
        Database.replayMode = true;
        Database.loadedReplay = ReplayFile.extractFrames(replayJson.getJSONArray("replay"));
        useLevel(replayJson.getJSONObject("info").getInt("level_id"));
    }

    public void useScoreDisplay() {
        if (taggedPanels.get("score-hud") == null) {
            if (Database.scoreDisplay == null)
                Database.scoreDisplay = new ScoreDisplay();
            taggedPanels.put("score-hud", Database.scoreDisplay);
        }
        ScoreDisplay scoreDisplay = (ScoreDisplay) taggedPanels.get("score-hud");
        switchPanel(scoreDisplay, JLayeredPane.PALETTE_LAYER);
    }

}
