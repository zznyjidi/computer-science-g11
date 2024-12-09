
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    AudioInputStream stream;
    Clip player;

    List<File> audioFile = new ArrayList<>();

    public SoundPlayer(File defaultSound) {
        audioFile.add(defaultSound);
        try {
            stream = AudioSystem.getAudioInputStream(defaultSound);
            player = AudioSystem.getClip();
            player.open(stream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        player.start();
    }
}
