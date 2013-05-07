require "support/spec_helper"
require "local_geocoder"

describe LocalGeocoder::Geometry::Point do

  let(:rect) { LocalGeocoder::Geometry::Rect.new(0, 0.1, 1.5, 2) }

  describe("#contains_point?") do

    it "Returns the x coord" do
      rect.x.should == 0
    end     
   
    it "Returns the y coord" do
      rect.y.should == 0.1
    end     
   
    it "Returns the width" do
      rect.width.should == 1.5
    end

    it "Returns the height" do
      rect.height.should == 2
    end     
   
    describe "#==" do

      it "Returns true if the other rect has exactly equal x, y, width, and height" do
        other_rect = LocalGeocoder::Geometry::Rect.new(0, 0.1, 1.5, 2)
        rect.should == other_rect
      end 

      it "Returns false if not" do
        other_rect = LocalGeocoder::Geometry::Rect.new(0, 0.1, 1.5, 2.1)
        rect.should_not == other_rect
      end 
      
    end     
    
    describe "#contains_point?" do
      it "Returns true if the rect contains the point" do
        rect.contains_point?(LocalGeocoder::Geometry::Point.new(0.1, 0.1)).should be_true
      end 

      it "Returns false if the rect doesn't contain the point" do
        rect.contains_point?(LocalGeocoder::Geometry::Point.new(1.62, 0.5)).should be_false
        rect.contains_point?(LocalGeocoder::Geometry::Point.new(0.2, 2.11)).should be_false
      end 

      it "Returns true if the rect has the point on it's border" do
        rect.contains_point?(LocalGeocoder::Geometry::Point.new(1.5, 2)).should be_true
      end
    end


 
  end

end



