import javax.swing.JFrame;

public class Application {
    public static void main(String[] args) {
        JFrame mainFrame = new LevelFrame(1);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
