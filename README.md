Local geocoder
==============
[![Build Status](https://travis-ci.org/aishfenton/local-geocoder.png?branch=master)](https://travis-ci.org/aishfenton/local-geocoder)

Reverse geocodes lng, lat pairs into country codes (plus State and Counties within the US). Runs locally, with no external dependancies, and is fast enough for large batch jobs (10,000 reverse geocodes per minute using the JRuby extention).

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

# FAQ

### Why use gem this instead of geocoder X.

Most geocoder gems (actually all last time that I checked) use an external web service to do their reverse geocoding (such as Google, Yahoo!, etc). And all have some sort of limit on how many reverse geocodes you can do per day. Also calling an external web service is slow. This gem does everything locally, so it runs fast, and doesn't have any limits on how many reverse geocodes you can do. The tradeoff is that it only provides coarse results. So if you have a large number of lng, lats that need to be translated into countries, then this gem should work for you. Otherwise you're probably better off using another gem.   


### How do I get a street address?

You don't. See 'Why use gem this instead of geocoder X'.

### JRuby extension

If you're running JRuby then a java native extension is used. No need to do anything - it should be picked up automatically. For large batch jobs performance will be ~10X faster. If performance is a concern (i.e. you're geocoding a large batch of lng, lats) then I suggest you use JRuby to run this gem, as performance should be in the ballpark of native code.

# Contributing and issues

I expect there's still some issues with country borders (the dataset we've based our's off is fairly coarse for geocoder purposes). If you find a country (or state/county) that is being incorrectly geocoded then raise an issue on the github repo and provide the lng/lat along with what result you were expecting. Also please note that several of the borders are still disputed. 

# Other platforms

Richard Hopton has put together a .NET port here: https://github.com/richardhopton/LocalGeocoder

# Attribution

Country, State, and County boundaries are based on an original dataset at https://github.com/johan/world.geo.json.
