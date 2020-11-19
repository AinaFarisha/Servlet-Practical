// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*; 

// Extend HttpServlet class
public class loginInfo extends HttpServlet {
 
	boolean login=false;
	Statement statement=null;
	Connection connection = null;
	
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	   
	   String id=request.getParameter("ID");
	   String pw=request.getParameter("pw");
      
      // Set response content type
      response.setContentType("text/html");
      
      try{
    	  Class.forName("com.mysql.jdbc.Driver");

    	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useTimezone=true&serverTimezone=UTC", "root", "12345");	
    	  	
    	  		// Create a statement to retrieve objects
    	  		statement = connection.createStatement();        

    	  		String sql = "select name from Student where id='"+ id +"' and password='"+ pw +"'";
    	  		
    	  		// Retrieve results from the table
    	  		ResultSet rs = statement.executeQuery (sql); 
    	  		
    	  		if(rs.next()) {
    	  	    	login = true;
    	  	    	
    	  	    } 
    	  		}
    	  catch(Exception e) {
    	  	   e.getMessage();
    	  	}

      if(login) {

    	  PrintWriter out = response.getWriter();
          String title = "Using GET Method to Read Form Data";
          String docType =
             "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
          
         
          out.println(docType +
             "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                   "<h1 align = \"center\">" + title + "</h1>\n" +
                   "<ul>\n" +
                      "  <li><b>ID</b>: "
                      + request.getParameter("ID") + "\n" +
                      "  <li><b>Password</b>: "
                      + request.getParameter("pw") + "\n" +
                   "</ul>\n" +
                "</body>" +
             "</html>"
          );
          
          try {
  		    statement = connection.createStatement();
  		    ResultSet rs = statement.executeQuery("select id, name, department, english, math, science, malay from Student");
            
  	
  		 
              
  		    
  		    
  		    out.println(docType +
  		    		"<table border=\"1\">\n" +
  		    		  "<tr>" +
  		  	  	        "<th>ID</th>" + 
  		  	  	        "<th>Name</th>" +
  		  	  	        "<th>Department</th>" +
  		  	  	        "<th>English</th>" +
  		  	  	        "<th>Math</th>" +
  		  	        	"<th>Science</th>" +
  		  	  	        "<th>Malay</th>" +
  		  	  	        "<th>Average Marks</th>" +
  		  	  	      "</tr>" 	);
          
  		  while(rs.next()) { 
  			 double english=rs.getDouble("english");
             double math=rs.getDouble("math");
             double science=rs.getDouble("science");
             double malay=rs.getDouble("malay");
             double total=(english+math+science+malay)/4;
             
             out.println(docType +
            		 "<tr>" + 
              	    "<td>" + rs.getString("id") +"</td>" +
              	    "<td>" + rs.getString("name") + "</td>" + 
              	    "<td>" + rs.getString("department") + "</td>" + 
              	    "<td>" + english + "</td>" +
              	    "<td>" + math + "</td>" +
              	    "<td>" + science +"</td>" + 
              	    "<td>" + malay +"</td>" + 
              	    "<td>" + total + "</td>" +
              	    "<tr>"  
            		 );
          }
  		  
  		 out.println(docType +
  				"</table>" +
  	             "<form action=\"addStudent\">" +
  	             "<input type=\"submit\" value=\"Add New Student\">" +
  	             "</form>" 
        		 );
    	  
      }
          catch(Exception e) {
  	    	out.println(e.getMessage());
  	    }
      
   }
      else {
    	  System.out.println("login failed!!");
      }
} }
