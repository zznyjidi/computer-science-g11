package level;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
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
        initKeyBind();

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

        revalidate();
        repaint();

        levelPanel = new JPanel();

        loadLevel(level);
        initLevel();
        initKeyBind();

        revalidate();
        repaint();
    }

    private void loadLevel(int level) {
        Scanner fileInput;
        try {
            fileInput = new Scanner(new File("levels/Level" + level + ".txt"));
            for (int row = 0; row < gameBoard.length; row++) {
                char[] lineArray = fileInput.next().toCharArray();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        levelPanel.addKeyListener(this);
        levelPanel.setFocusable(true);
        levelPanel.requestFocusInWindow();
    }

    public static boolean getCollisionX(int[] position, int deltaX) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleRow = indexColOffset != 0;

        // Move Right
        if (deltaX > 0) {
            // At Block Edge & Bottom Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & In the Air & Top Block triggered Collision
            if (inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        // Move Left
        if (deltaX < 0) {
            // At Block Edge & Bottom Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & In the Air & Top Block triggered Collision
            if (inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Bottom Block trigger Collision
            if (indexRowOffset < deltaX && gameBoard[indexPosRow][indexPosCol - 1].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Top Block trigger Collision
            if (indexRowOffset < deltaX && inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol - 1].getIcon() == Icon.WALL)
                return true;
        }
        return false;
    }

    public static boolean getCollisionY(int[] position, int deltaY) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleCol = indexRowOffset != 0;

        // Move Up
        if (deltaY > 0) {
            // At Block Edge & Left Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & Right Block triggered Collision
            if (inMiddleCol && gameBoard[indexPosRow][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Left Block trigger Collision
            if (indexColOffset < deltaY && gameBoard[indexPosRow - 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Right Block trigger Collision
            if (indexColOffset < deltaY && inMiddleCol && gameBoard[indexPosRow - 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        // Move Down
        if (deltaY < 0) {
            // At Block Edge & Left Block triggered Collision
            if (gameBoard[indexPosRow + 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & Right Block triggered Collision
            if (inMiddleCol && gameBoard[indexPosRow + 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        return false;
    }

    public static int[] getTouchedBlockPos(int[] position, ImageIcon block) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleRow = indexColOffset != 0;
        boolean inMiddleCol = indexRowOffset != 0;

        // At Coin Block
        if (gameBoard[indexPosRow][indexPosCol].getIcon() == block)
            return new int[] { indexPosRow, indexPosCol };
        // Reaching from Top
        else if (inMiddleRow && gameBoard[indexPosRow + 1][indexPosCol].getIcon() == block)
            return new int[] { indexPosRow + 1, indexPosCol };
        // Reaching from Left
        else if (inMiddleCol && gameBoard[indexPosRow][indexPosCol + 1].getIcon() == block)
            return new int[] { indexPosRow, indexPosCol + 1 };
        // Reaching from Top Left
        else if (inMiddleRow && inMiddleCol && gameBoard[indexPosRow + 1][indexPosCol + 1].getIcon() == block)
            return new int[] { indexPosRow + 1, indexPosCol + 1 };
        return new int[] { -1, -1 };
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
