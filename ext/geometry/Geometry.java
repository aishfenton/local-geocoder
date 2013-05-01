package com.afenton.local_geocoder;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyFixnum;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class Geometry implements BasicLibraryService {
  private Ruby runtime;
  
  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;
    RubyModule lc = runtime.defineModule("LocalGeocoder");
    
    RubyClass point = lc.defineClassUnder("Point", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new Point(runtime, rubyClass);
      }
    });

    //RubyClass polygon = lc.defineClassUnder("Polygon", runtime.getObject(), new ObjectAllocator() {
      //public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        //return new Polygon(runtime, rubyClass);
      //}
    //});
    
    //polygon.defineAnnotatedMethods(Polygon.class);
    point.defineAnnotatedMethods(Polygon.class);
    return true;
  }

  public class Point extends RubyObject {
  
    double x;
    double y;

    public Point(final Ruby runtime, RubyClass rubyClass) {
      super(runtime, rubyClass);
    }
   
    @JRubyMethod
    public IRubyObject initialize(ThreadContext context, IRubyObject x, IRubyObject y, IRubyObject data) {
      this.x = x;
      this.y = y;
      return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject x(ThreadContext context) {
      return x;
    }

    @JRubyMethod
    public IRubyObject y(ThreadContext context) {
      return y;
    }

  }
  
  public class Polygon extends RubyObject {
    
    public Polygon(final Ruby runtime, RubyClass rubyClass) {
      super(runtime, rubyClass);
    }
    
  }
}

