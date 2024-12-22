package replay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import global.Database;
import level.LevelPanel;
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

    public static void exportFileDefault() {
        JSONObject replay = ReplayFile.export(
            LevelPanel.currentLevel, Database.scoreDisplay.getScore(), Database.scoreDisplay.getTime(), 
            Database.account, Database.replayRecorder
        );
        // Format Current Time
        // https://stackoverflow.com/questions/23068676/how-to-get-current-timestamp-in-string-format-in-java-yyyy-mm-dd-hh-mm-ss
        String fileName = "replay/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".json";
        // Write to Json File
        // https://stackoverflow.com/questions/57913106/append-to-jsonobject-write-object-to-file-using-org-json-for-java
        try {
            // Create File
            // https://www.baeldung.com/java-how-to-create-a-file
            File replayFile = new File(fileName);
            replayFile.createNewFile();
            PrintWriter replayFileWriter = new PrintWriter(replayFile, "UTF-8");
            replayFileWriter.println(replay.toString());
            replayFileWriter.close();
            Database.account.submitScore(replay);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database.replayRecorder.reset();
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
