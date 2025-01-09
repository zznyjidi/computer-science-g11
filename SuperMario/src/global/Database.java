package global;

import java.util.List;

import javax.swing.Timer;

import hud.ScoreDisplay;
import interfaces.MainFrame;
import interfaces.PanelManager;
import level.LevelPanel;
import online.Account;
import physics.PhysicsStatus;
import replay.ReplayRecorder;

public class Database {
    // Components
    public static Timer levelTimer;
    public static MainFrame mainFrame;
    public static PanelManager panelManager;
    public static ScoreDisplay scoreDisplay;
    public static LevelPanel levelPanel;
    public static ReplayRecorder replayRecorder;

    // Windows Settings
    public static int windowLength = 640;
    public static int windowWidth = 535;

    // Replay Info
    public static boolean replayMode = false;
    public static List<PhysicsStatus> loadedReplay;

    // Account
    public static Account account;
}
