module LocalGeocoder  

  Rect = Struct.new(:x, :y, :width, :height) do
    def contains_point?(point)
      point.x >= self.x && point.y >= self.y &&
        point.x <= (self.x+self.width) && point.y <= (self.y+self.height)
    end
  end

  class Polygon
    attr_accessor :points
    
    def initialize(points)
      @points = points
    end
    
    def self.from_point_array(points)
      Polygon.new(points.map { |p| Point.new(*p) })
    end
    
    def number_of_points
      @points.size
    end

    def [](i)
      @points[i]
    end
    
    def bounding_box
      @bounding_box ||= begin
        min_x, max_x = @points.minmax_by { |p| p.x }.map { |p| p.x }
        min_y, max_y = @points.minmax_by { |p| p.y }.map { |p| p.y }
        Rect.new(min_x, min_y, max_x-min_x, max_y-min_y)
      end
    end

    def contains_point?(point)
      return false if !self.bounding_box.contains_point?(point)
      
      contains_point = false
      i = -1
      j = self.number_of_points - 1
      while (i += 1) < self.number_of_points
        p1 = self[i]; p2 = self[j]
        if within_y_bounds?(point, p1, p2)
          if intersects_line_segment?(point, p1, p2)
            contains_point = !contains_point
          end
        end
        j = i
      end
      return contains_point
    end

    private

    def within_y_bounds?(point, p1, p2)
      (p1.y <= point.y && point.y < p2.y) || 
        (p2.y <= point.y && point.y < p1.y)
    end

    def intersects_line_segment?(point, p1, p2)
      (point.x < (p2.x - p1.x) * (point.y - p1.y) / 
                 (p2.y - p1.y) + p1.x)
    end
    
  end
   
end

