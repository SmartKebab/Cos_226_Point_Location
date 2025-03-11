import javax.sound.sampled.Line;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class InOutUtils {
    public static void readLines(String fileName, List<LineSegment> list1, List<Point> list2) {
        String line;
        FileInputStream fis = null;
        Scanner scanner = null;
        try{
            fis = new FileInputStream(fileName);
            scanner = new Scanner(fis, "UTF-8");
            int linesCount = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < linesCount; i++) {
                String[] parts = scanner.nextLine().split("\\s+");
                System.out.println(parts[0]);
                LineSegment lineSegment = new LineSegment(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Float.parseFloat(parts[3]));
                lineSegment = lineSegment.adjustLine();
                list1.add(lineSegment);
                list1.get(i).setLineIndex(i);
            }
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\\s+");
                list2.add(new Point(Float.parseFloat(parts[0]), Float.parseFloat(parts[1])));
                list2.add(new Point(Float.parseFloat(parts[2]), Float.parseFloat(parts[3])));
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Blogas duomenu formattas");
        }
        finally {
            if (scanner != null) scanner.close();
        }
    }
    public static void printLines(List<LineSegment> list1, List<Point> list2){
        for(LineSegment line: list1){
            System.out.println(line.toString());
        }
        System.out.println();
        for(Point point: list2){
            System.out.println(point.toString());
        }
    }
    public static void printAnswer(PointLocator tree, List<Point> points, List<LineSegment> lines){
        for(int i = 0; i < points.size(); i = i + 2){
            LineSegment line = tree.separatingLine(points.get(i), points.get(i+1), lines);
            if(line != null){
                System.out.println("Yes, points " + points.get(i).toString() + ", " + points.get(i+1).toString() + " are Separeted by line: " + line.toString() + " , linijos indeksas: " + line.getLineIndex());
            }
            else{
                System.out.println("No, points "+ points.get(i).toString() + " " + points.get(i+1).toString() + " are not separetd, by any Line");
            }
        }
    }
}

