<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Search Page</title>
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
		document.forms[0].action = "UserAdminAction";
	}
	function keyEntered(){
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.useradminsearchpage" /></label>
<font color="red"><html:errors /></font>
<html:form action="/UserAdminAction" >
<bean:define name="UserAdminForm" property="vmsGroup" id="vmsGroup" type="String"/>
	<table border=1>
		<tr>
			<td><bean:message key="app.username" /><font color="red">*</font>:</td>
			<td><html:text property="userName" onkeypress="submitPage()"></html:text></td>
		</tr>
		<tr>
			<td><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.search" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="userDetailPage" />
	<html:hidden property="vmsGroup" value="${vmsGroup}" />
</html:form>
</body>
</html:html>