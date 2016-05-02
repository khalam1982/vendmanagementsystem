<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meter Details Search Page</title>
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
		document.forms[0].action = "MeterDispatchSearchAction";
	}
	
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.meterdetails" /></label>
<font color="red"><html:errors /></font>
<html:form action="/MeterDispatchSearchAction" >
	<table border=1>
		<tr>
			<td><bean:message key="app.msn" /><font color="red">*</font>:</td>
			<td><html:text property="msn" onkeypress="javascript:submitPage()"></html:text></td>
		</tr>
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.search" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="detailPage" />
</html:form>
</body>
</html:html>