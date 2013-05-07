module LocalGeocoder

  Entity = Struct.new(:short_name, :long_name, :geometries) do
    def inspect
      "#{self.long_name}#{self.short_name.empty? ? "" : ' ('+self.short_name+')' }"
    end
  end

  class DataSource

    def initialize(dir)
      @countries = load_data(dir, "countries/features.geo.json")
      @administrative_areas_level_1 = Hash.new do |h,k|
        h[k] = load_data(File.join(dir, "countries", k), "features.geo.json")
      end
      @administrative_areas_level_2 = Hash.new do |h,k|
        h[k] = load_data(File.join(dir, "countries", *k), "features.geo.json")
      end
    end

    def countries
      @countries
    end
    
    def administrative_areas_level_1(country_id)
      return nil if country_id.nil?      
      @administrative_areas_level_1[country_id]
    end

    def administrative_areas_level_2(country_id, aa1_id)
      return nil if country_id.nil? || aa1_id.nil?
      @administrative_areas_level_2[[country_id, aa1_id]]
    end 

    private
 
    def load_data(dir, file)
      features = JSON.load(File.open(File.join(dir, file)))['features']
      features.map do |f|
        id = f['id'] ? f['id'][/\w+$/] : ""

        # Note: Perimeter is always first element in GeoJSON.
        geometries = case f['geometry']['type']
        when "MultiPolygon"
          f['geometry']['coordinates'].map { |g| Geometry::Polygon.from_point_array(g.first) }
        when "Polygon"
          Array(Geometry::Polygon.from_point_array(f['geometry']['coordinates'].first))
        else
          raise "Don't know how to handle geometry type: #{f['geometry']['type']}"
        end
        Entity.new(id, f['properties']['name'], geometries)
      end
    end
    
  end

end
