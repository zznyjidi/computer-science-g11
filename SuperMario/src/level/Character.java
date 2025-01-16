package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import global.Database;
import global.Settings;
import physics.BlockTrigger;
import physics.Collision;
import physics.Gravity;
import physics.PhysicsProcessor;
import physics.PhysicsStatus;
import physics.TriggerAction;
import replay.ReplayFile;
import sound.Sound;
import sound.SoundPlayer;

public class Character extends JLabel implements ActionListener {
    // Class Constants
    public static final int PHYSICS_QUEUE = 0;
    public static final int TRIGGER_QUEUE = 1;

    // Felids
    private ImageIcon[] icons;

    private String[] keyBind;

    private boolean triggerNextLevel = false;

    private int cachedDeltaX = 0;
    private int cachedDeltaY = 0;
    private boolean cachedJump = false;

    private Collision collision = new Collision(LevelPanel.gameBoard);
    private Gravity gravity = new Gravity(collision);
    private PhysicsStatus physicsStatus = new PhysicsStatus(1, 0, 0, false, getX(), getY());;
    private List<PhysicsProcessor> physicsProcessors = new ArrayList<>();
    private List<PhysicsProcessor> locationProcessors = new ArrayList<>();
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

        defaultPhysics();
        defaultTrigger();
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
        cachedDeltaX = deltaX;
        physicsStatus.setDeltaX(deltaX);
    }

    public int getDeltaY() {
        return physicsStatus.getDeltaX();
    }
    public void setDeltaY(int deltaY) {
        cachedDeltaY = deltaY;
        physicsStatus.setDeltaY(deltaY);
    }

    public boolean isJumping() {
        return physicsStatus.isJumping();
    }
    public void jump() {
        if (collision.getCollisionY(getPosition(), -physicsStatus.getGravityFactor()) && !physicsStatus.isJumping()) {
            SoundPlayer.play(Sound.jump);
            cachedJump = true;
            cachedDeltaY = 10 * physicsStatus.getGravityFactor();
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
        cachedDeltaX = directionX;
    }

    public int[] getPosition() {
        return new int[] { getX(), getY() };
    }

    private List<PhysicsProcessor> getProcessorQueue(int queue) {
        return switch (queue) {
            case 0 -> physicsProcessors;
            case 1 -> locationProcessors;
            default -> physicsProcessors;
        };
    }

    // Trigger & Physics Management
    public void addProcessor(PhysicsProcessor processor, int queue) {
        getProcessorQueue(queue).add(processor);
    }
    public void removeProcessor(PhysicsProcessor processor, int queue) {
        getProcessorQueue(queue).remove(processor);
    }
    public void clearProcessor(int queue) {
        getProcessorQueue(queue).clear();
    }

    public void defaultPhysics() {
        clearProcessor(PHYSICS_QUEUE);
        // Gravity
        this.physicsProcessors.add(gravity);
    }
    public void defaultTrigger() {
        clearProcessor(TRIGGER_QUEUE);
        // Collect Coin
        TriggerAction collectCoin = (int[] coinPos, PhysicsStatus status) -> {
            LevelPanel.gameBoard[coinPos[0]][coinPos[1]].setIcon(null);
            Database.scoreDisplay.incrementScore(1);
            SoundPlayer.play(Sound.coinCollected);
        };
        this.locationProcessors.add(new BlockTrigger(LevelPanel.gameBoard, Icon.COIN, collectCoin));

        // Gravity Flip
        TriggerAction reverseGravity = (int[] cloudPos, PhysicsStatus status) -> {
            status.setGravityFactor(-status.getGravityFactor());
        };
        this.locationProcessors.add(new BlockTrigger(LevelPanel.gameBoard, Icon.FLIP, reverseGravity));

        // Target Flag
        TriggerAction nextLevel = (int[] flagPos, PhysicsStatus status) -> {
            LevelPanel.renderFrameTimer.stop();
            physicsStatus.reset();
            if (Database.replayRecorder != null && !Database.replayMode) {
                ReplayFile.exportFileDefault();
            }
            Database.scoreDisplay.reset();
            this.triggerNextLevel = true;
            LevelPanel.renderFrameTimer.start();
        };
        this.locationProcessors.add(new BlockTrigger(LevelPanel.gameBoard, Icon.FLAG, nextLevel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Process Cached Data
        physicsStatus.setDeltaX(cachedDeltaX);
        physicsStatus.setDeltaY(cachedDeltaY);
        physicsStatus.setJumping(cachedJump);

        // Process Physics
        for (PhysicsProcessor processor : physicsProcessors) {
            physicsStatus = processor.process(physicsStatus);
        }

        // Move Character base on delta Value
        setBounds(getX() + physicsStatus.getDeltaX(), getY() - physicsStatus.getDeltaY(), Settings.BLOCK_SIZE, Settings.BLOCK_SIZE);
        physicsStatus.update(getX(), getY());

        // Process Triggers
        for (PhysicsProcessor trigger : locationProcessors) {
            physicsStatus = trigger.process(physicsStatus);
        }

        // Trigger Next Level if needed
        // bugfix: java.util.ConcurrentModificationException
        if (this.triggerNextLevel) {
            LevelPanel.renderFrameTimer.stop();
            if (Database.replayMode) {
                Database.replayMode = false;
            }
            Database.levelPanel.nextLevel();
            this.triggerNextLevel = false;
        }

        // Write New Caches
        cachedDeltaX = physicsStatus.getDeltaX();
        cachedDeltaY = physicsStatus.getDeltaY();
        cachedJump = physicsStatus.isJumping();
    }
}
