package level;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import global.Database;
import global.Settings;

public class LevelPanel extends JPanel implements KeyListener {

    public static JLabel[][] gameBoard = new JLabel[20][25];
    public static JPanel levelPanel = new JPanel();
    public static Character character = new Character(Icon.characterIcon, new String[] { "a", "d", " " });
    public static int[] startingPoint;
    public static int currentLevel;

    public static Timer renderFrameTimer;

    public LevelPanel(int level) {

        setLayout(null);

        loadLevel(level);
        initLevel();
        if (!Database.replayMode) {
            initKeyBind();
            if (Database.replayRecorder != null)
                character.addTrigger(Database.replayRecorder);
        }

        Database.windowLength = Settings.BLOCK_SIZE * LevelPanel.gameBoard[0].length + 15;
        Database.windowWidth = Settings.BLOCK_SIZE * LevelPanel.gameBoard.length + 35;

        setBounds(0, 0, Database.windowLength, Database.windowWidth);

        renderFrameTimer = new Timer((1000 / Settings.RENDER_FRAME_LIMIT), character);
        renderFrameTimer.addActionListener(character);
        renderFrameTimer.start();
    }

    public void nextLevel() {
        newLevel(currentLevel + 1);
    }

    public void newLevel(int level) {
        remove(levelPanel);
        levelPanel.removeKeyListener(this);

        character.defaultTrigger();
        levelPanel = new JPanel();

        loadLevel(level);
        initLevel();
        if (!Database.replayMode) {
            initKeyBind();
            if (Database.replayRecorder != null)
                character.addTrigger(Database.replayRecorder);
        }

        revalidate();
        repaint();
    }

    private void loadLevel(int level) {
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File("levels/Level" + level + ".txt"));
            // Read until end of file
            // https://dev.to/ezzeddinp/read-input-until-eof-end-of-file-and-number-your-lines-effortlessly-competitive-programming-java-1egb
            for (int row = 0; fileInput.hasNext(); row++) {
                String line = fileInput.nextLine();
                if (line.startsWith("#")) {
                    row--;
                } else {
                    char[] lineArray = line.toCharArray();
                    for (int col = 0; col < gameBoard[0].length; col++) {
                        gameBoard[row][col] = switch (lineArray[col]) {
                            case 'B' -> new JLabel(Icon.WALL);
                            case 'C' -> new JLabel(Icon.COIN);
                            case 'F' -> new JLabel(Icon.FLAG);
                            case 'S' -> {
                                startingPoint = new int[] {row, col}; 
                                yield new JLabel();
                            }
                            default -> new JLabel();
                        };
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInput != null)
                fileInput.close();
        }
        currentLevel = level;
    }

    private void initLevel() {
        levelPanel.setLayout(null);
        levelPanel.setBackground(Color.WHITE);

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                JLabel icon = gameBoard[row][col];
                if (icon.getIcon() != null) {
                    icon.setBounds(
                        col * Settings.BLOCK_SIZE, row * Settings.BLOCK_SIZE,
                        Settings.BLOCK_SIZE, Settings.BLOCK_SIZE
                    );
                    levelPanel.add(icon);
                }
            }
        }

        character.setBounds(
            startingPoint[1] * Settings.BLOCK_SIZE, startingPoint[0] * Settings.BLOCK_SIZE, 
            Settings.BLOCK_SIZE, Settings.BLOCK_SIZE
        );
        levelPanel.add(character);
        levelPanel.setBounds(0, 0, Settings.BLOCK_SIZE * gameBoard[0].length, Settings.BLOCK_SIZE * gameBoard.length);
        add(levelPanel);
    }

    public void initKeyBind() {
        InputMap inputMap = levelPanel.getInputMap();
        ActionMap actionMap = levelPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[0].toCharArray()[0]), "MOVE_LEFT");
        actionMap.put("MOVE_LEFT", new KeyAction("MOVE_LEFT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[1].toCharArray()[0]), "MOVE_RIGHT");
        actionMap.put("MOVE_RIGHT", new KeyAction("MOVE_RIGHT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[2].toCharArray()[0]), "MOVE_JUMP");
        actionMap.put("MOVE_JUMP", new KeyAction("MOVE_JUMP"));

        // bugfix: key bind not working
        // https://stackoverflow.com/questions/16530775/keylistener-not-working-for-jpanel
        levelPanel.addKeyListener(this);
        levelPanel.setFocusable(true);
        levelPanel.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Set Velocity to 0
        if (e.getKeyChar() == 'a' && character.getDeltaX() < 0)
            character.setDeltaX(0);
        if (e.getKeyChar() == 'd' && character.getDeltaX() > 0)
            character.setDeltaX(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
