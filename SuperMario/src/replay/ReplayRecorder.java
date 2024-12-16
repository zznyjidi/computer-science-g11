package replay;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import physics.PhysicsStatus;

public class ReplayRecorder implements physics.PhysicsProcessor {

    List<PhysicsStatus> frames = new LinkedList<>();

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        frames.add(currentStatus.copy());
        return currentStatus;
    }

    public JSONArray toJSON() {
        JSONArray json = new JSONArray();
        for (PhysicsStatus physicsStatus : frames) {
            JSONObject jsonFrame = new JSONObject();
            jsonFrame.append("deltaX", physicsStatus.getDeltaX());
            jsonFrame.append("deltaY", physicsStatus.getDeltaY());
            jsonFrame.append("gravityFactor", physicsStatus.getGravityFactor());
            jsonFrame.append("jumping", physicsStatus.isJumping());
            jsonFrame.append("posX", physicsStatus.getLocationX());
            jsonFrame.append("posY", physicsStatus.getLocationY());
            json.put(jsonFrame);
        }
        return json;
    }

}
