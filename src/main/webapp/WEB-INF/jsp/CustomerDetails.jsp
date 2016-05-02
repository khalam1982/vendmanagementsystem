<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
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
		document.forms[0].action = 'CustomerDispatchDetailAction';
	}
</script>
<link href="./css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="./js/jquery-1.8.2.min.js"></script> 
<script src="./js/jquery-ui-1.8.24.custom.min.js"></script> 
<script>

	$(function() {
						
		
		$( "#validFrom" ).datepicker({	
			defaultDate: "+1w",
			changeYear: true,
			changeMonth: true,
			changeYear: true,
			numberOfMonths: 1,
			onSelect: function( selectedDate ) {
			   var validtoYear = new Date($("#validTo").datepicker('getDate')).getFullYear();
			    if(validtoYear >= 9999){
			    	var date = new Date(9999,11,31);
			    	$( "#validTo" ).datepicker("setDate", date );
			    }
				$( "#validTo" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#validTo" ).datepicker({
			defaultDate: new Date(9999,11,31),
			changeYear: true,
			changeMonth: true,
			onSelect: function( selectedDate ) {
				var date = new Date($("#validFrom").datepicker('getDate'));
				if (new Date(selectedDate).getTime() <= date.getTime()) {
					alert("Valid to date should be greater than or equal to Valid from date ");
				} else {
					$( "#validFrom" ).datepicker( "option", "maxDate", selectedDate );
				}
			}
		});
	});
</script>

</head>
<body bgcolor=#F5F5F5>
<font color="red"><html:errors /></font>
<html:form action="/CustomerDispatchDetailAction" >
	<bean:define name="CustomerDetailsForm" property="operationType" id="operationType" type="String"/>
	<bean:define name="CustomerDetailsForm" property="pan" id="pan" type="String"/>
	<label><bean:message key="app.customerdetails" /></label>
	<logic:messagesPresent message="true">
		<html:messages id="ErrorMessage" message="true">
			<font color="red" size="5"><bean:write name="ErrorMessage" /></font>
			<br/>
		</html:messages>
	</logic:messagesPresent>
	<table border=1>
		<tr>
			<td><bean:message key="app.pan" />:</td>
			<td><bean:write name="CustomerDetailsForm" property="pan" /></td>
		</tr>		
		<tr>
			<td><bean:message key="app.mpxn" /><font color="red">*</font>:</td>
			<td><html:text property="mpxn" onkeypress="javascript:submitPage('update')"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="app.validfrom" />(<bean:message key="app.MM/dd/yyyy" />)<font color="red">*</font>:</td>
			<td><input type="text" name="validFrom" id="validFrom" readonly value='<bean:write name="CustomerDetailsForm" property="validFrom"/>'/></td>
		</tr>
		<tr>
			<td><bean:message key="app.validto" />(<bean:message key="app.MM/dd/yyyy" />)<font color="red">*</font>:</td>
			<td><input type="text" name="validTo" id="validTo" readonly value='<bean:write name="CustomerDetailsForm" property="validTo"/>'/></td>
		</tr>
		<logic:equal name="CustomerDetailsForm" property="operationType" value="SAVE">
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
		<logic:equal name="CustomerDetailsForm" property="operationType" value="UPDATE">
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