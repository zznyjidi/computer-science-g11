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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONObject;

import global.Database;
import level.LevelPanel;
import online.Account;
import physics.PhysicsStatus;

public class ReplayFile {

    /**
     * Export Replay JSON File
     * @param levelID Level ID
     * @param score Score in the Level
     * @param time time spend (in 10ms)
     * @param player player's account object
     * @param recorder recorder that holds the list of frame
     * @return replay file JSON Object
     */
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

    /**
     * Export to JSON File with default parameters and reset recorder
     * - Level & Score Info from current Level
     * - Account Object from Database
     * - To replay folder
     * - Current time as file name
     */
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
            // Submit Score to Server
            Database.account.submitScore(replay);
            // Create File
            // https://www.baeldung.com/java-how-to-create-a-file
            File replayFile = new File(fileName);
            replayFile.createNewFile();
            PrintWriter replayFileWriter = new PrintWriter(replayFile, "UTF-8");
            replayFileWriter.println(replay.toString());
            replayFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database.replayRecorder.reset();
    }

    /**
     * Turn a Replay's Frame List to List of PhysicsStatus
     * @param frameJsonArray replay's 'replay' section
     * @return list of physics status from the replay file
     */
    public static List<PhysicsStatus> extractFrames(JSONArray frameJsonArray) {
        // Create Frame List
        List<PhysicsStatus> frames = new ArrayList<>();
        for (Object frame : frameJsonArray) {
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

    /**
     * Prompt User to choose a file
     * @return File that the user choose
     * @throws FileNotFoundException raised when return value from JFileChooser is not approved
     */
    public static File chooseReplayFile() throws FileNotFoundException {
        // Use JFileChooser to get a File
        // https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Replay File (json)", "json");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            throw new FileNotFoundException();
        }
    }

    /**
     * Load file to JSON Object
     * @param replayJson replay file
     * @return json object
     * @throws FileNotFoundException raised when the file is not found
     */
    public static JSONObject load(File replayJson) throws FileNotFoundException {
        // Read File with String Builder
        StringBuilder fileContent = new StringBuilder();
        try (Scanner file = new Scanner(replayJson)) {
            for (;file.hasNextLine(); fileContent.append(file.nextLine() + "\n"));
        }
        // Convert File to Json
        return new JSONObject(fileContent.toString());
    }

    /**
     * Load frames from json file
     * @param replayJson replay file
     * @return json object
     * @throws FileNotFoundException raised when the file is not found
     */
    public static List<PhysicsStatus> load_frame(File replayJson) throws FileNotFoundException {
        JSONArray frameArray = load(replayJson).getJSONArray("replay");
        return extractFrames(frameArray);
    }
}
