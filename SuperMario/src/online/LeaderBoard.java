package online;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import global.Settings;
import interfaces.list.LeaderBoardEntry;
import interfaces.list.ListPane;
import network.HttpRequest;

public class LeaderBoard {
    /**
     * Fetch Online Leaderboard
     * @param level level ID
     * @return LeaderBoard in JSON Array Format
     */
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

    /**
     * Fetch Leader Board From Server
     * @param level level id
     * @return JScrollPane for LeaderBoard
     */
    public static ListPane<LeaderBoardEntry> fromLevel(int level) {
        // Fetch LeaderBoardInfo
        List<JSONObject> leaderBoardInfo = new ArrayList<>();
        for (Object replayObject : LeaderBoard.fetch(level)) {
            leaderBoardInfo.add((JSONObject) replayObject);
        }

        // Convert Replay File to LeaderBoardEntries
        List<LeaderBoardEntry> entries = leaderBoardInfo.stream()
            .map((JSONObject replay) -> LeaderBoardEntry.fromJson(replay))
            .toList();

        return new ListPane<LeaderBoardEntry>(entries);
    }
}
