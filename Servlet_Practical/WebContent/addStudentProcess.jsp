
<%@page import="java.sql.*"%> 
<%@page import="java.io.*"%> 
<%@page import="java.util.*"%> 

<html>
<head>
<title>Add Student</title>
</head>
<body>
<%
String id=request.getParameter("newName");
String pw=request.getParameter("newPass");
String dp=request.getParameter("newDep");
String english=request.getParameter("eng");
String math=request.getParameter("math");
String science=request.getParameter("science");
String malay=request.getParameter("malay");

boolean login=false;
Statement statement=null;
Connection connection = null;

try{
Class.forName("com.mysql.jdbc.Driver");

connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useTimezone=true&serverTimezone=UTC", "root", "12345");	
	
		// Create a statement to retrieve objects
		statement = connection.createStatement();        
		
		// update results to the table 
		int stmt= statement.executeUpdate("insert into Student(name, department, password, english, math, science, malay) values('" + id + "', '" + dp + "', '"+ pw + "', '" + english + "', '" + math + "', '" + science +"', '"+ malay +"')");
		out.println("New Student Successfully Registered!");
		
%>
       <form action="login">
       <label>Login again to see student info!</label>
       <input type="submit" value="Login">
       </form>

<%
		}
            catch(Exception e) {
	    	out.println(e.getMessage());
		    }


%>
</body>
</html>