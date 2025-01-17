package interfaces.list;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Settings;

public class LevelEntry extends JPanel {
    private int levelID;

    private JLabel levelNameLabel;

    public LevelEntry(int levelID) {
        super();
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints componentInfo = new GridBagConstraints();
        componentInfo.insets = new Insets(10, 15, 10, 15);
        componentInfo.weightx = 1;
        componentInfo.weighty = 1;

        this.levelID = levelID;

        levelNameLabel = new JLabel("Level " +  levelID);
        levelNameLabel.setFont(new Font("Consolas", Font.BOLD, 34));
        componentInfo.gridx = 0;
        componentInfo.gridy = 0;
        componentInfo.anchor = GridBagConstraints.LINE_START;
        add(levelNameLabel, componentInfo);
    }

    public int getLevelID() {
        return levelID;
    }

    public static ListPane<LevelEntry> fromDir() {
        // List File in Dir
        // https://www.baeldung.com/java-list-directory-files
        File[] levelFiles = new File("levels").listFiles();

        // Read Replay Files
        List<LevelEntry> levelEntries = Stream.of(levelFiles)
                .filter(file -> !file.isDirectory())
                // Match With Regex
                // https://www.w3schools.com/java/java_regex.asp
                .filter(file -> {
                    Matcher matcher = Settings.LEVEL_FILE_PATTERN.matcher(file.getName());
                    return matcher.find();
                })
                // Extract Data With Regex
                // https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex
                .map(file -> {
                    Matcher matcher = Settings.LEVEL_FILE_PATTERN.matcher(file.getName());
                    matcher.find();
                    return new LevelEntry(Integer.parseInt(matcher.group(1)));
                })
                .toList();
        return new ListPane<>(levelEntries, new Dimension(300, 55), new Dimension(100, 55));
    }

    // Draw Rounded Boarder
    // https://stackoverflow.com/questions/15025092/border-with-rounded-corners-transparency
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(30, 30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint Background
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        // Paint Boarder
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }
}
