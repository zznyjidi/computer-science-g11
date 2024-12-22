package global;

import java.awt.Color;
import java.util.Map;

public class Settings {
    public final static int RENDER_FRAME_LIMIT = 60;
    public final static int BLOCK_SIZE = 25;
    public final static int COLLISION_OFFSET_THRESHOLD = 4;
    public static String timerDisplayFormat = "%02d:%02d:%02d";

    public final static String GAME_NAME = "Mario Game";
    public final static Color TEXT_COLOR = new Color(0x000000);

    public static String[] scoreServerAddr = new String[] {"https", "score.zzny.fun"};
    public static Map<String, String> scoreServerFeature = Map.ofEntries(
        Map.entry("user-login", "/auth/client/login"), 
        Map.entry("user-register", "/auth/user/new"), 
        Map.entry("score-submit", "/client/mario/score/submit"),
        Map.entry("score-fetch", "/client/mario/score/get"), 
        Map.entry("score-leaderboard", "/client/mario/score/leaderboard")
    );
}
