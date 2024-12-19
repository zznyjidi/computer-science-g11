package replay;

import physics.PhysicsProcessor;
import physics.PhysicsStatus;

public class ReplayPlayer implements PhysicsProcessor {

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        return currentStatus;
    }
    
}
