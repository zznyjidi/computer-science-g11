package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import global.Database;
import global.Settings;
import physics.Collision;
import physics.Gravity;
import physics.PhysicsProcessor;
import physics.PhysicsStatus;
import sound.Sound;
import sound.SoundPlayer;

public class Character extends JLabel implements ActionListener {

    private ImageIcon[] icons;

    private String[] keyBind;

    private Collision collision;
    private PhysicsStatus physicsStatus = new PhysicsStatus(1, 0, 0, false, getX(), getY());;
    private List<PhysicsProcessor> physicsProcessors = new ArrayList<>();
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
        this.collision = new Collision(LevelPanel.gameBoard);
        this.physicsProcessors.add(new Gravity(collision));
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
        Database.levelPanel.initKeyBind();
    }

    public int getDeltaX() {
        return physicsStatus.getDeltaX();
    }
    public void setDeltaX(int deltaX) {
        physicsStatus.setDeltaX(deltaX);
    }

    public int getDeltaY() {
        return physicsStatus.getDeltaX();
    }
    public void setDeltaY(int deltaY) {
        physicsStatus.setDeltaY(deltaY);
    }

    public boolean isJumping() {
        return physicsStatus.isJumping();
    }
    public void jump() {
        if (collision.getCollisionY(getPosition(), -physicsStatus.getGravityFactor()) && !physicsStatus.isJumping()) {
            SoundPlayer.play(Sound.jump);
            physicsStatus.setJumping(true);
            physicsStatus.setDeltaY(10 * physicsStatus.getGravityFactor());
        }
    }

    public int getGravityFactor() {
        return physicsStatus.getGravityFactor();
    }
    public void setGravityFactor(int gravityFactor) {
        physicsStatus.setGravityFactor(gravityFactor);
    }

    public void moveDirection(int directionX) {
        if (directionX > 0)
            setIcon(icons[0]);
        else if (directionX < 0)
            setIcon(icons[2]);
        physicsStatus.setDeltaX(directionX);
    }

    public int[] getPosition() {
        return new int[] { getX(), getY() };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Process Physics
        for (PhysicsProcessor processor : physicsProcessors) {
            physicsStatus = processor.process(physicsStatus);
        }

        // Move Character base on delta Value
        setBounds(getX() + physicsStatus.getDeltaX(), getY() - physicsStatus.getDeltaY(), Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
        physicsStatus.update(getX(), getY());

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
            physicsStatus.reset();
            Database.levelPanel.nextLevel();
        }
    }
}
