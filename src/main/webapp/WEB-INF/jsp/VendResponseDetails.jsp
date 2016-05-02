<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vend Response Details Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script language="javascript">
	function submitPage(dispatchType) {
		document.forms[0].dispatch.value = 'cancel';
		document.forms[0].action = 'VendRequestAction';
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.vendrequest" /></label>
<font color="red"><html:errors /></font>
<html:form action="/VendRequestAction" onsubmit="javascript:submitPage()">
	<table border=1>
		<tr>
			<td><bean:message key="app.transactionid" />:</td>
			<td><bean:write name="VendRequestForm" property="transactionID" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.statuscode" />:</td>
			<td><bean:write name="VendRequestForm" property="statusCode" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.description" />:</td>
			<td><bean:write name="VendRequestForm" property="description" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.vendcode" />:</td>
			<td><bean:write name="VendRequestForm" property="vendCode" /></td>
		</tr>
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand">
				<bean:message key="app.ok" /></html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" />
</html:form>
</body>
</html:html>