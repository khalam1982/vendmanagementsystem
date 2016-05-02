<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Premise Details</title>
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
		document.forms[0].action = 'PremiseDispatchDetailAction';
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<font color="red"><html:errors /></font>
<html:form action="/PremiseDispatchDetailAction" >
	<bean:define name="PremiseDetailsForm" property="operationType" id="operationType" type="String"/>
	<bean:define name="PremiseDetailsForm" property="mpxn" id="mpxn" type="String"/>
	<label><bean:message key="app.premisedetails" /></label>
	<logic:messagesPresent message="true">
		<html:messages id="ErrorMessage" message="true">
			<font color="red" size="5"><bean:write name="ErrorMessage" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
	<table border=1>
		<tr>
			<td><bean:message key="app.mpxn" />:</td>
			<td><bean:write name="PremiseDetailsForm" property="mpxn" /></td>
		</tr>
		<tr>
			<td><bean:message key="app.msn" /><font color="red">*</font>:</td>
			<td><html:text property="msn" onkeypress="javascript:submitPage('update')"></html:text></td>
		</tr>
		
		<logic:equal name="PremiseDetailsForm" property="operationType" value="SAVE">
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand"
				onclick="javascript:submitPage('save')">
				<bean:message key="app.save" />
			</html:submit> <html:submit style="cursor:hand"
				onclick="javascript:submitPage('searchPage')">
				<bean:message key="app.cancel" />
			</html:submit></td>
		</tr>
		</logic:equal>
		<logic:equal name="PremiseDetailsForm" property="operationType" value="UPDATE">
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand"
				onclick="javascript:submitPage('update')">
				<bean:message key="app.update" />
			</html:submit> <html:submit style="cursor:hand"
				onclick="javascript:submitPage('cancel')">
				<bean:message key="app.cancel" />
			</html:submit></td>
		</tr>
		</logic:equal>
	</table>
	<html:hidden property="dispatch" />
	<html:hidden property="pan"  value="${pan}"/>
	<html:hidden property="operationType" value="${operationType}"/>
</html:form>
</body>
</html>