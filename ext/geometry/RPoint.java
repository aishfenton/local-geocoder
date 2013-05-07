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

@JRubyClass(name = "LocalGeocoder::Geometry::Point")
public class RPoint extends RubyObject {

  Point point;
  IRubyObject data;

  public RPoint(final Ruby runtime, RubyClass rubyClass) {
    super(runtime, rubyClass);
  }
  
  @JRubyMethod(name = "initialize", required = 2, optional = 1)
  public IRubyObject initialize(ThreadContext context, IRubyObject[] args) {
    Ruby ruby = context.getRuntime();
    double x = args[0].convertToFloat().getDoubleValue();
    double y = args[1].convertToFloat().getDoubleValue();
    
    this.point = new Point(x,y);
    this.data = context.nil;
    if (args.length > 2) {
      this.data = args[2];
    }
    
    return context.nil;
  }
  
  public void setPoint(Point point) {
    this.point = point;
  }

  public Point getJava() {
    return this.point;
  }
 
  @JRubyMethod(name = "x")
  public IRubyObject x(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    return ruby.newFloat(point.getX());
  }

  @JRubyMethod(name = "y")
  public IRubyObject y(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    return ruby.newFloat(point.getY());
  }

  @JRubyMethod(name = "data")
  public IRubyObject data(ThreadContext context) {
    return data;
  }

  @JRubyMethod(name = "inspect")
  public IRubyObject inspect(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    return ruby.newString(point.toString());
  }
  
}
