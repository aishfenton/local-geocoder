local-geocoder
==============

Reverse geocodes a lat,lng to a country code (plus State and County for the US). Runs locally, with no external dependancies, and is fast enough for large batch jobs (10,000 reverse geocodes per minute using the JRuby extention).

# Installation

  gem install local-geocoder

# Lib usage

  require 'local_geocoder' 

  geo = LocalGeocoder::Geocoder.new
  result = geo.reverse_geocode( -122.4194155, 37.7749295 )
  p result.country, result.state, result.county

  > United States of America (USA), California (CA), San Francisco

# Command line usage

  > local_geocode [option] []
  
# Attribution

Based on an original data set by https://github.com/johan/world.geo.json and hand tweaked for better geocoding.
