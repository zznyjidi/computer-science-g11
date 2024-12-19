package global;

import java.util.List;

import javax.swing.Timer;

import hud.ScoreDisplay;
import level.LevelPanel;
import online.Account;
import physics.PhysicsStatus;
import replay.ReplayRecorder;

public class Database {
    public static Timer levelTimer;
    public static ScoreDisplay scoreDisplay;
    public static LevelPanel levelPanel;
    public static ReplayRecorder replayRecorder;

    public static int windowWidth;
    public static int windowLength;

    public static boolean replayMode = false;
    public static List<PhysicsStatus> loadedReplay;

    public static Account account;
}
