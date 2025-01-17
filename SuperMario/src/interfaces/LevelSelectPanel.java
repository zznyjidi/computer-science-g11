package interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import interfaces.list.LeaderBoardEntry;
import interfaces.list.LevelEntry;
import interfaces.list.ListActionListener;
import interfaces.list.ListPane;
import online.LeaderBoard;

public class LevelSelectPanel extends JPanel implements ListActionListener {
    ListPane<LevelEntry> levelList;
    ListPane<LeaderBoardEntry> leaderBoard;

    public LevelSelectPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints componentInfo = new GridBagConstraints();
        componentInfo.fill = GridBagConstraints.VERTICAL;
        componentInfo.insets = new Insets(10, 15, 10, 15);
        componentInfo.weightx = 1;
        componentInfo.weighty = 1;

        levelList = LevelEntry.fromDir();
        levelList.setMinimumSize(new Dimension(200, 400));
        levelList.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
        componentInfo.gridx = 0;
        componentInfo.gridy = 0;
        add(levelList, componentInfo);

        leaderBoard = LeaderBoard.fromLevel(1);
        leaderBoard.setMinimumSize(new Dimension(400, 400));
        leaderBoard.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        componentInfo.gridx = 1;
        componentInfo.gridy = 0;
        add(leaderBoard, componentInfo);

        levelList.addActionListener(this);

        revalidate();
        repaint();
    }

    @Override
    public void selectChanged(int index, JPanel newEntry) {
        LevelEntry selectedEntry = (LevelEntry) newEntry;
        leaderBoard = LeaderBoard.fromLevel(selectedEntry.getLevelID());
        revalidate();
        repaint();
    }

}
