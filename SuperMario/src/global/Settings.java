package global;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Settings {
    public final static int RENDER_FRAME_LIMIT = 60;
    public final static int BLOCK_SIZE = 25;
    public final static int COLLISION_OFFSET_THRESHOLD = 4;
    public static String timerDisplayFormat = "%02d:%02d:%02d";

    public final static String GAME_NAME = "Mario Game";
    public final static Color TEXT_COLOR = new Color(0x000000);

    public static Font titleFontTemplate;
    public static Font buttonFontTemplate;
    // Init Statics with Exception Handle
    // https://howtodoinjava.com/java/exception-handling/checked-exceptions-thrown-in-initializer-blocks-can-be-declared-by-the-constructors/
    static {
        try {
            // Custom Font
            // https://stackoverflow.com/questions/21081586/using-a-custom-font-for-a-jlabel
            titleFontTemplate = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/LOT.otf"));
            buttonFontTemplate = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font/Flynnmono.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public final static Font TITLE_FONT = titleFontTemplate.deriveFont(Font.BOLD, 82.5f);
    public final static Font BUTTON_FONT = buttonFontTemplate.deriveFont(Font.BOLD, 16f);

    public static String[] scoreServerAddr = new String[] {"https", "score.zzny.fun"};
    public static Map<String, String> scoreServerFeature = Map.ofEntries(
        Map.entry("user-login", "/auth/client/login"), 
        Map.entry("user-register", "/auth/user/new"), 
        Map.entry("score-submit", "/client/mario/score/submit"),
        Map.entry("score-fetch", "/client/mario/score/get"), 
        Map.entry("score-leaderboard", "/client/mario/score/leaderboard")
    );
}
