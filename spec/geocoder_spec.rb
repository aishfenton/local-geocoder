require "local_geocoder"

describe LocalGeocoder::Geocoder do

  let(:gc) { LocalGeocoder::Geocoder.new }
  let(:sf) { [-122.4022462, 37.7892022] }
  let(:auckland) { [174.7758815, -36.7764613] }

  it "Can have a configurable directory" do
    test = LocalGeocoder::Geocoder.new("/tmp/test") 
    test.data_dir.should == "/tmp/test"
  end
  
  it "Returns a result" do
    gc.reverse_geocode(*auckland).should be_kind_of LocalGeocoder::Result
  end

  it "Return the correct country with a short_name and long_name" do
    result = gc.reverse_geocode(*auckland)
    result.country.short_name.should == "NZL"
    result.country.long_name.should == "New Zealand"
  end
  
  it "Return also returns an administrative area level 1 (state) and level 2 (county) if within the USA" do
    result = gc.reverse_geocode(*sf)
    result.country.short_name.should == "USA"
    result.country.long_name.should == "United States of America"
    
    result.state.short_name.should == "CA"
    result.state.long_name.should == "California" 
  end

end


