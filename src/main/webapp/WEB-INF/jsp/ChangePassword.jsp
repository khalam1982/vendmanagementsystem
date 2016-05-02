<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password Page</title>
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
		document.forms[0].action = "ChangePwdAction";
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.changepwd" /></label>
<font color="red"><html:errors /></font>
<bean:define id="userName" name="ChangePasswordForm" property="userName"></bean:define>
<html:form action="/ChangePwdAction">
	<table border=1>
		<tr>
			<td><bean:message key="app.username" />:</td>
			<td><bean:write name="ChangePasswordForm" property="userName" /></td>
		</tr>		<tr>			<td><bean:message key="app.oldpassword" /><font color="red">*</font>:</td>			<td><html:password property="oldPassword" onkeypress="submitPage()"></html:password></td>		</tr>
		<tr>
			<td><bean:message key="app.newpassword" /><font color="red">*</font>:</td>
			<td><html:password property="newPassword" onkeypress="submitPage()"></html:password></td>
		</tr>
		<tr>
			<td><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.submit" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="update" />
	<html:hidden property="userName" value="${userName}" />
</html:form>
</body>
</html:html>