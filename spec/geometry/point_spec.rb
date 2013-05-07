require "support/spec_helper"
require "local_geocoder"

describe LocalGeocoder::Geometry::Point do

  let(:i_point) { LocalGeocoder::Geometry::Point.new(0, 0) }
  let(:f_point) { LocalGeocoder::Geometry::Point.new(1.134, 2.345) }

  it "It has an x value" do
    i_point.x.should == 0.0
  end
    
  it "It has an y value" do
    i_point.y.should == 0.0
  end
  
  it "Can be created with either ints or floats" do
    f_point.x.should == 1.134
  end 

end


