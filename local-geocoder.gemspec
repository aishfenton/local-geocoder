require "./lib/local_geocoder/version"

Gem::Specification.new do |s|
  s.name    = "local-geocoder"
  s.version = LocalGeocoder::VERSION
  s.summary = "Reverse geocodes lng, lat pairs into country codes (plus State and Counties within the US). Runs locally, with no external dependancies, and is fast enough for large batch jobs"
  s.author  = "Aish Fenton"

  files = Dir.glob("ext/**/*.{java,rb}") +
          Dir.glob("lib/**/*.rb") +
          Dir.glob("data/**/*.json")
  
  if RUBY_PLATFORM =~ /java/
    s.platform = "java"
    files << "lib/geometry.jar"
  end
  
  s.files = files
  s.add_dependency "trollop"

  s.executables = ['local_geocode']

  s.add_development_dependency "rake"
  s.add_development_dependency "rspec"
  s.add_development_dependency "rake-compiler"
end

__END__

rbenv shell jruby-1.7.2 && gem build local-geocoder.gemspec
rbenv shell 1.9.3-p392 && gem build local-geocoder.gemspec
gem push local-geocoder-0.1.2.gem
gem push local-geocoder-0.1.2-java.gem

