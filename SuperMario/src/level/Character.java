package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import global.Database;
import global.Settings;
import sound.Sound;
import sound.SoundPlayer;

public class Character extends JLabel implements ActionListener {

    private ImageIcon[] icons;

    private String[] keyBind;
    private int deltaX;
    private int deltaY;

    private boolean jumping;

    /**
     * Character in Game
     * 
     * @param icons   ImageIcon[] for character sprite
     * @param keyBind keyBind for movements
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
        if (LevelPanel.getCollisionY(getPosition(), -1) && !isJumping()) {
            SoundPlayer.play(Sound.jump);
            jumping = true;
            deltaY = 10;
        }
    }

    public void moveDirection(int directionX) {
        if (directionX > 0)
            setIcon(icons[0]);
        else if (directionX < 0)
            setIcon(icons[2]);
        deltaX = directionX;
    }

    public int[] getPosition() {
        return new int[] { getX(), getY() };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Jumping and Falling
        if (deltaY > 0)
            deltaY--;
        else if (!LevelPanel.getCollisionY(getPosition(), -1))
            deltaY--;

        if (deltaY == 0)
            jumping = false;

        // Collision
        if (LevelPanel.getCollisionX(getPosition(), deltaX)) {
            deltaX = 0;
        }
        if (LevelPanel.getCollisionY(getPosition(), deltaY)) {
            deltaY = 0;
            SoundPlayer.play(Sound.hitGround);
        }

        // Move Character base on delta Value
        setBounds(getX() + deltaX, getY() - deltaY, Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);

        // Collect Coin
        int[] coinPos = LevelPanel.getTouchedBlockPos(getPosition(), Icon.COIN);
        if (coinPos[0] != -1 && coinPos[1] != -1) {
            LevelPanel.gameBoard[coinPos[0]][coinPos[1]].setIcon(null);
            Database.scoreDisplay.incrementScore(1);
            SoundPlayer.play(Sound.coinCollected);
        }

        // Check Flag
        int[] flagPos = LevelPanel.getTouchedBlockPos(getPosition(), Icon.FLAG);
        if (flagPos[0] != -1 && flagPos[1] != -1) {
            System.out.println("FLAG");
        }
    }
}
