package geometry;

public class Polygon {

  Point[] points;
  Rect boundingBox;

  public Polygon(Point[] points) {
    this.points = points;
  } 

  public Point[] getPoints() {
    return this.points;
  }
 
  public int numberOfPoints() {
    return this.points.length;
  }
 
  public Rect boundingBox() {
    if (boundingBox == null) {
      double minX, minY, maxX, maxY;
      minX = minY = Double.POSITIVE_INFINITY; 
      maxX = maxY = Double.NEGATIVE_INFINITY;

      for (Point p: this.points) {
        
        double x = p.getX();
        double y = p.getY();

        if (x < minX) 
          minX = x;
        if (x > maxX)
          maxX = x;

        if (y < minY) 
          minY = y;
        if (y > maxY)
          maxY = y;
      }
       
      this.boundingBox = new Rect(minX, minY, maxX-minX, maxY-minY); 
    }
 
    return this.boundingBox;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Point p : points) {
      sb.append( p.toString() + "," );
    }
    return sb.toString();   
  }

  public boolean containsPoint(Point point) {
    if (!this.boundingBox().containsPoint(point))
      return false;

    boolean containsPoint = false;
    int i = -1;
    int j = this.points.length - 1;
    while (++i < this.points.length) {
      Point p1 = this.points[i];
      Point p2 = this.points[j];
      if (this.withinYBounds(point, p1, p2)) {
        if (this.intersectsLineSegment(point, p1, p2)) {
          containsPoint = !containsPoint;
        }
      }
      j = i;
    }
    return containsPoint; 
  }
  
  private boolean intersectsLineSegment(Point point, Point p1, Point p2) {
    return (point.getX() < (p2.getX() - p1.getX()) * (point.getY() - p1.getY()) / (p2.getY() - p1.getY()) + p1.getX());   
  }      

  private boolean withinYBounds(Point point, Point p1, Point p2) {
    return (p1.getY() <= point.getY() && point.getY() < p2.getY()) ||
      (p2.getY() <= point.getY() && point.getY() < p1.getY());
  }

}


