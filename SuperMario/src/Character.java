
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Character extends JLabel implements ActionListener {

    private ImageIcon[] icons;

    private String[] keyBind;
    private int deltaX, deltaY;

    private Timer jumpTimer = new Timer(25, this);
    private int jumpCounter;
    private boolean isJumping = false;

    private Timer fallTimer = new Timer(25, this);
    private int fallCounter;
    private boolean isFalling = false;

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

    public void jump() {
        isJumping = true;
        jumpCounter = 0;
        jumpTimer.start();
    }

    public void fall() {
        isFalling = true;
        fallCounter = 0;
        fallTimer.start();
    }

    public void moveDirection(int directionX, int directionY) {
        setBounds(getX() + deltaX*directionX, getY() + deltaY*directionY, 25, 25);
    }

    public int[] getPosition() {
        return new int[] {(int)getX()/25, (int)getY()/25};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}
