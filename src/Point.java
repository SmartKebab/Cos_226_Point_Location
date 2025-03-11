public class Point{
    private float x;
    private float y;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, ", x) + " " + String.format("%.2f)", y) + " ";
    }

    public boolean PointInBounds() {
        if (x > 1 || x < 0 || y > 1 || y < 0) {
            return false;
        }
        return true;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
