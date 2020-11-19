// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*; 


// Extend HttpServlet class
public class addStudentProcess extends HttpServlet {
	   
	   boolean login=false;
	   Statement statement=null;
	   Connection connection = null;
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	   
	   String id=request.getParameter("newName");
	   String pw=request.getParameter("newPass");
	   String dp=request.getParameter("newDep");
	   String english=request.getParameter("eng");
	   String math=request.getParameter("math");
	   String science=request.getParameter("science");
	   String malay=request.getParameter("malay");

      // Set response content type
      response.setContentType("text/html");
  
      try{
    	  Class.forName("com.mysql.jdbc.Driver");

    	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useTimezone=true&serverTimezone=UTC", "root", "12345");	
    	  	
    	  		// Create a statement to retrieve objects
    	  		statement = connection.createStatement();        
    	  		
    	  		// update results to the table 
    	  		int stmt= statement.executeUpdate("insert into Student(name, department, password, english, math, science, malay) values('" + id + "', '" + dp + "', '"+ pw + "', '" + english + "', '" + math + "', '" + science +"', '"+ malay +"')");
    	  		
    	  	    	login = true;
    	  		}
      
      catch(Exception e) {
    	  System.out.println(e.getMessage());
     }
      
      if (login) {
    	  PrintWriter out = response.getWriter();
          String title = "Using GET Method to Read Form Data";
          String docType =
             "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
          
          out.println(docType +
                  "<html>\n" +
                     "<head><title>" + title + "</title></head>\n" +
                     "<body bgcolor = \"#f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<form action=\"login.html\">" +
                        "<label>Login again to see student info!</label>" +
                        "<input type=\"submit\" value=\"Login\">" +
                        "</form>" +
                     "</body>" +
                  "</html>"
               );
      }
     

         
    
   }
}

