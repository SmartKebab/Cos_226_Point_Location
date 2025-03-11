import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String inputFile = "Input.txt";

        List<Point> points = new ArrayList<>();
        List<LineSegment> lines = new ArrayList<>();

        InOutUtils.readLines(inputFile, lines, points);
        InOutUtils.printLines(lines, points);


        PointLocator tree = new PointLocator();
        tree.buildTree(lines);

        System.out.println();
        tree.printTree();

        System.out.println();
        InOutUtils.printAnswer(tree, points, lines);

        System.out.println();

        //Edge case when line when points are on the line
        LineSegment lineSegment = new LineSegment(0.00f, 0.50f, 1.00f, 0.50f);
        LineSegment lineSegment2 = new LineSegment(0.50f, 0.00f, 0.50f, 1.00f);
        lineSegment2.setLineIndex(1);
        Point point1 = new Point(0.25f, 0.50f);
        Point point2 = new Point(0.75f, 0.50f);

        List<LineSegment> testLine1 = new ArrayList<>();
        testLine1.add(lineSegment);
        testLine1.add(lineSegment2);
        List<Point> testPoints = new ArrayList<>();
        testPoints.add(point1);
        testPoints.add(point2);

        System.out.println();
        PointLocator tree2 = new PointLocator();

        System.out.println();
        tree2.buildTree(testLine1);

        System.out.println();
        tree2.printTree();

        System.out.println();
        InOutUtils.printAnswer(tree2, testPoints, testLine1);

    }
}