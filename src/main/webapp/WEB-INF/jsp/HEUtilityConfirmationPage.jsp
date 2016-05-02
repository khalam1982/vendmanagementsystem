<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Utility Request Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script type="text/javascript">
	function submitPage() {
		document.forms[0].action = 'UtilityRequestAction';
	}
	function keyEntered(){
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<font color="red"><html:errors /></font>
<html:form action="UtilityRequestAction" >
	<label><bean:message key="app.heutilityconfirm" /></label>
	<table border=1>
		<logic:equal name="HEUtilityRequestForm" property="processStatus" value="0">
		<tr>
			<td><font color="red" size="5"><bean:message key="app.utycfmmsg" /></font></td>
		</tr>
		</logic:equal>
		<logic:greaterThan name="HEUtilityRequestForm" property="processStatus" value="0">
		<tr>
			<td><font color="red" size="5"><bean:message key="app.utypendingmsg" /></font></td>
		</tr>
		</logic:greaterThan>
		<logic:equal name="HEUtilityRequestForm" property="processStatus" value="-1">
		<tr>
			<td><font color="red" size="5"><bean:message key="app.utyfailedmsg" /></font></td>
		</tr>
		</logic:equal>
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.ok" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="requestTypePage"/>
</html:form>
</body>
</html>