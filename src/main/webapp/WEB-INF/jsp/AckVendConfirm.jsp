<%@ page language="java"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ACK Vend Request Confirm Page</title>

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

		document.forms[0].transactionID.value = '<bean:write name="AckVendRequestForm" property="transactionID" />';

		document.forms[0].transactionDesc.value = '<bean:write name="AckVendRequestForm" property="transactionDesc" />';

		document.forms[0].action = 'AckVendRequestAction';

	}	

</script>

</head>

<body  bgcolor=#F5F5F5>

<label><bean:message key="app.ackvend" /></label>

<font color="red"><html:errors /></font>

<html:form action="/AckVendRequestAction" >

	<table border=1>

		<tr>

			<td><bean:message key="app.transactionid" />:</td>

			<td><bean:write name="AckVendRequestForm" property="transactionID" /></td>

		</tr>
		
		<tr>

			<td><bean:message key="app.transactiondesc" />:</td>

			<td><bean:write name="AckVendRequestForm" property="transactionDesc" /></td>

		</tr>
		
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

	<html:hidden property="transactionID" />

	<html:hidden property="transactionDesc" />

</html:form>

</body>

</html:html>