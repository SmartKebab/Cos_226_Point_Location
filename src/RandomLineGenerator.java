import java.util.Random;
public class RandomLineGenerator {
    public static LineSegment generateRandomLine() {
        Random random = new Random();

        Point p1 = generateRandomPointOnEdge(random);
        Point p2;

        do {
            p2 = generateRandomPointOnEdge(random);
        } while (isSameEdge(p1, p2));

        return new LineSegment(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private static Point generateRandomPointOnEdge(Random random) {
        int edge = random.nextInt(4);
        float coord = random.nextFloat();

        switch (edge) {
            case 0: return new Point(coord, 0);
            case 1: return new Point(coord, 1);
            case 2: return new Point(0, coord);
            case 3: return new Point(1, coord);
        }
        throw new IllegalStateException("Invalid edge case");
    }

    private static boolean isSameEdge(Point p1, Point p2) {
        return (p1.getX() == 0 && p2.getX() == 0) ||
                (p1.getX() == 1 && p2.getX() == 1) ||
                (p1.getY() == 0 && p2.getY() == 0) ||
                (p1.getY() == 1 && p2.getY() == 1);
    }
}
