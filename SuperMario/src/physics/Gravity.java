package physics;

public class Gravity implements PhysicsProcessor {

    // Components
    Collision collision;

    /**
     * Collisions and Jumping&Falling for Character
     * @param collision collision processor
     */
    public Gravity(Collision collision) {
        this.collision = collision;
    }

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        // Jumping and Falling
        if (currentStatus.getGravityFactor() * currentStatus.getDeltaY() > 0)
            currentStatus.setDeltaY(currentStatus.getDeltaY() - currentStatus.getGravityFactor());
        else if (!this.collision.getCollisionY(currentStatus.getPosition(), -currentStatus.getGravityFactor()))
            currentStatus.setDeltaY(currentStatus.getDeltaY() - currentStatus.getGravityFactor());
        // Collision
        currentStatus = this.collision.process(currentStatus);
        // Jumping Reset
        if (currentStatus.getDeltaY() == 0)
            currentStatus.setJumping(false);
        return currentStatus;
    }

}
