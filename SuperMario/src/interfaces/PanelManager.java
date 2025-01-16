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
    // Components
    private Map<Integer, JPanel> panels = new HashMap<>();
    private Map<String, JPanel> taggedPanels = new HashMap<>();
    private JLayeredPane layeredPane = new JLayeredPane();

    /**
     * Switch the Panel at specific layer
     * @param panel new Panel
     * @param layer target layer
     */
    public void switchPanel(JPanel panel, Integer layer) {
        try {
            layeredPane.remove(panels.get(layer));
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        try {
            panels.put(layer, panel);
            panel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
            layeredPane.add(panels.get(layer), layer);
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void removePanel(Integer layer) {
        try {
            layeredPane.remove(panels.get(layer));
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    // Pages
    public void useTitle() {
        if (taggedPanels.get("title") == null) {
            taggedPanels.put("title", new TitlePanel());
        }
        TitlePanel titlePanel = (TitlePanel) taggedPanels.get("title");
        switchPanel(titlePanel, JLayeredPane.DEFAULT_LAYER);
    }
    public void useReplaySelect() {
        if (taggedPanels.get("replay-select") == null) {
            taggedPanels.put("replay-select", new ReplaySelectPanel());
        }
        ReplaySelectPanel replaySelectPanel = (ReplaySelectPanel) taggedPanels.get("replay-select");
        switchPanel(replaySelectPanel, JLayeredPane.DEFAULT_LAYER);
    }

    // Levels
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
        replayLevel(replayJson);
    }

    public void replayLevel(JSONObject replayJson) {
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
