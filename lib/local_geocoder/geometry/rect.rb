module LocalGeocoder
  module Geometry

    Rect = Struct.new(:x, :y, :width, :height) do
      def contains_point?(point)
        point.x >= self.x && point.y >= self.y &&
          point.x <= (self.x+self.width) && point.y <= (self.y+self.height)
      end
    
      def ==(rect)
        self.x == rect.x && self.y == rect.y &&
          self.width == rect.width && self.height == rect.height
      end
    
      def inspect
        "[#{x},#{y}],[#{x+width},#{y+height}]"
      end
      
    end

  end
end
