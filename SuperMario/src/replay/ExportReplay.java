package replay;

import org.json.JSONObject;

import online.Account;

public class ExportReplay {
    public static JSONObject export(int levelID, int score, int time, Account player, ReplayRecorder recorder) {
        JSONObject json = new JSONObject();

        // Player Section of the Json
        json.put("player", player.toJSON());

        // Info Section of the Json
        JSONObject infoJson = new JSONObject();
        infoJson.put("level_id", levelID);
        infoJson.put("score", score);
        infoJson.put("time", time);
        json.put("info", infoJson);

        // Replay Section of the Json
        json.put("replay", recorder.toJSON());

        return json;
    }
}
