package global;

import javax.swing.Timer;

import hud.ScoreDisplay;
import level.LevelPanel;
import online.Account;
import replay.ReplayRecorder;

public class Database {
    public static Timer levelTimer;
    public static ScoreDisplay scoreDisplay;
    public static LevelPanel levelPanel;
    public static ReplayRecorder replayRecorder;

    public static int windowWidth;
    public static int windowLength;

    public static boolean replayMode = false;

    public static Account account;
}
