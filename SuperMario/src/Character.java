
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Character extends JLabel implements ActionListener {

    private ImageIcon[] icons;

    private String[] keyBind;
    private int deltaX;
    private int deltaY;

    private boolean jumping;

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

    public boolean isJumping() {
        return jumping;
    }

    public void jump() {
        if (LevelFrame.getCollisionY(getPosition(), -1) && !isJumping()) {
            jumping = true;
            deltaY = 10;
        }
    }

    public void moveDirection(int directionX) {
        deltaX = directionX;
    }

    public int[] getPosition() {
        return new int[] {(int)getX(), (int)getY()};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Jumping and Falling
        if (deltaY > 0)
            deltaY--;
        else if (!LevelFrame.getCollisionY(getPosition(), -1))
            deltaY--;
        
        if (deltaY == 0)
            jumping = false;
        
        // Collision
        if (LevelFrame.getCollisionX(getPosition(), deltaX)) 
            deltaX = 0;
        if (LevelFrame.getCollisionY(getPosition(), deltaY)) {
            System.out.println("Y_COLLISION " + deltaY);
            deltaY = 0;
        }

        // Move Character base on delta Value
        setBounds(getX() + deltaX, getY() - deltaY, Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
    }
}
