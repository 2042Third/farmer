import java.util.prefs.*;
import java.sql.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HW5 implementation for the farmer sarch
 */
@WebServlet("/Farmer")
public class Farmer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public HelloUserGet a;
  public String title_name = "<h2>Farmer Search</h2><p>New search</p>";
  public String formtail = "</form>";
  public StringBuffer resultTable = new StringBuffer();
  public String current_url = "192.168.1.28:8080/";
  public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
      Map<String, String> query_pairs = new LinkedHashMap<String, String>();
      String[] pairs = query.split("&");
      for (String pair : pairs) {
        System.out.println(pair);
          int idx = pair.indexOf("=");
          query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
      }
      return query_pairs;
  } 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Farmer() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    
    String state = "", city = "";
    Integer page=0;
    String parameters = request.getQueryString();
    if (parameters != null && !parameters.isEmpty()) {
      Map<String,String> parameterMap = splitQuery(parameters);
      if (parameterMap.containsKey("state")) {
        state = parameterMap.get("state");
      }
      if (parameterMap.containsKey("city")) {
        city = parameterMap.get("city");
      }
      if(parameterMap.containsKey("page")) {
        page = Integer.parseInt(parameterMap.get("page"));
      }
    }
    response.getWriter().append(
      "<html><title>Farmer search</title><body>" +
        title_name+
        "<form >Enter state<input name=\"state\" type=\"text\" value=\""+state+"\">" +
        "Enter city<input name=\"city\" type=\"text\" size=\"60\" value=\""+city+"\">" +
        "<input type=\"submit\" value=\"Find\">" );

    if(state.length()==0 && city.length()==0)
      System.out.print("First enter");
    else
      get_from_sql(page,state, city, response);
    get_pager(page, request,response);
    response.getWriter().append(formtail);
    //END
    response.getWriter().append("</body></html>");
  }
  /**
   * Implements pagination
   * 
   * */
  public void get_pager(Integer page,HttpServletRequest request ,HttpServletResponse response){
    try{

      if(page <= 0)
        response.getWriter().append("<button type=\"submit\"name=\"page\" value=0 size=\"90\" disabled><</button>");
      else
        response.getWriter().append("<button type=\"submit\"name=\"page\"  value="+(page-1)+" size=\"90\" ><</button>");
      response.getWriter().append(" page "+page+" out of "+
        "<button type=\"submit\" name=\"page\"  value="+(page+1)+" size=\"90\" >></button>"
        );
    }
    catch(Exception e){
      System.out.println("ok, bad getwriter");
    }
  }

  /**
   * note: this code looks disgusting, modified from one the lecture examples.
   * Connects to the data base and search for the input,
   * return the result to ResultSet.
   * 
   * @param page page number
   * @param state state
   * @param city city
   * @param response the response
   * */
  public void get_from_sql(Integer page,String state, String city,HttpServletResponse response){
      Preferences root  = Preferences.userRoot();
      Preferences node = Preferences.userNodeForPackage(this.getClass());
      String url = node.get("MySQLConnection", "jdbc:mysql://localhost:9234/advjava?useSSL=false");


      Connection con = null;
      
      try
      {
        con = DriverManager.getConnection(url, "admin", "f3ck");
        String query = "SELECT *" + 
                "FROM farmerdata.farmers WHERE city LIKE ? AND state LIKE ? LIMIT 20 OFFSET ? ;";
        response.getWriter().append("<button onclick=\"getLocation()\" class=\"primary\">Get My Location</button>\n"+
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
"          // console.log(j+\" \"+out+\" dist\");\n"+
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
"  </script>");
        resultTable = new StringBuffer("<table id=\"farmertable\">"+
        "<tr><th>Name</th><th>City/County</th><th>State</th>" +
        "<th>Reviews</th><th>website</th><th>Detail</th></tr>");
        try (PreparedStatement stat = con.prepareStatement(query)) {
          stat.setString(1, state+"%");
          stat.setString(2, city+"%");
          stat.setInt(3, page*20);
          try (ResultSet rs = stat.executeQuery()) {
            System.out.println("Executed the following SQL statement:");
            System.out.println(query);
            while (rs.next()) {
              resultTable.append("<tr><td>").append(rs.getString("name")).
                append("</td><td>").append(rs.getString("city")+", "+rs.getString("county")).
                append("</td><td>").append(rs.getString("state")).
                append("</td><td>").append(rs.getString("reviewscore")+"/5 ("+rs.getString("reviewcount")+")").
                append("</td><td>").append("0").
                append("</td><td>").append("<a href=\"/farmer/farmerdetail?id="+rs.getString("id")+"\" >detail</a>").
                append("</td><td style=\"display:none;\">").append(rs.getString("x")).
                append("</td><td style=\"display:none;\">").append(rs.getString("y")).

                append("</td></tr>");
            }
          }
          resultTable.append("</table>");
        }
      }
      catch (SQLException ex) {
        for (Throwable t : ex)
          System.out.println(t.getMessage());
        System.out.println("Opening connection unsuccessful!");
      }
      catch(Exception e){
        System.out.println("getWriter().append() failure");

      }
      finally {
        node.put("MySQLConnection", url);
        if (con != null) {
          try {
            con.close();
          }
          catch (SQLException ex) {
            for (Throwable t : ex)
              System.out.println(t.getMessage());
            System.out.println("Closing connection unsuccessful!");
          }
        }
      }
          System.out.println("State is: " + state);
        try{
        response.getWriter().append( 
          "<head><style>\r\n" + 
          "table {\r\n" + 
          "  font-family: arial, sans-serif;\r\n" + 
          "  border-collapse: collapse;\r\n" + 
          "  width: 100%;\r\n" + 
          "}\r\n" + 
          "\r\n" + 
          "td, th {\r\n" + 
          "  border: 1px solid #dddddd;\r\n" + 
          "  text-align: left;\r\n" + 
          "  padding: 8px;\r\n" + 
          "}\r\n" + 
          "\r\n" + 
          "tr:nth-child(even) {\r\n" + 
          "  background-color: #dddddd;\r\n" + 
          "}\r\n" + 
          "</style></head>" +
          resultTable.toString() );
      }
      catch(Exception e){
        System.out.println("get writer fucked");
      }
    }
}