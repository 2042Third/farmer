"<h2>Geolocation</h2>\n"+
"<p>\n"+
"<button onclick=\"getLocation()\" class=\"primary\">Get My Location</button>\n"+
"  <p id=geo></p>\n"+
"<script>\n"+
"var x = document.getElementById(\"geo\");\n"+
"\n"+
"function getLocation() {\n"+
"  if (navigator.geolocation) {\n"+
"    navigator.geolocation.getCurrentPosition(showPosition, showError);\n"+
"  } else { \n"+
"    x.innerHTML = \"Geolocation is not supported by this browser.\";\n"+
"  }\n"+
"}\n"+
"\n"+
"function showPosition(position) {\n"+
"  x.innerHTML = \"Latitude: \" + position.coords.latitude + \n"+
"  \"<br>Longitude: \" + position.coords.longitude;\n"+
"}\n"+
"\n"+
"function showError(error) {\n"+
"  switch(error.code) {\n"+
"    case error.PERMISSION_DENIED:\n"+
"      x.innerHTML = \"User denied the request for Geolocation.\"\n"+
"      break;\n"+
"    case error.POSITION_UNAVAILABLE:\n"+
"      x.innerHTML = \"Location information is unavailable.\"\n"+
"      break;\n"+
"    case error.TIMEOUT:\n"+
"      x.innerHTML = \"The request to get user location timed out.\"\n"+
"      break;\n"+
"    case error.UNKNOWN_ERROR:\n"+
"      x.innerHTML = \"An unknown error occurred.\"\n"+
"      break;\n"+
"  }\n"+
"}\n"+
"</script>\n"+
"</p>"