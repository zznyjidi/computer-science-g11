package physics;

public interface PhysicsProcessor {
    /**
     * Triggered Every Frame to Process/Retrieve Physics Status
     * @param currentStatus status from last processor triggered
     * @return status after processing
     */
    public PhysicsStatus process(PhysicsStatus currentStatus);
}
