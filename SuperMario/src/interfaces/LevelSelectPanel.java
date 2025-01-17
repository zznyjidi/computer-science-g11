package interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import global.Database;
import global.Settings;
import interfaces.list.LeaderBoardEntry;
import interfaces.list.LevelEntry;
import interfaces.list.ListActionListener;
import interfaces.list.ListPane;
import online.LeaderBoard;

public class LevelSelectPanel extends JPanel implements ActionListener, ListActionListener {
    // Components
    ListPane<LevelEntry> levelList;
    ListPane<LeaderBoardEntry> leaderBoard;
    JPanel buttonPanel;

    List<JButton> buttons = new ArrayList<>();

    public LevelSelectPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints componentInfo = new GridBagConstraints();
        componentInfo.fill = GridBagConstraints.VERTICAL;
        componentInfo.insets = new Insets(10, 15, 10, 15);
        componentInfo.weightx = 1;
        componentInfo.weighty = 1;

        leaderBoard = new ListPane<>();
        leaderBoard.setMinimumSize(new Dimension(400, 400));
        leaderBoard.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        componentInfo.gridx = 0;
        componentInfo.gridy = 0;
        componentInfo.gridheight = 2;
        add(leaderBoard, componentInfo);

        levelList = LevelEntry.fromDir();
        levelList.setMinimumSize(new Dimension(200, 400));
        levelList.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
        componentInfo.gridx = 1;
        componentInfo.gridy = 0;
        componentInfo.gridheight = 1;
        add(levelList, componentInfo);

        // Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 10;
        componentInfo.gridx = 1;
        componentInfo.gridy = 1;
        add(buttonPanel, componentInfo);

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(switch (i) {
                case 0 -> "Refresh";
                case 1 -> "Watch";
                case 2 -> "Play";
                case 3 -> "Back";
                default -> "ERROR!";
            });
            constraints.gridy = i;
            button.setFont(Settings.BUTTON_FONT);
            button.addActionListener(this);
            buttons.add(button);
            buttonPanel.add(button, constraints);
        }

        levelList.addActionListener(this);
        levelList.selectEntry(0);

        revalidate();
        repaint();
    }

    @Override
    public void selectChanged(ListPane<?> source, int index, JPanel newEntry) {
        if (source.equals(levelList)) {
            LevelEntry selectedEntry = (LevelEntry) newEntry;
            leaderBoard.updateList(LeaderBoard.fromLevel(selectedEntry.getLevelID()));
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (buttons.indexOf(event.getSource())) {
            // Refresh Button
            case 0 -> {
                LevelEntry selectedEntry = levelList.getSelectedEntryObject();
                leaderBoard.updateList(LeaderBoard.fromLevel(selectedEntry.getLevelID()));
            }
            // Watch Button
            case 1 -> {
                try {
                    Database.panelManager.replayLevel(
                        leaderBoard.getSelectedEntryObject().getReplayJSON()
                    );
                    Database.panelManager.useScoreDisplay();
                    Database.levelTimer.start();
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(this, "Replay Not Selected! ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Play Button
            case 2 -> {
                int levelID = levelList.getSelectedEntryObject().getLevelID();
                Database.panelManager.playLevel(levelID);
                Database.panelManager.useScoreDisplay();
                Database.levelTimer.start();
            }
            // Back Button
            case 3 -> Database.panelManager.useTitle();
        }
    }
}
