<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vend Request Confirm Page</title>
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
		document.forms[0].dispatch.value = dispatchType;
		document.forms[0].pan.value = '<bean:write name="VendRequestForm" property="pan" />';
		document.forms[0].creditValue.value = '<bean:write name="VendRequestForm" property="creditValue" />';
		document.forms[0].holdingPeriod.value = '<bean:write name="VendRequestForm" property="holdingPeriod" />';
		document.forms[0].payChannel.value = '<bean:write name="VendRequestForm" property="payChannel" />';
		document.forms[0].creditType.value = '<bean:write name="VendRequestForm" property="creditType" />';
		document.forms[0].action = 'VendRequestAction';
	}	
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.vendrequest" /></label>
<font color="red"><html:errors /></font>
<html:form action="/VendRequestAction" >
	<table border=1>
		<tr>
			<td><bean:message key="app.pan" />:</td>
			<td><bean:write name="VendRequestForm" property="pan" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.creditvalue" />:</td>
			<td><bean:message key="app.pound" /><bean:write name="VendRequestForm" property="creditValue" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.paychannel" />:</td>
			<td><bean:write name="VendRequestForm" property="payChannel" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.credittype" />:</td>
			<td><bean:write name="VendRequestForm" property="creditType" /></td>
		</tr>
		<%-- <tr>
			<td><bean:message key="app.timestamp" />:</td>
			<td><bean:write name="VendRequestForm" property="dateTime" /></td>
		</tr>  --%>
		<tr>
			<td><html:submit style="cursor:hand"
				onclick="javascript:submitPage('confirm')">
				<bean:message key="app.confirm" />
			</html:submit></td>
			<td><html:submit style="cursor:hand"
				onclick="javascript:submitPage('cancel')">
				<bean:message key="app.cancel" /></html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch"/>
	<html:hidden property="pan"/>
	<html:hidden property="creditValue" />
	<html:hidden property="holdingPeriod" />
	<html:hidden property="payChannel" />
	<html:hidden property="creditType" />
</html:form>
</body>
</html:html>