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
import replay.ReplayPlayer;

public class LevelPanel extends JPanel implements KeyListener {

    // Components
    public static JLabel[][] gameBoard = new JLabel[20][25];
    public static JPanel levelPanel = new JPanel();
    public static Character character = new Character(Icon.characterIcon, new String[] { "a", "d", " " });
    public static int[] startingPoint;
    public static int currentLevel;

    // Render
    public static Timer renderFrameTimer;

    // Replay
    private ReplayPlayer replayPlayer = new ReplayPlayer();

    public LevelPanel(int level) {
        setLayout(null);

        newLevel(level);

        Database.windowLength = Settings.BLOCK_SIZE * LevelPanel.gameBoard[0].length + 15;
        Database.windowWidth = Settings.BLOCK_SIZE * LevelPanel.gameBoard.length + 35;
        setBounds(0, 0, Database.windowLength, Database.windowWidth);

        renderFrameTimer = new Timer((1000 / Settings.RENDER_FRAME_LIMIT), character);
        renderFrameTimer.start();
    }

    public LevelPanel() {
        setLayout(null);

        loadLevel(1);
        Database.windowLength = Settings.BLOCK_SIZE * LevelPanel.gameBoard[0].length + 15;
        Database.windowWidth = Settings.BLOCK_SIZE * LevelPanel.gameBoard.length + 35;
        setBounds(0, 0, Database.windowLength, Database.windowWidth);

        renderFrameTimer = new Timer((1000 / Settings.RENDER_FRAME_LIMIT), character);
    }

    public void nextLevel() {
        newLevel(currentLevel + 1);
    }

    /**
     * Create Level From File in levels named Level{x}.txt
     * @param level x(Level ID)
     */
    public void newLevel(int level) {
        remove(levelPanel);
        levelPanel.removeKeyListener(this);

        character.defaultTrigger();
        levelPanel = new JPanel();

        loadLevel(level);
        initLevel();
        if (!Database.replayMode) {
            character.defaultPhysics();
            initKeyBind();
            if (Database.replayRecorder != null)
                character.addProcessor(Database.replayRecorder, Character.TRIGGER_QUEUE);
        } else {
            replayPlayer.loadReplay(Database.loadedReplay);
            character.addProcessor(replayPlayer, Character.PHYSICS_QUEUE);
        }

        revalidate();
        repaint();

        if (!renderFrameTimer.isRunning())
            renderFrameTimer.start();
    }

    /**
     * Load Level From File to gameBoard
     * @param level Level ID
     */
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
                            case 'U' -> new JLabel(Icon.FLIP);
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

    /**
     * Draw Level from gameBoard
     */
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

    /**
     * Setup Key Binds from character's KeyBind Array
     */
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
