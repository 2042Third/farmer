import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/HelloGet")
public class HelloUserGet extends HttpServlet {
  public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
      Map<String, String> query_pairs = new LinkedHashMap<String, String>();
      String[] pairs = query.split("&");
      for (String pair : pairs) {
          int idx = pair.indexOf("=");
          query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
      }
      return query_pairs;
  }
  
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  public HelloUserGet() {
      super();
      // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    String parameters = request.getQueryString();
    if (parameters != null && !parameters.isEmpty()) {
      Map<String,String> parameterMap = splitQuery(parameters);
      if (parameterMap.containsKey("name")) {
        response.getWriter().append("<html><title>Greeting Web App with the GET form</title><body>" +
            "I have seen you before!<br/>" +
            "<h1>Hello, " + parameterMap.get("name") +  
            "</h1>" +
            "</body></html>");
      }
      else {
        throw new UnsupportedOperationException();
      }
    }
    else {
      response.getWriter().append("<html><title>Greeting Web App with the GET form</title><body>" +
          "<form>Enter your name:<br/><input name=\"name\" type=\"text\">" +
          "<br/><input type=\"submit\" value=\"Submit\"></form>" +
          "</body></html>");
    }
  }
}
