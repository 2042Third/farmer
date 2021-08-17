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
@WebServlet("/farmerdetail")
public class farmerdetail extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public HelloUserGet a;
  public String title_name = "<h2></h2>";
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
    public farmerdetail() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    
    Integer id =-1;
    String parameters = request.getQueryString(), message="", user_name="";
    if (parameters != null && !parameters.isEmpty()) {
      Map<String,String> parameterMap = splitQuery(parameters);
      if (parameterMap.containsKey("id")) {
        id = Integer.parseInt(parameterMap.get("id"));
      }
      if (parameterMap.containsKey("message")) {
        message = parameterMap.get("message");
      }
      if (parameterMap.containsKey("user_name")) {
        user_name = parameterMap.get("user_name");
      }
    }
    response.getWriter().append(
      "<!DOCTYPE html><html><title>Farmer detail</title><body>" +
        "");
    //Geolocation
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
          "</style><script type=\"text/javascript\">var x = document.getElementById(\"geo\"); function getLocation() { if (navigator.geolocation) { navigator.geolocation.getCurrentPosition(showPosition, showError); } else { x.innerHTML = \"Geolocation is not supported by this browser.\"; }} function showPosition(position) { x.innerHTML = \"Latitude: \" + position.coords.latitude + \"<br>Longitude: \" + position.coords.longitude;}function showError(error) { switch(error.code) { case error.PERMISSION_DENIED: x.innerHTML = \"User denied the request for Geolocation.\" break; case error.POSITION_UNAVAILABLE: x.innerHTML = \"Location information is unavailable.\" break; case error.TIMEOUT: x.innerHTML = \"The request to get user location timed out.\" break; case error.UNKNOWN_ERROR: x.innerHTML = \"An unknown error occurred.\" break; }}</script></head>"  );
    response.getWriter().append("<h2>Geolocation</h2><p><button onclick=\"getLocation()\" class=\"primary\">Get My Location</button> </p> <p id=geo></p>");
    get_textarea(id,message,user_name,response);
    get_review(id,message,user_name,response);
    get_from_sql(id, response);
    response.getWriter().append(formtail);
    //END
    response.getWriter().append("</body></html>");
  }
  /**
   * Implements pagination
   * 
   * */
  public void get_pager(HttpServletRequest request ,HttpServletResponse response){
    
  }
  /**
   * Get the text area of a farmer
   * @param id id of the farmer
   * @param message the input review
   * @param user_name the name of the user who wrote reviews
   * @param response http get response object
   * */
  public void get_review (Integer id, String message, String user_name, HttpServletResponse response){
    String position = "start-up";
    Preferences root  = Preferences.userRoot();
    Preferences node = Preferences.userNodeForPackage(this.getClass());
    String url = node.get("MySQLConnection", "jdbc:mysql://localhost:9234/farmerdata?useSSL=false");


      Connection con = null;
      
      try
      {
        con = DriverManager.getConnection(url, "admin", "f3ck");
        String query = "select * " + 
                "from farmerdata.reviews where name=?;";
        response.getWriter().append("<table>"+
        "");
        response.getWriter().append("<tr><td>").append("Customer Name").
                append("</td><td>").append("Customer Review").
                append("</td></tr>");
        try (PreparedStatement stat = con.prepareStatement(query)) {
          stat.setString(1, id.toString());
          try (ResultSet rs = stat.executeQuery()) {
            System.out.println("Executed the following SQL statement:");
            System.out.println(query);
            while (rs.next()) {
              // System.out.println("Retriving lines of review");
              response.getWriter().append("<tr><td>").append(rs.getString("review1")).
                append("</td><td>").append(rs.getString("review")).
                append("</td></tr>");
            }
          }
          response.getWriter().append("</table>");
        }
      }
      catch (SQLException ex) {
        for (Throwable t : ex)
          System.out.println(t.getMessage());
        System.out.println("Opening connection unsuccessful!");
      }
      catch(Exception e){
        System.out.println("fucked reviews");
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

    

    
  }

  /**
   * Get the text area of a farmer
   * @param id id of the farmer
   * @param message the input review
   * @param user_name the name of the user who wrote reviews
   * @param response http get response object
   * */
  public void get_textarea(Integer id, String message, String user_name, HttpServletResponse response){
    try{
      response.getWriter().append("<p>Write a review:</p>");
      response.getWriter().append("<form>");
      response.getWriter().append("<p>Please Enter Your Name:</p><textarea name=\"user_name\" ></textarea></br>");
      response.getWriter().append("<p>Review:</p><textarea name=\"message\" rows=\"10\" cols=\"30\"></textarea>");
      response.getWriter().append("<br><br>");
      response.getWriter().append("<input type=\"submit\">");
      response.getWriter().append("<input type=\"hidden\" name=\"id\" value=\""+id+"\">");
      response.getWriter().append("</form>");
    }
    catch(Exception e){
      System.out.println("Initial text area failure.");
    }
    Preferences root  = Preferences.userRoot();
    Preferences node = Preferences.userNodeForPackage(this.getClass());
    String url = node.get("MySQLConnection", "jdbc:mysql://localhost:9234/farmerdata?useSSL=false");
    Connection con = null;
    String position = "message processing";
    try{
      con = DriverManager.getConnection(url, "admin", "f3ck");
      String query;
      
      //If there is any message
      if(message.length()!=0 && user_name.length()!=0){
        query = "INSERT INTO farmerdata.reviews VALUES (?,?,?,?) ; ";
        // query = "INSERT INTO farmerdata.reviews SET name=? , score=? , review=? , review1=? ; ";
        position = "after query";
        PreparedStatement stat = con.prepareStatement(query);
        position = "after stat init";
        stat.setString(1, id.toString());
        stat.setString(2, "5");
        stat.setString(3, message);
        stat.setString(4, user_name);
        position = "after stat";
        stat.executeUpdate();
        node.put("MySQLConnection", url);
        
      }
      
      
    }
    catch(Exception e){
      System.out.println("get textarea failure at "+ position);
    }
    finally{
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
  }

  /**
   * note: this code looks disgusting, modified from one the lecture examples.
   * Connects to the data base and search for the input,
   * return the result to ResultSet.
   * 
   * @param id id
   * @param response the response
   * */
  public void get_from_sql(Integer id,HttpServletResponse response){
      Preferences root  = Preferences.userRoot();
      Preferences node = Preferences.userNodeForPackage(this.getClass());
      if(id == -1)
        return;
      String url = node.get("MySQLConnection", "jdbc:mysql://localhost:9234/farmerdata?useSSL=false");


      Connection con = null;
      
      try
      {
        con = DriverManager.getConnection(url, "admin", "f3ck");
        String query = "select * " + 
                "from farmerdata.farmers where id=?; ";
        resultTable = new StringBuffer("<table>");
        ResultSetMetaData resultSetMetaData;
        try (PreparedStatement stat = con.prepareStatement(query)) {
          stat.setInt(1, id);
          
          try (ResultSet rs = stat.executeQuery()) {
            System.out.println("Executed the following SQL statement for id "+id+" : ");
            System.out.println(query);
            // while (rs.next()) {
            // for (String i: rs.getArray(0))
            // }  
            rs.next();
            if(rs == null)
              return;
            resultSetMetaData = rs.getMetaData();
            for (int i = 1; i<=58; i++) {
              resultTable.append("<tr><td>"+resultSetMetaData.getColumnName(i)+"</td><td>"+rs.getString(i)+"</td></tr>");
              // System.out.println(resultSetMetaData.getColumnName(i)+" %% "+rs.getString(i));
                
            }
            response.getWriter().append("<h2>"+"Portfolio of farmer "+rs.getString("name")+"</h2>");
          }
          catch(Exception e){
            System.out.println("get writer fucked");
          }
          resultTable.append("</table>");
        }
      }
      catch (SQLException ex) {
        for (Throwable t : ex)
          System.out.println(t.getMessage());
        System.out.println("Opening connection unsuccessful!");
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
      
    }
}