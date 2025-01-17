package interfaces;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import global.Database;
import global.Settings;
import interfaces.list.LeaderBoardEntry;
import interfaces.list.ListPane;
import interfaces.list.ReplayEntry;
import replay.ReplayFile;

public class ReplaySelectPanel extends JPanel implements ActionListener {

    // Components
    ListPane<LeaderBoardEntry> leaderBoardPane;

    // Buttons
    private JPanel buttonPanel;
    private List<JButton> buttons = new ArrayList<>();

    public ReplaySelectPanel() {
        setLayout(null);

        leaderBoardPane = new ListPane<>();
        leaderBoardPane.setBounds(0, 0, 400, 500);
        add(leaderBoardPane);

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
                case 0 -> "Refresh";
                case 1 -> "Browse";
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

        refreshDir();
    }

    /**
     * Reload the List from the replay Directory
     */
    public void refreshDir() {
        // List File in Dir
        // https://www.baeldung.com/java-list-directory-files
        File[] replayFiles = new File("replay").listFiles();

        // Read Replay Files
        List<LeaderBoardEntry> replayEntryComponents = Stream.of(replayFiles)
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().endsWith(".json"))
                .map(file -> {
                    try {
                        return ReplayFile.load(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return new JSONObject();
                    }
                })
                .map(replayJSON -> ReplayEntry.fromJson(replayJSON))
                .collect(Collectors.toList());
        leaderBoardPane.updateList(replayEntryComponents);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (buttons.indexOf(event.getSource())) {
            // Refresh Button
            case 0 -> refreshDir();
            // Browse Button
            case 1 -> {
                try {
                    JSONObject replayFile = ReplayFile.load(ReplayFile.chooseReplayFile());
                    leaderBoardPane.selectEntry(leaderBoardPane.newEntry(ReplayEntry.fromJson(replayFile)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // Play Button
            case 2 -> {
                try {
                    Database.panelManager.replayLevel(
                        leaderBoardPane.getSelectedEntryObject().getReplayJSON()
                    );
                    Database.panelManager.useScoreDisplay();
                    Database.levelTimer.start();
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(this, "Replay Not Selected! ", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Back Button
            case 3 -> Database.panelManager.useTitle();
        }
    }
}
