
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Character extends JLabel implements ActionListener {

    private ImageIcon[] icons;

    private String[] keyBind;
    private int deltaX = 10;
    private int deltaY = 10;

    private Timer jumpTimer = new Timer(25, this);
    private int jumpCounter;
    private boolean jumping = false;

    private Timer fallTimer = new Timer(25, this);
    private int fallCounter;
    private boolean falling = false;

    /**
     * Character in Game
     * @param icons ImageIcon[] for character sprite
     * @param keyBind
     */
    public Character(ImageIcon[] icons, String[] keyBind) {
        super();
        this.icons = icons;
        this.keyBind = keyBind;
        setIcon(icons[0]);

        jumpTimer.addActionListener(this);
        fallTimer.addActionListener(this);
    }

    // Getter & Setters
    public ImageIcon[] getIcons() {
        return icons;
    }
    public void setIcons(ImageIcon[] icons) {
        this.icons = icons;
    }
    public String[] getKeyBind() {
        return keyBind;
    }
    public void setKeyBind(String[] keyBind) {
        this.keyBind = keyBind;
    }
    public int getDeltaX() {
        return deltaX;
    }
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }
    public int getDeltaY() {
        return deltaY;
    }
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void jump() {
        jumping = true;
        jumpCounter = 0;
        jumpTimer.start();
    }

    public void fall() {
        falling = true;
        fallCounter = 0;
        fallTimer.start();
    }

    public void moveDirection(int directionX, int directionY) {
        setBounds(getX() + deltaX*directionX, getY() + deltaY*directionY, 25, 25);
    }

    public int[] getPosition() {
        return new int[] {(int)getX()/25, (int)getY()/25};
    }

    public void collectCoin() {
        int row = getPosition()[1];
        int col = getPosition()[0];

        if (LevelFrame.gameBoard[row][col].getIcon() == Icon.COIN) {
            LevelFrame.gameBoard[row][col].setIcon(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jumping && deltaY < 0 && LevelFrame.gameBoard[getPosition()[1] - 1][getPosition()[0]].getIcon() == Icon.WALL) {
            jumping = false;
            deltaY = 0;
            jumpTimer.stop();
            fall();
        } 
        if (falling && deltaY > 0) {
            int nextRow = getPosition()[1] + 1;
            if (nextRow >= LevelFrame.gameBoard.length || LevelFrame.gameBoard[nextRow][getPosition()[0]].getIcon() == Icon.WALL) {
                falling = false;
                deltaY = 0;
                fallTimer.stop();
            }
        }
        if (jumping) {
            jumpCounter++;
            if (jumpCounter <= 10) 
                deltaY = -5;
            else if (jumpCounter <= 20)
                deltaY = 5;
            else {
                jumping = false;
                deltaY = 0;
                jumpTimer.stop();
                fall();
            }
        }
        else if (falling) {
            fallCounter++;
            deltaY = 5;
            setBounds(getX(), getY() - deltaY, 25, 25);

            if (LevelFrame.gameBoard[getPosition()[1] + 1][getPosition()[0]].getIcon() == Icon.WALL) {
                falling = false;
                deltaY = 0;
                fallTimer.stop();
            }
            else if (fallCounter >= 20) {
                falling = false;
                deltaY = 0;
                fallTimer.stop();
            }
        }

        collectCoin();

        if (LevelFrame.gameBoard[getPosition()[1] + 1][getPosition()[0]].getIcon() == Icon.WALL) 
            deltaX = 0;
        else if (LevelFrame.gameBoard[getPosition()[1]][getPosition()[0]].getIcon() == Icon.WALL && deltaX > 0) 
            deltaX = 0;
        else if (LevelFrame.gameBoard[getPosition()[1]][getPosition()[0]].getIcon() == Icon.WALL && deltaY < 0)
            deltaY = 0;
        else if (LevelFrame.gameBoard[getPosition()[1]][getPosition()[0]].getIcon() == Icon.WALL && deltaY < 0)
            deltaY = 0;
    }
    
}
