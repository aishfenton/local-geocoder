require 'rake/extensiontask'
Rake::ExtensionTask.new('polygon')

# Building tasks
spec = Gem::Specification.load('faye-websocket.gemspec')
if RUBY_PLATFORM =~ /java/
  require 'rake/javaextensiontask'
  Rake::JavaExtensionTask.new('local_geocoder_geometry', spec)
end

namespace :convert do

  desc "Converts geo-json file structure as found at 'world.geo.json' into the format we expect"
  task :geo_json do
    require "json"
    source = ENV["source"]
    dest = "data"
    
    `rm -Rf #{dest} && mkdir #{dest}`
    FeatureProcessor.new.process(source, dest, "countries") 
  end

end

class FeatureProcessor

  def process(source, dest, path)
    features = extract_features(Dir[File.join(source, path, '*.json')])
    return if features.empty?
    
    Dir.mkdir(File.join(dest, path))
    write_features(features, File.join(dest, path, "features.geo.json"))
    features.each do |f|
      next unless f['id']
      process(source, dest, File.join(path, f['id'][/\w+$/]))
    end
  end

  def extract_features(in_files)
    in_files.map do |f|
      begin
        json = JSON.load(File.open(f))
        json['features'].first
      rescue JSON::ParserError => e
        puts "Error parsing: #{f}"
        raise e
      end
    end
  end

  def write_features(features, out_file)
    File.open(out_file, "w") do |file|
      file.puts '{"type":"FeatureCollection","features":'
      file.puts features.to_json
      file.puts '}'
    end
  end

end
