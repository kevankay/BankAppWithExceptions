<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@ include file="home.jsp" %> 
</head>
<body>
<h1>Your Account Balance is:  ${sessionScope.customer.customerAccount.accountBalance}</h1>
</body>
</html>