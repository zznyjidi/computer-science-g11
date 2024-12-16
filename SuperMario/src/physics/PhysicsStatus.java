package physics;

public class PhysicsStatus {
    private int gravityFactor;
    private int deltaX; 
    private int deltaY;
    private boolean jumping;

    private int locationX;
    private int locationY;
    
    public PhysicsStatus(int gravityFactor, int deltaX, int deltaY, boolean jumping, int locationX, int locationY) {
        this.gravityFactor = gravityFactor;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.jumping = jumping;
        this.locationX = locationX;
        this.locationY = locationY;
    }

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

    public PhysicsStatus copy() {
        return new PhysicsStatus(gravityFactor, deltaX, deltaY, jumping, locationX, locationY);
    }

    public void update(int gravityFactor, int deltaX, int deltaY, boolean jumping, int locationX, int locationY) {
        this.gravityFactor = gravityFactor;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.jumping = jumping;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void update(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void reset() {
        this.gravityFactor = 1;
        this.deltaX = 0;
        this.deltaY = 0;
        this.jumping = false;
    }
}
