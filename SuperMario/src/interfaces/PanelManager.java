package interfaces;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import global.Database;
import hud.ScoreDisplay;
import level.LevelPanel;

public class PanelManager extends JLayeredPane {
    private Map<Integer, JPanel> panels = new HashMap<>();
    private Map<String, JPanel> taggedPanels = new HashMap<>();

    public PanelManager() {
        super();
        taggedPanels.put("level", new LevelPanel());
        Database.levelPanel = (LevelPanel) taggedPanels.get("level");
        
        taggedPanels.put("score-hud", new ScoreDisplay());
        Database.scoreDisplay = (ScoreDisplay) taggedPanels.get("score-hud");
    }

    public void switchPanel(JPanel panel, int layer) {
        try {
            remove(panels.get(layer));
        } catch (NullPointerException e) {
        }
        panels.put(layer, panel);
        add(panels.get(layer), layer);
    }

    public void useLevel(int level) {
        LevelPanel levelPanel = (LevelPanel) taggedPanels.get("level");
        switchPanel(levelPanel, JLayeredPane.DEFAULT_LAYER);
        levelPanel.newLevel(level);
    }

    public void useScoreDisplay() {
        ScoreDisplay scoreDisplay = (ScoreDisplay) taggedPanels.get("score-hud");
        switchPanel(scoreDisplay, JLayeredPane.PALETTE_LAYER);
    }

}
