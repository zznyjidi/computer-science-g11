import java.awt.Point;

public class Circle {
    // Fields
    private Point center;
    private double radius;

    // Constructor
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Create Unit Circle if no param
     */
    public Circle() {
        this.center = new Point(0, 0);
        this.radius = 1;
    }

    // Getters/Setters
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // toString Method
    @Override
    public String toString() {
        return "Circle [center=" + center + ", radius=" + radius + "]";
    }

    // Utility Method

    /**
     * Calculate Area of the Circle
     * @return circle's area
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculate the Circumference of the Circle
     * @return circle's circumference
     */
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

}
