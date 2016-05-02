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
<html:form action="/UtilityRequestAction" >
	<bean:define name="UtilityRequestForm" property="userName" id="userName" type="String"/>
	<label><bean:message key="app.utilitysupportpage" /></label>
	<logic:messagesPresent message="true">
		<html:messages id="ErrorMessage" message="true">
			<font color="red" size="5"><bean:write name="ErrorMessage" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
	<table border=1>
		<tr>
			<td><bean:message key="app.utilitytype" /><font color="red">*</font>:</td>
			<td><html:select property="requestType" name="UtilityRequestForm">
				<html:option value="00">
					<bean:message key="app.utilitytype.option1" />
				</html:option>
				<html:option value="01">
					<bean:message key="app.utilitytype.option2" />
				</html:option>
				<html:option value="02">
					<bean:message key="app.utilitytype.option3" />
				</html:option>
				<html:option value="03">
					<bean:message key="app.utilitytype.option4" />
				</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.submit" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="requestPage"/>
	<html:hidden property="userName" value="${userName}"/>
</html:form>
</body>
</html>