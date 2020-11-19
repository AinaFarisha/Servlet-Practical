<%@page import="java.sql.*"%> 
<%@page import="java.io.*"%> 
<%@page import="java.util.*"%> 


<html>

<body>
<%
String id=request.getParameter("ID");
String pw=request.getParameter("pw");
boolean login=false;
Statement statement=null;
Connection connection = null;

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
	    	out.println(e.getMessage());
		    }
%>

<% 
    if(login) {
     out.println("Successfully Login :)");
%>
     <p>Login ID: <%=request.getParameter("ID")%></p> 
     <p>Login Password: <%=request.getParameter("pw")%> </p>
     
     <%
     try {
		    statement = connection.createStatement();
		    ResultSet rs = statement.executeQuery("select id, name, department, english, math, science, malay from Student");
      %>
        <table border="1">
	  	<tr>
	  	  <th>ID</th>
	  	  <th>Name</th>
	  	  <th>Department</th>
	  	  <th>English</th>
	  	  <th>Math</th>
	  	  <th>Science</th>
	  	  <th>Malay</th>
	  	  <th>Average Marks</th>
	  	</tr>
 <%       while(rs.next()) { %>
              
              <%double english=rs.getDouble("english");%>
              <%double math=rs.getDouble("math");%>
              <%double science=rs.getDouble("science");%>
              <%double malay=rs.getDouble("malay");%>
              <%double total=(english+math+science+malay)/4; %>
 
              <tr>
       	      <td><%=rs.getString("id")%></td>
       	      <td><%=rs.getString("name")%></td>
       	      <td><%=rs.getString("department")%></td>
       	      <td><%=english%></td>
       	      <td><%=math%></td>
       	      <td><%=science%></td>
       	      <td><%=malay%></td>
       	      <td><%=total%></td>
       	    <tr> 
 <% } %>
      </table>
       <form action="addStudent">
       <input type="submit" value="Add New Student">
       </form>
      
 
 <% }
     catch(Exception e) {
	    	out.println(e.getMessage());
	    }
     %>
<%    	
    } else{
    	out.println("login failed!!");
    }
	    
%>

</body>
</html>

