package replay;

import java.util.List;

import physics.PhysicsProcessor;
import physics.PhysicsStatus;

public class ReplayPlayer implements PhysicsProcessor {

    private List<PhysicsStatus> loadedReplay;
    private int frameCounter;

    public void loadReplay(List<PhysicsStatus> replayFrameList) {
        this.loadedReplay = replayFrameList;
        this.frameCounter = 0;
    }

    public void restartReplay() {
        this.frameCounter = 0;
    }

    public void movePlayHead(int frame) {
        this.frameCounter += frame;
    }

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        frameCounter++;
        return getValidFrame().copy();
    }

    private PhysicsStatus getValidFrame() {
        if (frameCounter >= loadedReplay.size()) {
            frameCounter = loadedReplay.size() - 1;
            return loadedReplay.getLast();
        }
        if (frameCounter < 0) {
            frameCounter = 0;
            return loadedReplay.getFirst();
        }
        return loadedReplay.get(frameCounter);
    }
}
