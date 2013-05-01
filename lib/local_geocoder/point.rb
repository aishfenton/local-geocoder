module LocalGeocoder

  class ZPoint
    attr_accessor :x, :y, :data
    def initialize(x, y, data=nil)
      @x = x
      @y = y
      @data = data
    end

    def inspect
      "[#{x}, #{y}]"
    end

  end

end
