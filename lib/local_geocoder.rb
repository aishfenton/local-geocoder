require "json"

module LocalGeocoder
  DATA_DIR = "data"
  VERSION = '0.0.1'
end

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

