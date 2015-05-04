## README

#####*latlong* 
######is a utility library that can be called to work out cell height, width based on lat long, check out the unit test to see how to use it

######for example
```
assertTrue(161 == LatLongUtil.calcCellId(new LatLong(40.752827, -74.913585)).getCellHeight());
assertTrue(157 == LatLongUtil.calcCellId(new LatLong(40.752827, -73.973145)).getCellWidth());
```
