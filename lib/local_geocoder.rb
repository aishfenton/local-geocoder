require "json"

module LocalGeocoder
  DATA_DIR = "data"
  VERSION = '0.0.1'
end

#require "local_geocoder/point"
require "local_geocoder/polygon"
require "local_geocoder/data_source"
require "local_geocoder/geocoder"

if RUBY_PLATFORM =~ /java/
  require 'jruby'
  require 'geometry.jar' 
  
  com.afenton.local_geocoder.Geometry.new.basicLoad(JRuby.runtime)
end

