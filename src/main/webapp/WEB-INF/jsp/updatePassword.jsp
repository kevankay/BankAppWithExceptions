<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<img src="/images/icicilogo.jpg" align="left"  width="100" height="100">
</head>
<body>
	<br>
	<h1>Change Password</h1>
	<form action="updatePassword.do" method="post">
		<br>
		<br> <label>Old Password: </label>
		 <input type="password"name="oldPassword" placeholder="enter your Old Password"required /> 
		 <br> <br> 
		 <label>New Password: </label>
		  <input type="password" name="newPassword" placeholder="enter your New Password" required /> <br> <br>
		<br> <input type="submit" value="update" />
	</form>
</body>
</html>