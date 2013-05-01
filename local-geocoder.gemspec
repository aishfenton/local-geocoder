Gem::Specification.new do |s|
  s.name    = "local-geocoder"
  s.version = '0.1.0'
  s.summary = "Reverse geocodes a lat,lng to a country code (plus State for the US only). Runs locally, with no dependancies, and is fast enough for large batch jobs"
  s.author  = "Aish Fenton"
  
  files = Dir.glob("ext/**/*.{java,rb}") +
          Dir.glob("lib/**/*.rb")
  
  if RUBY_PLATFORM =~ /java/
    s.platform = "java"
    files << "lib/local_geocoder_geometry.jar"
  end
  
  s.files = files 

  s.add_development_dependency "rspec"
  s.add_development_dependency "rake-compiler"
end

