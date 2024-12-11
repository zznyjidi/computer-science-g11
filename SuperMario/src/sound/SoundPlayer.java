package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

    public static void play(File sound) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(sound);
            Clip audioPlayer = AudioSystem.getClip();
            audioPlayer.open(stream);

            // Close Clip and Stream
            // https://stackoverflow.com/questions/69184459/reusing-an-audioinputstream
            audioPlayer.addLineListener(event -> {
                if (event.getType().equals(LineEvent.Type.STOP)) {
                    audioPlayer.close();
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            audioPlayer.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
