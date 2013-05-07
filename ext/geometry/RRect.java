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

@JRubyClass(name = "LocalGeocoder::Geometry::Rect")  
public class RRect extends RubyObject {

  Rect rect;

  public RRect(final Ruby runtime, RubyClass rubyClass) {
    super(runtime, rubyClass);
  }
 
  @JRubyMethod(name = "initialize", required = 4)
  public IRubyObject initialize(ThreadContext context, IRubyObject[] args) {
    Ruby ruby = context.getRuntime();

    double dX = args[0].convertToFloat().getDoubleValue();
    double dY = args[1].convertToFloat().getDoubleValue();
    double dWidth = args[2].convertToFloat().getDoubleValue();
    double dHeight = args[3].convertToFloat().getDoubleValue();
    
    this.rect = new Rect(dX,dY,dWidth,dHeight);
      
    return context.nil;
  }

  public void setRect(Rect rect) {
    this.rect = rect;
  }

  public Rect getJava() {
    return this.rect;
  }
  
  @JRubyMethod(name = "x")
  public IRubyObject x(ThreadContext context) {
    Ruby ruby = context.getRuntime(); 
    return ruby.newFloat(rect.getX());
  }

  @JRubyMethod(name = "y")
  public IRubyObject y(ThreadContext context) {
    Ruby ruby = context.getRuntime(); 
    return ruby.newFloat(rect.getY());
  }
  

  @JRubyMethod(name = "width")
  public IRubyObject widht(ThreadContext context) {
    Ruby ruby = context.getRuntime(); 
    return ruby.newFloat(rect.getWidth());
  }
  

  @JRubyMethod(name = "height")
  public IRubyObject height(ThreadContext context) {
    Ruby ruby = context.getRuntime(); 
    return ruby.newFloat(rect.getHeight());
  }

  @JRubyMethod(name = "==")
  public IRubyObject r_eql(ThreadContext context, IRubyObject rRect) {
    Ruby ruby = context.getRuntime(); 

    
    return ruby.newBoolean(rect.equals(((RRect) rRect).getJava()));
  } 

  @JRubyMethod(name = "contains_point?")
  public IRubyObject containsPoint(ThreadContext context, IRubyObject point) {
    Ruby ruby = context.getRuntime();
    boolean result = this.rect.containsPoint(((RPoint)point).getJava());
      
    return ruby.newBoolean(result);
  }

  @JRubyMethod(name = "inspect")
  public IRubyObject inspect(ThreadContext context) {
    Ruby ruby = context.getRuntime();
    String str = String.format("[%s,%s],[%s,%s]", rect.getX(),rect.getY(),rect.getX()+rect.getWidth(),rect.getY()+rect.getHeight()); 
    return ruby.newString(str);
  }
  
}
