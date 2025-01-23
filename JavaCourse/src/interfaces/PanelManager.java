package interfaces;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import assessment.QuestionList;

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
        removePanel(layer);
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
            taggedPanels.put("last-panel-"+layer, panels.get(layer));
            layeredPane.remove(panels.get(layer));
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
    }

    public void useLastPage(Integer layer) {
        switchPanel(taggedPanels.get("last-panel-"+layer), layer);
    }

    public boolean lastPageIsTag(Integer layer, String tag) {
        return taggedPanels.get("last-panel-"+layer).equals(taggedPanels.get(tag));
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void useAssessment() {
        if (!taggedPanels.containsKey("assessment-manager")) {
            if (QuestionList.pageManager == null) {
                QuestionList.pageManager = QuestionList.createPagedManager();
            }
            taggedPanels.put("assessment-manager", QuestionList.pageManager);
        }
        switchPanel(taggedPanels.get("assessment-manager"), JLayeredPane.DEFAULT_LAYER);
    }
}
