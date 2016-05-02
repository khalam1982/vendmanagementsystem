<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vend Request Details Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script language="javascript">
	function submitPage() {
		document.forms[0].action = "VendRequestAction";
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.vendrequest" /></label>
<font color="red"><html:errors /></font>
<html:form action="/VendRequestAction">
	<table border=1>
		<tr>
			<td><bean:message key="app.pan" /><font color="red">*</font>:</td>
			<td><html:text property="pan"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="app.creditvalue" /><font color="red">*</font>:<bean:message key="app.pound" /></td>
			<td><html:text property="creditValue"></html:text></td>
		</tr>
		<tr>
			<td  colspan=2 align="right"><html:submit style="cursor:hand" onclick="javascript:submitPage()">
				<bean:message key="app.submit" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="submit" />
	<html:hidden property="payChannel" value="1-2" />
	<html:hidden property="holdingPeriod" value="-1" />
</html:form>
</body>
</html:html>