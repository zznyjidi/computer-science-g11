package replay;

import java.util.List;

import physics.PhysicsProcessor;
import physics.PhysicsStatus;

public class ReplayPlayer implements PhysicsProcessor {

    // Fields
    private List<PhysicsStatus> loadedReplay;
    private int frameCounter;

    /**
     * Load Frames from list of PhysicsStatus
     * @param replayFrameList list of frame
     */
    public void loadReplay(List<PhysicsStatus> replayFrameList) {
        this.loadedReplay = replayFrameList;
        this.frameCounter = 0;
    }

    /**
     * Restart replay
     */
    public void restartReplay() {
        this.frameCounter = 0;
    }

    /**
     * Move Play Head to Selected Frame
     * @param frame frame index
     */
    public void movePlayHead(int frame) {
        this.frameCounter += frame;
    }

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        frameCounter++;
        return getValidFrame().copy();
    }

    /**
     * Get Valid frame from list
     * (If out of index, return first or last frame)
     * @return closest valid frame
     */
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
