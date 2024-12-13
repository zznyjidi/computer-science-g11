package interfaces;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import global.Database;
import hud.ScoreDisplay;
import level.LevelPanel;

public class PanelManager {
    private Map<Integer, JPanel> panels = new HashMap<>();
    private Map<String, JPanel> taggedPanels = new HashMap<>();
    JLayeredPane layeredPane = new JLayeredPane();

    public void switchPanel(JPanel panel, int layer) {
        try {
            layeredPane.remove(panels.get(layer));
        } catch (NullPointerException e) {
        }
        panels.put(layer, panel);
        layeredPane.add(panels.get(layer), layer);
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void useLevel(int level) {
        if (taggedPanels.get("level") == null) {
            taggedPanels.put("level", new LevelPanel());
            Database.levelPanel = (LevelPanel) taggedPanels.get("level");
        }
        LevelPanel levelPanel = (LevelPanel) taggedPanels.get("level");
        switchPanel(levelPanel, JLayeredPane.DEFAULT_LAYER);
        levelPanel.newLevel(level);
    }

    public void useScoreDisplay() {
        if (taggedPanels.get("score-hud") == null) {
            taggedPanels.put("score-hud", new ScoreDisplay());
            Database.scoreDisplay = (ScoreDisplay) taggedPanels.get("score-hud");
        }
        ScoreDisplay scoreDisplay = (ScoreDisplay) taggedPanels.get("score-hud");
        switchPanel(scoreDisplay, JLayeredPane.PALETTE_LAYER);
    }

}
