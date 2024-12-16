package replay;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExportReplay {
    static JSONObject export(int levelID, int score, int time, JSONArray replayFrames) {
        JSONObject json = new JSONObject();

        // Player Section of the Json
        JSONObject playerJson = new JSONObject();
        json.put("player", playerJson);

        // Info Section of the Json
        JSONObject infoJson = new JSONObject();
        infoJson.put("level_id", levelID);
        infoJson.put("score", score);
        infoJson.put("time", time);
        json.put("info", infoJson);

        // Replay Section of the Json
        json.put("replay", replayFrames);

        return json;
    }
}
