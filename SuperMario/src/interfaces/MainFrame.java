package interfaces;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import global.Database;
import global.Settings;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Prepare Window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Database.windowLength, Database.windowWidth);
        setTitle(Settings.GAME_NAME);
        setVisible(true);

        // Create PanelManager
        PanelManager manager = new PanelManager();
        Database.panelManager = manager;
        add(manager.getLayeredPane());

        // Show Panel
        manager.useLevel(1);
        manager.useScoreDisplay();

        // bugfix: force rerender
        revalidate();
        repaint();

        Database.scoreDisplay.startTimer();
    }
}
