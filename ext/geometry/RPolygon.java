package geometry;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyFixnum;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

@JRubyClass(name = "LocalGeocoder::Geometry::Polygon")  
public class RPolygon extends RubyObject {

  Polygon polygon;

  public RPolygon(final Ruby runtime, RubyClass rubyClass) {
    super(runtime, rubyClass);
  }

  public void setPolygon(Polygon polygon) {
    this.polygon = polygon; 
  }

  @JRubyMethod(name = "initialize")
  public IRubyObject initialize(ThreadContext context, IRubyObject rps) {
    Ruby ruby = context.getRuntime();

    RubyArray rPoints = (RubyArray) rps;

    Point[] points = new Point[rPoints.getLength()];
    for (int i = 0; i < rPoints.getLength(); i++) {
      points[i] = ((RPoint) rPoints.get(i)).getJava();
    }
    
    this.polygon = new Polygon(points);

    return context.nil;
  }

  @JRubyMethod(meta = true, name = "from_point_array")
  public static IRubyObject fromPointArray(ThreadContext context, IRubyObject klazz, IRubyObject pts) {
    Ruby ruby = context.getRuntime();

    RubyArray rpts = (RubyArray) pts;

    Point[] points = new Point[rpts.getLength()];
    for (int i = 0; i < rpts.getLength(); i++) {
      double x = ((Number) ((RubyArray) rpts.get(i)).get(0)).doubleValue();
      double y = ((Number) ((RubyArray) rpts.get(i)).get(1)).doubleValue();
      points[i] = new Point(x,y);
    }
   
    IRubyObject obj = ((RubyClass) klazz).allocate();
    ((RPolygon) obj).setPolygon(new Polygon(points));

    return obj;
  }

  @JRubyMethod
  public IRubyObject number_of_points(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    int i = this.polygon.numberOfPoints();
    return ruby.newFixnum(i);
  }

  @JRubyMethod(name = "points")
  public IRubyObject points(ThreadContext context) {
    Ruby ruby = context.getRuntime();

    Point[] points = this.polygon.getPoints();
    RPoint[] rPoints = new RPoint[points.length];
    for (int i = 0; i < points.length; i++) {
      RPoint rPoint = new RPoint(ruby, (RubyClass)ruby.getClassFromPath("LocalGeocoder::Geometry::Point"));
      rPoint.setPoint(points[i]);
      rPoints[i] = rPoint;
    }

    return ruby.newArray(rPoints);
  }

  @JRubyMethod(name = "bounding_box")
  public IRubyObject boundingBox(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    
    Rect rect = this.polygon.boundingBox();
    RRect rRect = new RRect(ruby, (RubyClass)ruby.getClassFromPath("LocalGeocoder::Geometry::Rect"));
    rRect.setRect(rect);

    return rRect;
  }

  @JRubyMethod(name = "contains_point?")
  public IRubyObject containsPoint(ThreadContext context, IRubyObject rPoint) {
    Ruby ruby = context.getRuntime();
    
    boolean result = this.polygon.containsPoint(((RPoint)rPoint).getJava());
    return ruby.newBoolean(result);
  }

  @JRubyMethod(name = "inspect")
  public IRubyObject inspect(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    return ruby.newString(polygon.toString());
  }
  
}
