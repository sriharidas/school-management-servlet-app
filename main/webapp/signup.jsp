<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<%@include file="navigation.jsp" %>
 <div class="container">
  	<h3>Please fill the following details to create a new account</h3> 
  	<p class="error-msg">
  		<%
  			if(request.getAttribute("message") != null)
  				out.print((String)request.getAttribute("message"));

  		%>
  	</p>
 	<form action="signup" method="post">
	 	<p>
	 		<label for="fname">First Name</label>
	 		<input type="text" id="fname" name="fname" placeholder="Enter your first name" />
	 	</p>
	 	<p>
	 		<label for="lname">last Name</label>
	 		<input type="text" id="lname" name="lname" placeholder="Enter your last name" />
	 	</p>
	 	<p>
	 		<label for="uname">username</label>
	 		<input type="text" id="uname" name="uname" placeholder="Enter your  username" />
	 	</p>
	 	<p>
	 		<label for="password">Password</label>
	 		<input type="password" id="password" name="password" placeholder="Enter your password" />
	 	</p>
	 	<p>
	 		<label for="cpassword">Password</label>
	 		<input type="password" id="cpassword" name="cpassword" placeholder="Re-enter your password" />
	 	</p>
	 	<input type="submit" value="sign up" />
 	</form>
 	<hr/>
 	
 	<p>Already have an account? click here to <a href="login.jsp">Login</a></p>.
 </div>
</body>
</html>