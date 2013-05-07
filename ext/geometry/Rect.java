package geometry;

public class Rect {

  double x;
  double y;
  double width;
  double height;

  public Rect(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  } 
 
  public boolean containsPoint(Point point) {
    return point.getX() >= this.x && point.getY() >= this.y &&
           point.getX() <= (this.x + this.width) && point.getY() <= (this.y + this.height);
  }

  public double getX() {
    return this.x;
  }
    
  public double getY() {
    return this.y;
  }
  
  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }
  
  @Override public boolean equals(Object other) {
    boolean result = false;
    if (other instanceof Rect) {
      Rect rect = (Rect) other;
      result = (this.x == rect.getX() && this.y == rect.getY() && 
        this.width == rect.getWidth() && this.height == rect.getHeight());
    }
    return result;
  }
  
}

