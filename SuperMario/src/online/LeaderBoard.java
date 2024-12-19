package online;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;

import global.Settings;
import network.HttpRequest;

public class LeaderBoard {
    public static JSONArray fetch(int level) {
        // Connect to Server
        JSONArray respondJson = null;
        try {
            URL url = new URI(
                Settings.scoreServerAddr[0],
                Settings.scoreServerAddr[1],
                Settings.scoreServerFeature.get("score-leaderboard"),
                "level=" + level, null).toURL();
            HttpURLConnection connection = HttpRequest.get(url);
            String responds = HttpRequest.getRespond(connection);
            respondJson = new JSONArray(responds);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return respondJson;
    }
}
