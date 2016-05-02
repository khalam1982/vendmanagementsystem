<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Title Page</title>
<style type="text/css">
label{
	
	font-weight:bold;
    padding-left: 1em;
    font-family: Blackletter;
     color:white;
     font-size:2.5em
  
}
   

</style>
</head>
<body background="images/centricaheader.bmp">
<table width="100%">
<tr>
<td align="center">
<label><bean:message key="app.vms" /></label>
</td>
</tr>
<tr>
<td align=right>
<B><bean:message key="app.logininfo" /><%=(request.getSession(false).getAttribute("USERNAME")).toString().toUpperCase() %></B>
</td>
</tr>
</table>
</body>
</html>