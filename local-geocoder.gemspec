require "./lib/local_geocoder/version"

Gem::Specification.new do |s|
  s.name    = "local-geocoder"
  s.version = LocalGeocoder::VERSION
  s.summary = "Reverse geocodes lng, lat pairs into country codes (plus State and Counties within the US). Runs locally, with no external dependancies, and is fast enough for large batch jobs"
  s.author  = "Aish Fenton"
  
  files = Dir.glob("ext/**/*.{java,rb}") +
          Dir.glob("lib/**/*.rb")
  
  if RUBY_PLATFORM =~ /java/
    s.platform = "java"
    files << "lib/local_geocoder_geometry.jar"
  end
  
  s.files = files
  s.add_dependency "trollop"
  
  s.add_development_dependency "rspec"
  s.add_development_dependency "rake-compiler"
end

