<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meter Details Confirmation Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script type="text/javascript">
	function submitPage(dispatchType) {
		document.forms[0].dispatch.value = dispatchType;
		document.forms[0].action = 'MeterDispatchSearchAction';
	}
	function keyEntered(){
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<html:form action="/MeterDispatchSearchAction" >
	<logic:messagesPresent message="true">
		<html:messages id="ConfirmationMessage" message="true">
			<font color="red" size="5"><bean:write name="ConfirmationMessage" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
	<table>
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand"
				onclick="submitPage('searchPage')">
				<bean:message key="app.ok" /></html:submit>
				
			</td>
		</tr>
	</table>
	<html:hidden property="dispatch" />
</html:form>
</body>
</html>