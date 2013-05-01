package org.afenton.localGeocoder;

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

public class Geometry implements BasicLibraryService {
  private Ruby runtime;
  
  public boolean basicLoad(Ruby runtime) {
    this.runtime = runtime;
    RubyModule lc = runtime.defineModule("LocalGeocoder");
    
    RubyClass point = lc.defineClassUnder("Point", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new Point(runtime, rubyClass);
      }
    });

    RubyClass polygon = lc.defineClassUnder("Polygon", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new Polygon(runtime, rubyClass);
      }
    });
    
    polygon.defineAnnotatedMethods(Polygon.class);
    point.defineAnnotatedMethods(Point.class);
    return true;
  }

  @JRubyClass(name = "LocalGeocoder::Point")
  public class Point extends RubyObject {
  
    double x;
    double y;
    IRubyObject data;

    public Point(final Ruby runtime, RubyClass rubyClass) {
      super(runtime, rubyClass);
    }
   
    @JRubyMethod(name = "initialize", required = 2, optional = 1)
    public IRubyObject initialize(ThreadContext context, IRubyObject[] args) {
      Ruby ruby = context.getRuntime();
      this.x = args[0].convertToFloat().getDoubleValue();
      this.y = args[1].convertToFloat().getDoubleValue();
      this.data = context.nil;
      if (args.length > 2) {
        this.data = args[2];
      }
      
      return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject x(ThreadContext context) {
      Ruby ruby = context.getRuntime();
      return ruby.newFloat(x);
    }

    @JRubyMethod
    public IRubyObject y(ThreadContext context) {
      Ruby ruby = context.getRuntime();
      return ruby.newFloat(y);
    }

    @JRubyMethod
    public IRubyObject data(ThreadContext context) {
      return data;
    }

    @JRubyMethod(name = "inspect")
    public IRubyObject inspect(ThreadContext context) {
      Ruby ruby = context.getRuntime();
      String str = String.format("[%s,%s]", x,y); 
      return ruby.newString(str);
    }
    
  }

  @JRubyClass(name = "LocalGeocoder::Polygon")  
  public class Polygon extends RubyObject {
    
    Point[] points;

    public Polygon(final Ruby runtime, RubyClass rubyClass) {
      super(runtime, rubyClass);
    }
    
    @JRubyMethod(name = "initialize")
    public IRubyObject initialize(ThreadContext context, IRubyObject points) {
      Ruby ruby = context.getRuntime();
      this.points = points.convertToArray().toJava(com.afenton.local_geocoder.Point);
      return context.nil;
    }
   
    @JRubyMethod(name = "points")
    public IRubyObject points(ThreadContext context) {
      Ruby ruby = context.getRuntime();
      return ruby.newArray(this.points);
    }
    
  }
}

