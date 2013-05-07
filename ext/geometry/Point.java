package geometry;

public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }

  public String toString() {
    return String.format("[%s,%s]", x, y);
  }
  
}


