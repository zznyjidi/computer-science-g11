package interfaces;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PagedPanelManager<PanelType extends JPanel> extends JPanel {
    List<PanelType> panelList;
    PanelType currentPanel;
    int panelIndex;

    public PagedPanelManager() {
        setLayout(new BorderLayout());
        panelIndex = 0;
    }

    public PagedPanelManager(List<PanelType> panelList) {
        this();
        this.panelList = panelList;
        switchPanel(panelIndex);
    }

    public void switchPanel(PanelType newPanel) {
        SwingUtilities.invokeLater(() -> {
            try {
                remove(currentPanel);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            currentPanel = newPanel;
            add(newPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
        });
    }

    public void switchPanel(int pageIndex) {
        panelIndex = pageIndex;
        switchPanel(panelList.get(panelIndex));
    }
    public void restoreToPage() {
        switchPanel(panelIndex);
    }

    public void lastPage() {
        if (panelIndex > 0)
            switchPanel(panelIndex - 1);
    }
    public void nextPage() {
        if (panelIndex < panelList.size() - 1)
            switchPanel(panelIndex + 1);
    }
}
