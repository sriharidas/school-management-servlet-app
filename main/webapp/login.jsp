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
  	<h3>Please login to continue</h3> 
  	<p class="error-msg">
  		<%
  		String message = (String)request.getAttribute("message");
  			if(message != null)
  				out.println(message);
  		%>
  	</p>
 	<form action="login" method="post">
	 	<p>
	 		<label for="uname">Username</label>
	 		<input type="text" id="uname" name="uname" placeholder="Enter your username" />
	 	</p>
	 	<p>
	 		<label for="password">Password</label>
	 		<input type="password" id="password" name="unmapassworde" placeholder="Enter your password" />
	 	</p>
	 	<input type="submit" value="login" />
 	</form>
 	<hr/>
 	
 	<p>New to our website? click here to <a href="signup.jsp">Sign up</a></p>.
 </div>

</body>
</html>