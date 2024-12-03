
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

public class LevelFrame extends JFrame implements KeyListener {

    public static JLabel[][] gameBoard = new JLabel[20][25];
    public static JPanel level1Panel = new JPanel();
    public static Character character = new Character(Icon.characterIcon, new String[] {"a", "d", " "});

    public LevelFrame(int level) {
        setSize(25*gameBoard[0].length + 15, 25*gameBoard.length + 35);

        setLayout(null);
        //setResizable(false);

        loadLevel(level);
        initLevel();

        initKeyBind();

        setVisible(true);
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
        level1Panel.setLayout(null);
        level1Panel.setBackground(Color.WHITE);

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                JLabel icon = gameBoard[row][col];
                if (icon.getIcon() == Icon.WALL || icon.getIcon() == Icon.COIN) {
                    icon.setBounds(col*25, row*25, 25, 25);
                    level1Panel.add(icon);
                }
            }
        }

        character.setBounds(25, 425, 25, 25);
        level1Panel.add(character);
        level1Panel.setBounds(0, 0, 25*gameBoard[0].length, 25*gameBoard.length);
        add(level1Panel);
    }

    private void initKeyBind() {
        ActionMap actionMap;
        InputMap inputMap;

        inputMap = level1Panel.getInputMap();
        actionMap = level1Panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[0].toCharArray()[0]), "MOVE_LEFT");
        actionMap.put("MOVE_LEFT", new KeyAction("MOVE_LEFT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[1].toCharArray()[0]), "MOVE_RIGHT");
        actionMap.put("MOVE_RIGHT", new KeyAction("MOVE_RIGHT"));
        inputMap.put(KeyStroke.getKeyStroke(character.getKeyBind()[1].toCharArray()[0]), "MOVE_JUMP");
        actionMap.put("MOVE_JUMP", new KeyAction("MOVE_JUMP"));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Set Velocity to 0
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            character.setDeltaX(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}
