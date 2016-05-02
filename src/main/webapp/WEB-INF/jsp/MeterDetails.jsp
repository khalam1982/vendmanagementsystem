<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meter Details</title>
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
		document.forms[0].action = 'MeterDispatchDetailAction';
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<font color="red"><html:errors /></font>
<html:form action="/MeterDispatchDetailAction" >
	<bean:define name="MeterDetailsForm" property="operationType" id="operationType" type="String"/>
	<bean:define name="MeterDetailsForm" property="msn" id="msn" type="String"/>
	<label><bean:message key="app.meterdetails" /></label>
	<logic:messagesPresent message="true">
		<html:messages id="ErrorMessage" message="true">
			<font color="red" size="5"><bean:write name="ErrorMessage" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
	<table border=1>
		<tr>
			<td><bean:message key="app.msn" />:</td>
			<td><bean:write name="MeterDetailsForm" property="msn" /></td>
		</tr>
		
		<tr>
			<td><bean:message key="app.fueltype" /><font color="red">*</font>:</td>
			<td><html:select property="fuelType" name="MeterDetailsForm" onkeypress="javascript:submitPage('update')">
				<html:option value="0">
					<bean:message key="app.fueltype.option1" />
				</html:option>
				<html:option value="ELECTRIC">
					<bean:message key="app.fueltype.option3" />
				</html:option>
				<html:option value="GAS">
					<bean:message key="app.fueltype.option2" />
				</html:option>
			</html:select></td>
		</tr>
		<logic:equal name="MeterDetailsForm" property="operationType" value="SAVE">
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
		<logic:equal name="MeterDetailsForm" property="operationType" value="UPDATE">
		<tr>
			<td colspan=2 align="right"><html:submit style="cursor:hand"
				onclick="javascript:submitPage('update')">
				<bean:message key="app.update" />
			</html:submit> <html:submit style="cursor:hand"
				onclick="javascript:submitPage('delete')">
				<bean:message key="app.delete" />
			</html:submit> <html:submit style="cursor:hand"
				onclick="javascript:submitPage('cancel')">
				<bean:message key="app.cancel" />
			</html:submit></td>
		</tr>
		</logic:equal>
	</table>
	<html:hidden property="dispatch" />
	<html:hidden property="msn"  value="${msn}"/>
	<html:hidden property="operationType" value="${operationType}"/>
</html:form>
</body>
</html>