Local geocoder
==============

Reverse geocodes lat,lng pairs into country code (plus State and Counties for the US). Runs locally, with no external dependancies, and is fast enough for large batch jobs (10,000 reverse geocodes per minute using the JRuby extention).

# Installation

```
gem install local-geocoder
```

# Lib usage

```ruby
require 'local_geocoder' 

geo = LocalGeocoder::Geocoder.new
result = geo.reverse_geocode( -122.4194155, 37.7749295 )
p result.country, result.state, result.county

> United States of America (USA), California (CA), San Francisco
```

# Command line usage

```
Usage:
  local_geocode [options] file

Example:
> echo "-122.4194155, 37.7749295" | local_geocode 
> United States of America (USA), California (CA), San Francisco
 
Options:
  --template, -t <s>:   The regex used to parse lng,lat paris from the input
                        stream (default: (?<lng>\-?\d+.?\d+)[,\t
                        ]\s?(?<lat>\-?\d+.?\d+))
       --version, -v:   Print version and exit
          --help, -h:   Show this message
```

# JRuby extension

If you're running JRuby then a java native extension is used instead. No need to do anything - it should be picked up automatically. For large batch jobs performance should be~ 10X faster. If performance is a concern (i.e. you're geocoding a huge batch of lng,lats) then I suggest you use JRuby with this gem, performance should be in the ballpark of a native-C version.

# Contributing and issues

I expect there's still some issues with country borders (the dataset we've based our's off is fairly coarse for geocoder purposes). If you find a country (or state/county) that is being incorrectly geocoded then raise an issue on the github repo and provide the lng/lat along with what result you were expecting. Also please note that several of the borders are still disputed. 

# Attribution

Country, State, and County boundaries are based on an original dataset by https://github.com/johan/world.geo.json.
