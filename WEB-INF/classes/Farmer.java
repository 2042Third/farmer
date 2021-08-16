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
  public StringBuffer resultTable = new StringBuffer(
        );
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
        "<input type=\"submit\" value=\"Find\"></form>" );

    if(state.length()==0 && city.length()==0)
      System.out.print("First enter");
    else
      get_from_sql(state, city, response);
    get_pager(response);
    //END
    response.getWriter().append("</body></html>");
  }
  /**
   * Implements pagination
   * 
   * */
  public void get_pager(HttpServletResponse response){
    try{
      response.getWriter().append("<b>${param.page}</b>");
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
   * @param state state
   * @param city city
   * @param response the response
   * */
  public void get_from_sql(String state, String city,HttpServletResponse response){
      Preferences root  = Preferences.userRoot();
      Preferences node = Preferences.userNodeForPackage(this.getClass());
      String url = node.get("MySQLConnection", "jdbc:mysql://localhost:9234/advjava?useSSL=false");


      Connection con = null;
      
      try
      {
        con = DriverManager.getConnection(url, "admin", "f3ck");
        String query = "SELECT name, website,city, county, state " + 
                "FROM farmerdata.farmers WHERE city LIKE ? AND state LIKE ?";
        resultTable = new StringBuffer("<table>"+
        "<tr><th>Name</th><th>City/County</th><th>State</th>" +
        "<th>Reviews</th><th>website</th></tr>");
        try (PreparedStatement stat = con.prepareStatement(query)) {
          stat.setString(1, state+"%");
          stat.setString(2, city+"%");
          try (ResultSet rs = stat.executeQuery()) {
            System.out.println("Executed the following SQL statement:");
            System.out.println(query);
            while (rs.next()) {
              resultTable.append("<tr><td>").append(rs.getString(1)).
                append("</td><td>").append(rs.getString("city")+", "+rs.getString("county")).
                append("</td><td>").append(rs.getString("state")).
                append("</td><td>").append("5/5").
                append("</td><td>").append(rs.getString("website")).
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