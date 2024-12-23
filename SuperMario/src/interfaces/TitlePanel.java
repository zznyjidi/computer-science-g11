package interfaces;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import global.Database;
import global.Settings;

/*
 * Designed in Canvas
 * https://www.canva.com/design/DAGaIPDi0nQ/wZGvsAGywXT_qLxQM_0igQ/view
 */
public class TitlePanel extends JPanel {

    private JLabel titleLabel;
    private JLabel titleShadowLabel;

    public TitlePanel() {
        setLayout(null);

        titleLabel = new JLabel("MARIO");
        titleLabel.setFont(Settings.TITLE_FONT);
        titleLabel.setForeground(new Color(0x354eab));
        titleLabel.setBounds(55, 50, 300, 100);
        add(titleLabel);

        titleShadowLabel = new JLabel("MARIO");
        titleShadowLabel.setFont(Settings.TITLE_FONT);
        titleShadowLabel.setBounds(55, 60, 300, 100);
        titleShadowLabel.setForeground(new Color(0x87d398));
        add(titleShadowLabel);

        
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(Database.windowLength, Database.windowWidth);
        mainFrame.setTitle(Settings.GAME_NAME);
        mainFrame.setVisible(true);

        mainFrame.add(new TitlePanel());

        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
