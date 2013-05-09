require "support/spec_helper"
require "local_geocoder"

describe LocalGeocoder::Geometry::Polygon do

  let(:simple) { LocalGeocoder::Geometry::Polygon.from_point_array([[0,0],[0,1],[1.1,2],[1,0]]) }
  let(:sf_boundary) { LocalGeocoder::Geometry::Polygon.from_point_array(BOUNDARIES['sf_boundary']) }
  let(:sc_boundary) { LocalGeocoder::Geometry::Polygon.from_point_array(BOUNDARIES['sc_boundary']) }
  let(:nzl_si_boundary) { LocalGeocoder::Geometry::Polygon.from_point_array(BOUNDARIES['nzl_boundary']) }
  let(:fairfax_county) { LocalGeocoder::Geometry::Polygon.from_point_array(BOUNDARIES['fairfax_county']) } 
  
  it "Calculates a bounding box that encloses the polygon" do
    simple.bounding_box.should == LocalGeocoder::Geometry::Rect.new(0.0, 0.0, 1.1, 2.0)
  end

  it "Builds a polygon from a nested array of x,y's" do
    simple = LocalGeocoder::Geometry::Polygon.from_point_array([[0,0],[1,1],[1,0]])
    simple.points == [LocalGeocoder::Geometry::Point.new(0,0), LocalGeocoder::Geometry::Point.new(1,1), LocalGeocoder::Geometry::Point.new(1,0)] 
  end
 
  describe "#contains_point" do

    context "Simple shapes" do
    
      it "Returns true if the point lies within the polygon" do
        simple.contains_point?(LocalGeocoder::Geometry::Point.new(0.1,0.1)).should be_true
      end

      it "Returns false if the point lies outside the polygon" do
        simple.contains_point?(LocalGeocoder::Geometry::Point.new(0,2)).should be_false 
      end    

      it "Returns true if the point lies on the boundary" do
        simple.contains_point?(LocalGeocoder::Geometry::Point.new(0,0)).should be_true
      end

    end
    
    context "Complex shapes" do

      it "Returns true if point lies within polygon" do
        sf_boundary.contains_point?(LocalGeocoder::Geometry::Point.new(-122.425557, 37.7813481)).should be_true
        nzl_si_boundary.contains_point?(LocalGeocoder::Geometry::Point.new(173, -42)).should be_true
        fairfax_county.contains_point?(LocalGeocoder::Geometry::Point.new(-77.3570028, 38.9586307)).should be_true
      end

      it "Returns false if point lies outside polygon" do
        sc_boundary.contains_point?(LocalGeocoder::Geometry::Point.new(-122.425557, 37.7813481)).should be_false
      end
      
    end

  end

end

