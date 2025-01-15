package replay;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import physics.PhysicsStatus;

public class ReplayRecorder implements physics.PhysicsProcessor {

    // Use Linked List to avoid frequent memory reallocate
    List<PhysicsStatus> frames = new LinkedList<>();

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        frames.add(currentStatus.copy());
        return currentStatus;
    }

    /**
     * Export to JSON Array for "replay" section for the replay file
     * @return json array
     */
    public JSONArray toJSON() {
        JSONArray json = new JSONArray();
        for (PhysicsStatus physicsStatus : frames) {
            JSONObject jsonFrame = new JSONObject();
            jsonFrame.put("dX", physicsStatus.getDeltaX());
            jsonFrame.put("dY", physicsStatus.getDeltaY());
            jsonFrame.put("gF", physicsStatus.getGravityFactor());
            jsonFrame.put("j", physicsStatus.isJumping());
            jsonFrame.put("pX", physicsStatus.getLocationX());
            jsonFrame.put("pY", physicsStatus.getLocationY());
            json.put(jsonFrame);
        }
        return json;
    }

    /**
     * Clear all recorded frames
     */
    public void reset() {
        frames.clear();
    }

}
