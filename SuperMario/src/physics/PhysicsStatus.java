package physics;

public class PhysicsStatus {

    // Relative Moving Info
    private int gravityFactor;
    private int deltaX; 
    private int deltaY;
    private boolean jumping;

    // Absolute Position
    private int locationX;
    private int locationY;

    /**
     * Data Class for Physics Status
     * @param gravityFactor amount of movement per frame
     * @param deltaX movement for the frame on x
     * @param deltaY movement for the frame on y
     * @param jumping is jumping
     * @param locationX current absolute x position
     * @param locationY current absolute y position
     */
    public PhysicsStatus(int gravityFactor, int deltaX, int deltaY, boolean jumping, int locationX, int locationY) {
        this.gravityFactor = gravityFactor;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.jumping = jumping;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    // Getters & Setters for relative info
    public int getGravityFactor() {
        return gravityFactor;
    }
    public void setGravityFactor(int gravityFactor) {
        this.gravityFactor = gravityFactor;
    }

    public int getDeltaX() {
        return deltaX;
    }
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public boolean isJumping() {
        return jumping;
    }
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    // Getters for absolute position
    public int getLocationX() {
        return locationX;
    }
    public int getLocationY() {
        return locationY;
    }
    public int[] getPosition() {
        return new int[] {locationX, locationY};
    }

    @Override
    public String toString() {
        return "PhysicsStatus [gravityFactor=" + gravityFactor + ", deltaX=" + deltaX + ", deltaY=" + deltaY
                + ", locationX=" + locationX + ", locationY=" + locationY + "]";
    }

    /**
     * Create a Copy of the Status
     * @return copied version of status
     */
    public PhysicsStatus copy() {
        return new PhysicsStatus(gravityFactor, deltaX, deltaY, jumping, locationX, locationY);
    }

    /**
     * Update Info in the Status
     * @param gravityFactor amount of movement per frame
     * @param deltaX movement for the frame on x
     * @param deltaY movement for the frame on y
     * @param jumping is jumping
     * @param locationX current absolute x position
     * @param locationY current absolute y position
     */
    public void update(int gravityFactor, int deltaX, int deltaY, boolean jumping, int locationX, int locationY) {
        this.gravityFactor = gravityFactor;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.jumping = jumping;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Update Absolute Position in the Status
     * @param locationX current absolute x position
     * @param locationY current absolute y position
     */
    public void update(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Reset Physics Status
     */
    public void reset() {
        this.gravityFactor = 1;
        this.deltaX = 0;
        this.deltaY = 0;
        this.jumping = false;
    }
}
