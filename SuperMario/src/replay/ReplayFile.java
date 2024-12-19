package replay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import online.Account;
import physics.PhysicsStatus;

public class ReplayFile {
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

    public static JSONObject load(File replayJson) throws FileNotFoundException {
        // Read File with String Builder
        StringBuilder fileContent = new StringBuilder();
        try (Scanner file = new Scanner(replayJson)) {
            for (;file.hasNextLine(); fileContent.append(file.nextLine() + "\n"));
        }
        // Convert File to Json
        return new JSONObject(fileContent.toString());
    }

    public static List<PhysicsStatus> load_frame(File replayJson) throws FileNotFoundException {
        JSONArray frameArray = load(replayJson).getJSONArray("replay");
        // Create Frame List
        List<PhysicsStatus> frames = new ArrayList<>();
        for (Object frame : frameArray) {
            JSONObject currentFrame = (JSONObject)frame;
            frames.add(new PhysicsStatus(
                currentFrame.getInt("gF"), 
                currentFrame.getInt("dX"), currentFrame.getInt("dY"), 
                currentFrame.getBoolean("j"), 
                currentFrame.getInt("pX"), currentFrame.getInt("pY")
            ));
        }
        return frames;
    }
}
