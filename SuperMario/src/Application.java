import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Application {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();

        LevelPanel levelPanel = new LevelPanel(1);
        mainFrame.add(levelPanel);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(Settings.BLOCK_SIZE*LevelPanel.gameBoard[0].length + 15, Settings.BLOCK_SIZE*LevelPanel.gameBoard.length + 35);
        mainFrame.setVisible(true);

        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
