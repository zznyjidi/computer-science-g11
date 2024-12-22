package interfaces;

import java.io.File;
import java.io.FileNotFoundException;

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
        try {
            manager.replayLevel(new File("replay/2024-12-22_15-01-25.json"));
            manager.useScoreDisplay();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // bugfix: force rerender
        revalidate();
        repaint();

        Database.scoreDisplay.startTimer();
    }
}
