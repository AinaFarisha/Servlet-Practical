
<%@page import="java.sql.*"%> 
<%@page import="java.io.*"%> 
<%@page import="java.util.*"%> 

<html>
<head>
<title>Add New Student</title>
</head>
<body>

<form action="addStudentProcess.jsp" method="get">

	<p>Name: <input type="text" name="newName" /></p>
	<p>Password: <input type="text" name="newPass" /></p>
	<p>Department: <input type="text" name="newDep" /></p>
	<p>English: <input type="text" name="eng" /></p>
	<p>Math: <input type="text" name="math" /></p>
	<p>Science: <input type="text" name="science" /></p>
	<p>Malay: <input type="text" name="malay" /></p>
	<p><button type="submit">Submit</button></p>
</form>

</body>
</html>