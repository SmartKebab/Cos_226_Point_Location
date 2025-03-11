public class LineSegment {
    private float x1;
    private float y1;
    private float x2;
    private float y2;

    private int lineIndex;

    public LineSegment(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        lineIndex = 0;
    }
    public Point findIntersection(LineSegment other) {
            float a1 = this.y2 - this.y1;
            float b1 = this.x1 - this.x2;
            float c1 = a1 * this.x1 + b1 * this.y1;

            float a2 = other.y2 - other.y1;
            float b2 = other.x1 - other.x2;
            float c2 = a2 * other.x1 + b2 * other.y1;

            float determinant = a1 * b2 - a2 * b1;

            if (determinant == 0){
                return new Point(-1, -1);
            }
            float x = (b2 * c1 - b1 * c2) / determinant;
            float y = (a1 * c2 - a2 * c1) / determinant;
            if(x > 1 || x < 0 || y > 1 || y < 0){
                return new Point(-1, -1);
            }
        return new Point(x, y);
    }
    public float getSlope(){
        if((this.x2 - this.x1) == 0){
            return 0;
        }
        return (this.y2 - this.y1) / (this.x2 - this.x1);
    }

    public LineSegment adjustLine() {
        if(getSlope() == 0){
            if((x1 == x2 && y1 > y2) || (y1 == y2 && x1 > x2)) {
                return new LineSegment(this.x2, this.y2, this.x1, this.y1);
            }
        }
        else if((getSlope() < 0 && y1 > y2) || (getSlope() > 0 && y1 > y2)) {
            return new LineSegment(this.x2, this.y2, this.x1, this.y1);
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%.2f", x1) + " " + String.format("%.2f, ", y1) + " " + String.format("%.2f", x2) + " " + String.format("%.2f)", y2);
    }
    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }
    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }
}
