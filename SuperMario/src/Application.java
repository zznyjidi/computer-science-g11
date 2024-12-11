import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

import global.Database;
import hud.ScoreDisplay;
import level.LevelPanel;

public class Application {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        // mainFrame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        mainFrame.add(layeredPane);

        LevelPanel levelPanel = new LevelPanel(1);
        layeredPane.add(levelPanel, JLayeredPane.DEFAULT_LAYER);

        // SoundPlayer.play(Sound.metalPipe);

        ScoreDisplay scoreDisplay = new ScoreDisplay();
        Database.scoreDisplay = scoreDisplay;
        layeredPane.add(scoreDisplay, JLayeredPane.PALETTE_LAYER);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(Database.windowLength, Database.windowWidth);
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();

        scoreDisplay.startTimer();
    }
}
