module LocalGeocoder
  
  Result = Struct.new(:country, :administrative_area_level_1, :administrative_area_level_2) do
    alias_method :state, :administrative_area_level_1
    alias_method :county, :administrative_area_level_2
    def inspect
      "#{self.country.inspect}, #{self.administrative_area_level_1.inspect}, #{self.administrative_area_level_2.inspect}"
    end    
  end
  
  class Geocoder
    
    def initialize
      @data_source = DataSource.new(DATA_DIR) 
    end

    def reverse_geocode(lng, lat)
      country = find_result(lng, lat)
    end
   
    private
    
    def find_result(lng, lat)
      cnt = find_country(lng, lat)
      aa1, aa2 = find_administrative_areas(cnt.short_name, lng, lat) if cnt && cnt.short_name == "USA"
      Result.new(cnt, aa1, aa2) 
    end
    
    def find_country(lng, lat)
      @data_source.countries.find { |c| contains_location?(c, lng, lat) }
    end

    def find_administrative_areas(country_id, lng, lat)
      aa1 = @data_source.administrative_areas_level_1(country_id).find { |a| contains_location?(a, lng, lat) }
      return nil,nil if aa1.nil?

      aa2 = @data_source.administrative_areas_level_2(country_id, aa1.short_name).find { |a| contains_location?(a, lng, lat) }
      return aa1, aa2
    end

    def contains_location?(entity, lng, lat)
      entity.geometries.any? { |g| g.contains_point?(Point.new(lng, lat)) }
    end

  end

end

