require "csv"
require "benchmark"

$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), "../lib"))
require "local_geocoder"

@geo = LocalGeocoder::Geocoder.new

def test_real_data
  CSV.foreach("perf_test/data.csv", col_sep: "\t") do |row|
    @geo.reverse_geocode(row[0].to_f, row[1].to_f)
  end
end

Benchmark.bmbm do |r|

  r.report("1") { @geo.reverse_geocode(122, 177) }
  r.report("10000") { 10000.times { @geo.reverse_geocode( [1,-1].sample * Random.rand(180), [1,-1].sample * Random.rand(90)) } }
  r.report("real-data") { test_real_data }
  
end

__END__

