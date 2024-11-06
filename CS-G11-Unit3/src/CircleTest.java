import java.awt.Point;

public class CircleTest {

    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println(c1);
        
        Circle c2 = new Circle(new Point(4, 5), 3);
        System.out.println(c2);
    }

}
