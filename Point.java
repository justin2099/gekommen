public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * format constructor
     * (x,y)
     */
    public Point(String s) {
        String[] format = s.split("[(,)]");
        x = Double.parseDouble(format[1]);
        y = Double.parseDouble(format[2]);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
