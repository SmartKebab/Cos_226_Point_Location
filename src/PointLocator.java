import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PointLocator {
        private class Node{
            Node left, right;
            LineSegment line;
            Boolean isLeaf;
            public Node(LineSegment line) {
                this.line = line;
                this.isLeaf = false;
                this.left = null;
                this.right = null;
            }

        }
        private Node root;

        public PointLocator() {
            this.root = null;
        }

        public void buildTree(List<LineSegment> lines){
            root = buildTreeRecursively(lines);
        }

        public Node buildTreeRecursively(List<LineSegment> lines){
            if(lines.isEmpty()){
                return null;
            }
            LineSegment rootLine = lines.get(0);
            List<LineSegment> leftLines = new ArrayList<>();
            List<LineSegment> rightLines = new ArrayList<>();

            if(lines.size() > 1){
                for(int i = 1; i < lines.size(); i++){
                    LineSegment line = lines.get(i);
                    int lineIndex = line.getLineIndex();
                    Point intersection = rootLine.findIntersection(line);

                    if(PointInLineBounds(intersection, line)){
                        List<LineSegment> splitLines = splitLines(line, intersection);

                        for(LineSegment splitLine : splitLines){
                            splitLine.setLineIndex(lineIndex);
                            Point splitPoint = new Point(splitLine.getX1(), splitLine.getY1());
                            if(splitLine.getX1()== intersection.getX() && splitLine.getY1() == intersection.getY()){
                                splitPoint = new Point(splitLine.getX2(), splitLine.getY2());
                            }

                            if(isOnTheLeftSide(rootLine, splitPoint)){
                                leftLines.add(splitLine);
                            }
                            else {
                                rightLines.add(splitLine);
                            }
                        }
                    }
                    else if(isOnTheLeftSide(rootLine, new Point(line.getX1(), line.getY1()))){
                        leftLines.add(line);
                    }
                    else{
                        rightLines.add(line);
                    }
                }
            }

            Node node = new Node(rootLine);

            node.left = buildTreeRecursively(leftLines);
            node.right = buildTreeRecursively(rightLines);

            return node;
        }

        public float findCross_Product(LineSegment line, Point point){
            float x1 = line.getX1();
            float y1 = line.getY1();
            float x2 = line.getX2();
            float y2 = line.getY2();


            return (x2 - x1) * (point.getY() - y1) - (y2 - y1) * (point.getX() - x1);
        }

        public boolean isOnTheLeftSide(LineSegment line, Point point){

            float cross_Product = findCross_Product(line, point);

            if(cross_Product >= 0){
                return true;
            }
            return false;
        }

        public boolean isOnTheRightSide(LineSegment line, Point point){

            float cross_Product = findCross_Product(line, point);

            if(cross_Product < 0){
                return true;
            }
            return false;
        }
        public boolean PointInLineBounds(Point point, LineSegment line){
            if(Math.min(line.getX1(), line.getX2()) <= point.getX() && Math.max(line.getX1(), line.getX2()) >= point.getX()
            && Math.min(line.getY1(), line.getY2()) <= point.getY() && Math.max(line.getY1(), line.getY2()) >= point.getY()) {
                return true;
            }
            return false;
        }

        public List<LineSegment> splitLines(LineSegment line, Point point){
            List<LineSegment> lines = new ArrayList<>();

            lines.add(new LineSegment(line.getX1(), line.getY1(), point.getX(), point.getY()));
            lines.add(new LineSegment(point.getX(), point.getY(), line.getX2(), line.getY2()));

            return lines;
        }
        public LineSegment makeLine(Point p1, Point p2) {
            return new LineSegment(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
        public LineSegment findSeparatingLine(Point point1, Point point2, Node current){

            while (current != null){
                LineSegment currentline = current.line;

                boolean point1Left = (isOnTheLeftSide(currentline, point1));
                boolean point2Left = (isOnTheLeftSide(currentline, point2));
                boolean point1Right = (isOnTheRightSide(currentline, point1));
                boolean point2Right = (isOnTheRightSide(currentline, point2));

                if((point1Left && point2Right) || (point2Left && point1Right)){
                    return currentline;
                }
                if(point1Left){
                    current = current.left;
                }
                else{
                    current = current.right;
                }
            }
            return null;
        }
        public LineSegment separatingLine(Point point1, Point point2, List<LineSegment> lines) {
            LineSegment pointsLine = makeLine(point1, point2);
            LineSegment line = null;
            line = findSeparatingLine(point1, point2, root);
            if (line == null) {
                return null;
            }
            return lines.get(line.getLineIndex());
        }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.line != null) {
                System.out.printf(current.line.getLineIndex() + "Line: (%.2f, %.2f) -> (%.2f, %.2f)%n ",
                        current.line.getX1(), current.line.getY1(),
                        current.line.getX2(), current.line.getY2());
            } else {
                System.out.println("Empty Node");
            }

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

}

