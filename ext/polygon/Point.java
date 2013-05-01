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

public class  implements BasicLibraryService {
  private Ruby runtime;
  
  // Initial setup function. Takes a reference to the current JRuby runtime and
  // sets up our modules. For JRuby, we will define mask() as an instance method
  // on a specially created class, Faye::WebSocketMask.
  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;
    RubyModule faye = runtime.defineModule("Faye");
    
    // Create the WebSocketMask class. defineClassUnder() takes a name, a
    // reference to the superclass -- runtime.getObject() gets you the Object
    // class for the current runtime -- and an allocator function that says
    // which Java object to constuct when you call new() on the class.
    RubyClass webSocket = faye.defineClassUnder("WebSocketMask", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new WebSocket(runtime, rubyClass);
      }
    });
    
    webSocket.defineAnnotatedMethods(WebSocket.class);
    return true;
  }
  
  // The Java class that backs the Ruby class Faye::WebSocketMask. Its methods
  // annotated with @JRubyMethod become exposed as instance methods on the Ruby
  // class through the call to defineAnnotatedMethods() above.
  public class Polygon extends RubyObject {
    public WebSocket(final Ruby runtime, RubyClass rubyClass) {
      super(runtime, rubyClass);
    }
    
    @JRubyMethod
    public IRubyObject mask(ThreadContext context, IRubyObject payload, IRubyObject mask) {
      int n = ((RubyArray)payload).getLength(), i;
      long p, m;
      RubyArray unmasked = RubyArray.newArray(runtime, n);
      
      long[] maskArray = {
        (Long)((RubyArray)mask).get(0),
        (Long)((RubyArray)mask).get(1),
        (Long)((RubyArray)mask).get(2),
        (Long)((RubyArray)mask).get(3)
      };
      
      for (i = 0; i < n; i++) {
        p = (Long)((RubyArray)payload).get(i);
        m = maskArray[i % 4];
        unmasked.set(i, p ^ m);
      }
      return unmasked;
    }
  }
}
