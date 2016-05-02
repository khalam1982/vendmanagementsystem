<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log Off Screen</title>
</head>
<body>
<% request.getSession().invalidate(); %>
<table height="500" width=1000>
<tr height="45%">
</tr>
<tr height="55%">
<td>
<center><h1>You have been successfully logged off.</h1></center>
</td>
</tr>
</table>
</body>
</html>