import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

import global.Database;
import global.Settings;
import hud.ScoreDisplay;
import interfaces.PanelManager;
import level.LevelPanel;
import online.Account;
import replay.ReplayRecorder;

public class Application {

    public static void main(String[] args) {
        Settings.scoreServerAddr = new String[] {"http", "127.0.0.1:8080"};
        Database.account = new Account("test_user");
        Database.account.login("uU55c1Y340XpLgJJHp74");
        Database.replayRecorder = new ReplayRecorder();

        JFrame mainFrame = new JFrame();
        // mainFrame.setLayout(null);

        PanelManager manager = new PanelManager();
        mainFrame.add(manager);

        LevelPanel levelPanel = new LevelPanel(1);
        Database.levelPanel = levelPanel;
        manager.switchPanel(levelPanel, JLayeredPane.DEFAULT_LAYER);

        ScoreDisplay scoreDisplay = new ScoreDisplay();
        Database.scoreDisplay = scoreDisplay;
        manager.switchPanel(scoreDisplay, JLayeredPane.PALETTE_LAYER);

        // SoundPlayer.play(Sound.metalPipe);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(Database.windowLength, Database.windowWidth);
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();

        scoreDisplay.startTimer();
    }
}
