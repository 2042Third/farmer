"<button onclick=\"getLocation()\" class=\"primary\">Get My Location</button>\n"+
"<button onclick=\"getLocationFixed()\" class=\"primary\">Use RPI Location</button>\n"+
"<button onclick=\"getLocationFixedTable()\" class=\"primary\">Use RPI Location Update the table</button>\n"+
"  <p id=geo></p>\n"+
"<script>\n"+
"var x = document.getElementById(\"geo\");\n"+
"var table, tr;\n"+
"  // input = document.getElementById(\"myInput\");\n"+
"  // filter = input.value.toUpperCase();\n"+
"\n"+
"function getLocation() {\n"+
"  if (navigator.geolocation) {\n"+
"    navigator.geolocation.getCurrentPosition(showPosition, showError);\n"+
"  } else { \n"+
"    x.innerHTML = \"Geolocation is not supported by this browser.\";\n"+
"  }\n"+
"}\n"+
"function showPosition(position) {\n"+
"    x.innerHTML = \"Latitude: \" + position.coords.latitude + \n"+
"    \"<br>Longitude: \" + position.coords.longitude;\n"+
"\n"+
"  }\n"+
"function getLocationFixed() {\n"+
"  a = 42.73840006302369;\n"+
"  b=-73.69101901260044;\n"+
"  c=41;\n"+
"  d=-71;\n"+
"  x.innerHTML = \"Latitude: \" + a+ \n"+
"  \"<br>Longitude: \" + b+\n"+
"  \"<br>Distence: \"+calcCrow(a,b,c,d);\n"+
"}\n"+
"function getLocationFixedTable() {\n"+
"  a = 42.73840006302369;\n"+
"  b=-73.69101901260044;\n"+
"  // updateTable();\n"+
"  var table = document.getElementById(\"farmertable\");\n"+
"  for (var i = 0, row; row = table.rows[i]; i++) {\n"+
"     //iterate through rows\n"+
"     //rows would be accessed using the \"row\" variable assigned in the for loop\n"+
"     for (var j = 0, col; col = row.cells[j]; j++) {\n"+
"       //iterate through columns\n"+
"       if(j==6 ){\n"+
"          c = parseFloat(col.innerText);\n"+
"        }\n"+
"       if(j==7){\n"+
"          d = parseFloat(col.innerText);\n"+
"          out = calcCrow(a,b,d,c);\n"+
"          // console.log(j+\" \"+out+\" dist\n\");\n"+
"          row.cells[4].innerText = out.toFixed(1);\n"+
"        }\n"+
"       //columns would be accessed using the \"col\" variable assigned in the for loop\n"+
"     }  \n"+
"  }\n"+
"}\n"+
"\n"+
"  \n"+
"  function calcCrow(lat1, lon1, lat2, lon2) \n"+
"  {\n"+
"    var R = 6371; // km\n"+
"    var dLat = toRad(lat2-lat1);\n"+
"    var dLon = toRad(lon2-lon1);\n"+
"    var lat1 = toRad(lat1);\n"+
"    var lat2 = toRad(lat2);\n"+
"\n"+
"    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +\n"+
"      Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); \n"+
"    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); \n"+
"    var d = R * c;\n"+
"    return d;\n"+
"  }\n"+
"\n"+
"  // Converts numeric degrees to radians\n"+
"  function toRad(Value) \n"+
"  {\n"+
"      return Value * Math.PI / 180;\n"+
"  }\n"+
"\n"+
"  function showError(error) {\n"+
"    switch(error.code) {\n"+
"      case error.PERMISSION_DENIED:\n"+
"        x.innerHTML = \"User denied the request for Geolocation.\"\n"+
"        break;\n"+
"      case error.POSITION_UNAVAILABLE:\n"+
"        x.innerHTML = \"Location information is unavailable.\"\n"+
"        break;\n"+
"      case error.TIMEOUT:\n"+
"        x.innerHTML = \"The request to get user location timed out.\"\n"+
"        break;\n"+
"      case error.UNKNOWN_ERROR:\n"+
"        x.innerHTML = \"An unknown error occurred.\"\n"+
"        break;\n"+
"    }\n"+
"  }\n"+
"  </script>"