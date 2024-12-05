
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class LevelFrame extends JFrame implements KeyListener {

    public static JLabel[][] gameBoard = new JLabel[20][25];
    public static JPanel levelPanel = new JPanel();
    public static Character character = new Character(Icon.characterIcon, new String[] {"a", "d", " "});

    public static Timer renderFrameTimer;

    public LevelFrame(int level) {
        setSize(Settings.BLOCK_SIZE*gameBoard[0].length + 15, Settings.BLOCK_SIZE*gameBoard.length + 35);

        setLayout(null);
        //setResizable(false);

        loadLevel(level);
        initLevel();

        initKeyBind();

        setVisible(true);

        renderFrameTimer = new Timer((1000/Settings.RENDER_FRAME_LIMIT), character);
        renderFrameTimer.addActionListener(character);
        renderFrameTimer.start();
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
                        default -> new JLabel();
                    };
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initLevel() {
        levelPanel.setLayout(null);
        levelPanel.setBackground(Color.WHITE);

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                JLabel icon = gameBoard[row][col];
                if (icon.getIcon() == Icon.WALL || icon.getIcon() == Icon.COIN) {
                    icon.setBounds(col*Settings.BLOCK_SIZE, row*Settings.BLOCK_SIZE, Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
                    levelPanel.add(icon);
                }
            }
        }

        character.setBounds(25, 425, Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
        levelPanel.add(character);
        levelPanel.setBounds(0, 0, Settings.BLOCK_SIZE*gameBoard[0].length, Settings.BLOCK_SIZE*gameBoard.length);
        add(levelPanel);
    }

    private void initKeyBind() {
        ActionMap actionMap;
        InputMap inputMap;

        inputMap = levelPanel.getInputMap();
        actionMap = levelPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[0].toCharArray()[0]), "MOVE_LEFT");
        actionMap.put("MOVE_LEFT", new KeyAction("MOVE_LEFT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[1].toCharArray()[0]), "MOVE_RIGHT");
        actionMap.put("MOVE_RIGHT", new KeyAction("MOVE_RIGHT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[2].toCharArray()[0]), "MOVE_JUMP");
        actionMap.put("MOVE_JUMP", new KeyAction("MOVE_JUMP"));

        levelPanel.addKeyListener(this);
    }

    public static boolean getCollisionX(int[] position, double deltaX) {
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

    public static boolean getCollisionY(int[] position, double deltaY) {
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

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        // Set Velocity to 0
        if (e.getKeyChar() == 'a' && character.getDeltaX() < 0)
            character.setDeltaX(0);
        if (e.getKeyChar() == 'd' && character.getDeltaX() > 0)
            character.setDeltaX(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
