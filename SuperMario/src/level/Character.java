package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.json.JSONObject;

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

    private ImageIcon[] icons;

    private String[] keyBind;

    private boolean triggerNextLevel = false;

    private int cachedDeltaX = 0;
    private int cachedDeltaY = 0;
    private boolean cachedJump = false;

    private Collision collision;
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
        this.collision = new Collision(LevelPanel.gameBoard);
        this.physicsProcessors.add(new Gravity(collision));

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

    public void addTrigger(PhysicsProcessor trigger) {
        locationProcessors.add(trigger);
    }
    public void removeTrigger(PhysicsProcessor trigger) {
        locationProcessors.remove(trigger);
    }
    public void clearTrigger() {
        locationProcessors.clear();
    }
    public void defaultTrigger() {
        clearTrigger();
        // Collect Coin
        TriggerAction collectCoin = (int[] coinPos, PhysicsStatus status) -> {
            LevelPanel.gameBoard[coinPos[0]][coinPos[1]].setIcon(null);
            Database.scoreDisplay.incrementScore(1);
            SoundPlayer.play(Sound.coinCollected);
        };
        this.locationProcessors.add(new BlockTrigger(LevelPanel.gameBoard, Icon.COIN, collectCoin));

        // Target Flag
        TriggerAction nextLevel = (int[] flagPos, PhysicsStatus status) -> {
            LevelPanel.renderFrameTimer.stop();
            physicsStatus.reset();
            if (Database.replayRecorder != null) {
                JSONObject replay = ReplayFile.export(
                    LevelPanel.currentLevel, Database.scoreDisplay.getScore(), Database.scoreDisplay.getTime(), 
                    Database.account, Database.replayRecorder
                );
                // Format Current Time
                // https://stackoverflow.com/questions/23068676/how-to-get-current-timestamp-in-string-format-in-java-yyyy-mm-dd-hh-mm-ss
                String fileName = "replay/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".json";
                // Write to Json File
                // https://stackoverflow.com/questions/57913106/append-to-jsonobject-write-object-to-file-using-org-json-for-java
                try {
                    // Create File
                    // https://www.baeldung.com/java-how-to-create-a-file
                    File replayFile = new File(fileName);
                    replayFile.createNewFile();
                    PrintWriter replayFileWriter = new PrintWriter(replayFile, "UTF-8");
                    replayFileWriter.println(replay.toString());
                    replayFileWriter.close();
                    Database.account.submitScore(replay);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Database.replayRecorder.reset();
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
            Database.levelPanel.nextLevel();
            this.triggerNextLevel = false;
        }

        // Write New Caches
        cachedDeltaX = physicsStatus.getDeltaX();
        cachedDeltaY = physicsStatus.getDeltaY();
        cachedJump = physicsStatus.isJumping();
    }
}
