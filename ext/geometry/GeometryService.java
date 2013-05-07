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

public class GeometryService implements BasicLibraryService {
  private Ruby runtime;
  
  public boolean basicLoad(Ruby runtime) {
    this.runtime = runtime;
    RubyModule lc = runtime.defineModule("LocalGeocoder");
    RubyModule geo = lc.defineModuleUnder("Geometry");
    
    RubyClass point = geo.defineClassUnder("Point", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new RPoint(runtime, rubyClass);
      }
    });

    RubyClass rect = geo.defineClassUnder("Rect", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new RRect(runtime, rubyClass);
      }
    });

    RubyClass polygon = geo.defineClassUnder("Polygon", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new RPolygon(runtime, rubyClass);
      }
    });

    point.defineAnnotatedMethods(RPoint.class);    
    rect.defineAnnotatedMethods(RRect.class);
    polygon.defineAnnotatedMethods(RPolygon.class);
    return true;
  }

}

