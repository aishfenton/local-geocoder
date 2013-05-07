require "local_geocoder"

describe LocalGeocoder::DataSource do

  let(:data_source) { LocalGeocoder::DataSource.new("./data") }

  
  describe "#countries" do

    it "Returns an array of entities" do
      data_source.countries.each do |c|
        c.should be_kind_of LocalGeocoder::Entity
      end
    end

    it "Gives each entity a valid short-name and long-name" do
      data_source.countries.each do |c|
        c.short_name.should =~ /\w\w\-?\w/
        c.long_name.should_not be_empty
      end
    end

    it "Gives each entity a valid set of geometries" do
      data_source.countries.each do |c|
        c.geometries.should be_kind_of Array
        c.geometries.each { |g| g.should be_kind_of LocalGeocoder::Geometry::Polygon }
        c.geometries.each { |g| g.number_of_points.should > 4 }
      end
    end 

  end 

end


