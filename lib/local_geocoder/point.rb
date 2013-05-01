module LocalGeocoder

  class Point
    attr_accessor :x, :y, :data
    def initialize(x, y, data=nil)
      @x = x
      @y = y
      @data = data
    end

    def dist_to(p)
      xd = (self.x - p.x)**2
      yd = (self.y - p.y)**2
      Math::sqrt(xd + yd)
    end

    def inspect
      "[#{x}, #{y}]"
    end

    def point_array
      [self.x, self.y]
    end
  end

end
