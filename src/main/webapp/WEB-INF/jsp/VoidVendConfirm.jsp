<%@ page language="java"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Void Vend Request Confirm Page</title>

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

		document.forms[0].transactionID.value = '<bean:write name="VoidVendRequestForm" property="transactionID" />';

		document.forms[0].originalTransactionID.value = '<bean:write name="VoidVendRequestForm" property="originalTransactionID" />';
		
		document.forms[0].pan.value = '<bean:write name="VoidVendRequestForm" property="pan" />';

		document.forms[0].action = 'VoidVendRequestAction';

	}	

</script>

</head>

<body  bgcolor=#F5F5F5>

<label><bean:message key="app.voidvend" /></label>

<font color="red"><html:errors /></font>

<html:form action="/VoidVendRequestAction" >

	<table border=1>

		<tr>

			<td><bean:message key="app.transactionid" />:</td>

			<td><bean:write name="VoidVendRequestForm" property="transactionID" /></td>

		</tr>
		
		<tr>

			<td><bean:message key="app.originaltransactionid" />:</td>

			<td><bean:write name="VoidVendRequestForm" property="originalTransactionID" /></td>

		</tr>
		
		<tr>

			<td><bean:message key="app.pan" />:</td>

			<td><bean:write name="VoidVendRequestForm" property="pan" /></td>

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

	<html:hidden property="pan"/>

	<html:hidden property="transactionID" />

	<html:hidden property="originalTransactionID" />

</html:form>

</body>

</html:html>