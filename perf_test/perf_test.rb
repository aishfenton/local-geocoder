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

> jruby 1.7.2 (1.9.3p327)

1           0.050000   0.010000   0.060000 (  0.019000)
5000        1.790000   0.030000   1.820000 (  1.111000)
real-data   1.600000   0.030000   1.630000 (  1.077000)
------------------------------------ total: 3.510000sec

                user     system      total        real
1           0.000000   0.000000   0.000000 (  0.000000)
5000        0.630000   0.020000   0.650000 (  0.518000)
real-data   1.040000   0.040000   1.080000 (  0.752000)

