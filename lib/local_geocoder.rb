require "json"

module LocalGeocoder
  DATA_DIR = File.join(File.dirname(__FILE__), "../data")
end

require "local_geocoder/version"
require "local_geocoder/data_source"
require "local_geocoder/geocoder"

if RUBY_PLATFORM =~ /java/
  require 'jruby'
  require 'geometry.jar' 

  java_import 'geometry.GeometryService' 
  
  Java::Geometry::GeometryService.new.basicLoad(JRuby.runtime)
else
  require "local_geocoder/geometry/point"
  require "local_geocoder/geometry/rect"
  require "local_geocoder/geometry/polygon"
end

