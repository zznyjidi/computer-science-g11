package interfaces;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Settings;

/*
 * Designed in Canvas
 * https://www.canva.com/design/DAGaIPDi0nQ/wZGvsAGywXT_qLxQM_0igQ/view
 */
public class TitlePanel extends JPanel {

    // Labels
    private JLabel titleLabel;
    private JLabel titleShadowLabel;

    // Buttons
    private JPanel buttonPanel;
    private JButton[] buttons = new JButton[4];

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

        buttonPanel = new JPanel();
        buttonPanel.setBounds(430, 250, 150, 180);
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 10;
        add(buttonPanel);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(switch (i) {
                case 0 -> "Start";
                case 1 -> "Select";
                case 2 -> "Watch";
                case 3 -> "Exit";
                default -> "ERROR!";
            });
            constraints.gridy = i;
            buttons[i].setFont(Settings.BUTTON_FONT);
            buttonPanel.add(buttons[i], constraints);
        }
    }
}
