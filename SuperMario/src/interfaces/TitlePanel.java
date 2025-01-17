package interfaces;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Database;
import global.Settings;

/*
 * Designed in Canvas
 * https://www.canva.com/design/DAGaIPDi0nQ/wZGvsAGywXT_qLxQM_0igQ/view
 */
public class TitlePanel extends JPanel implements ActionListener {

    // Labels
    private JLabel titleLabel;
    private JLabel titleShadowLabel;

    // Buttons
    private JPanel buttonPanel;
    private List<JButton> buttons = new ArrayList<>();

    public TitlePanel() {
        setLayout(null);

        // Title Text
        titleLabel = new JLabel("MARIO");
        titleLabel.setFont(Settings.TITLE_FONT);
        titleLabel.setForeground(new Color(0x354eab));
        titleLabel.setBounds(55, 50, 300, 100);
        add(titleLabel);

        // Title Text Shadow
        titleShadowLabel = new JLabel("MARIO");
        titleShadowLabel.setFont(Settings.TITLE_FONT);
        titleShadowLabel.setBounds(55, 60, 300, 100);
        titleShadowLabel.setForeground(new Color(0x87d398));
        add(titleShadowLabel);

        // Buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(430, 250, 150, 180);
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 10;
        add(buttonPanel);

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(switch (i) {
                case 0 -> "Start";
                case 1 -> "Select";
                case 2 -> "Watch";
                case 3 -> "Exit";
                default -> "ERROR!";
            });
            constraints.gridy = i;
            button.setFont(Settings.BUTTON_FONT);
            button.addActionListener(this);
            buttons.add(button);
            buttonPanel.add(button, constraints);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (buttons.indexOf(e.getSource())) {
            // Start Button
            case 0 -> {
                Database.panelManager.playLevel(1);
                Database.panelManager.useScoreDisplay();
                Database.levelTimer.start();
            }
            // Select Button
            case 1 -> {
                Database.panelManager.useLevelSelect();
            }
            // Watch Button
            case 2 -> {
                Database.panelManager.useReplaySelect();
            }
            // Exit Button
            case 3 -> {
                System.exit(0);
            }
        }
    }
}
