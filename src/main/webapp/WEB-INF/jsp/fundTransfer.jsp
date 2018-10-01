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

<form action="transferAmount.do" method="post">
		
		<label>From Account number:</label>
		<input type="number" name="fromAcc" size=15 required>
		<label>To Account number:</label>
		<input type="number" name="toAcc" size=15 required>
		<label>Amount:</label>
		<input type="number" name="amount" size=15 required>
		<br>
		<input type="submit" value="Transfer Fund">
	</form>
</body>
</html>