package interfaces.list;

import org.json.JSONObject;

public class ReplayEntry extends LeaderBoardEntry {
    public static LeaderBoardEntry fromJson(JSONObject replayFile) {
        LeaderBoardEntry entry = LeaderBoardEntry.fromJson(replayFile);
        entry.scoreLabel.setText(
            "Level: " 
            + entry.getReplayJSON()
                .getJSONObject("info")
                .getInt("level_id")
        );
        entry.revalidate();
        entry.repaint();
        return entry;
    }
}
