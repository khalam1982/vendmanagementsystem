<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HE CIM Utility Request Page</title>
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
		document.forms[0].action = 'HECIMUtilityRequestAction';
	}
	function keyEntered(){
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<font color="red"><html:errors /></font>
<html:form action="HECIMUtilityRequestAction" >
	<bean:define name="HEUtilityRequestForm" property="userName" id="userName" type="String"/>
	<bean:define name="HEUtilityRequestForm" property="count" id="count" type="String"/>
	<label><bean:message key="app.heutilitydetail" /></label>
	<table border=1>
		<tr>
			<td><bean:message key="app.heutilitycountmsg" />:</td>
			<td><bean:write name="HEUtilityRequestForm" property="count" /></td>
		</tr>
		<logic:greaterThan name="HEUtilityRequestForm" property="count" value="0">
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.schedule" />
			</html:submit></td>
		</tr>
		</logic:greaterThan>
	</table>
	<html:hidden property="count"  value="${count}"/>
	<html:hidden property="userName" value="${userName}"/>
	<html:hidden property="dispatch" value="heConfirmPage"/>
</html:form>
</body>
</html>