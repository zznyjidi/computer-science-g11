package interfaces;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import global.Database;
import global.Settings;
import replay.ReplayFile;

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

        try {
            Database.replayMode = true;
            Database.loadedReplay = ReplayFile.load_frame(new File("replay/2024-12-22_15-01-25.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Show Panel
        manager.useLevel(1);
        manager.useScoreDisplay();

        // bugfix: force rerender
        revalidate();
        repaint();

        Database.scoreDisplay.startTimer();
    }
}
