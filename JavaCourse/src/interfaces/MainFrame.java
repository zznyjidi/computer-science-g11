package interfaces;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

import assessment.QuestionList;

public class MainFrame extends JFrame {
    static PanelManager panelManager;

    public MainFrame() {
        // Prepare Window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024, 576);
        setTitle("Learn Java - Repetition");
        setVisible(true);

        // Create Panel Manager
        panelManager = new PanelManager();
        add(panelManager.getLayeredPane());

        // bugfix: force rerender
        revalidate();
        repaint();

        panelManager.switchPanel(new AssessmentQuestionPanel(QuestionList.questions[0]), JLayeredPane.DEFAULT_LAYER);
    }
}
