<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@page import="com.centrica.vms.form.PowerUserAdminForm"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Power User Admin Details Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script language="javascript">
	function submitPage(operationType) {
		document.forms[0].action = "PowerUserAdminDetailAction";
		if(document.forms[0].vmsGroup.value!='G4'){
			var userFunctions = document.getElementById('selectedFunctions');
			var strUserFunctionCodes="";
			var temp = userFunctions.options[1];
			for(var r=1;r<userFunctions.options.length;r++){
				strUserFunctionCodes += userFunctions.options[r].value+"$";
				
			}
			document.forms[0].userFunctionCodes.value=strUserFunctionCodes;
		}
		document.forms[0].dispatch.value = operationType;
	}
	function keyEntered(){
	}
	function addUserfunctions(var1, var2){
		moveOptions(var1, var2);
	}
	 
	function removeUserfunctions(var1, var2){
	         moveOptions(var1, var2);
	 }
	 
	 
	function deleteOption(theSel, theIndex){
		var selLength = theSel.length;
		if(selLength>0)	{
		    theSel.options[theIndex] = null;
		}
	 }
	function addOption(theSel, theText, theValue){
		var newOpt = new Option(theText, theValue);
		var selLength = theSel.length;
		theSel.options[selLength] = newOpt;
	 }
	    
	function moveOptions(theSelFrom, theSelTo){
		var selLength = theSelFrom.length;
		selectedText = new Array();
		selectedValues = new Array();
		var selectedCount = 0;
	         var i;
		
	         // Find the selected Options in reverse order
		// and delete them from the 'from' Select.
		for(i=selLength-1; i>=0; i--)
		{
		   if(theSelFrom.options[i].selected){
		   		selectedText[selectedCount] = theSelFrom.options[i].text;
		   		selectedValues[selectedCount] = theSelFrom.options[i].value;
		   		if(selectedValues[selectedCount]!=-1){
			   		deleteOption(theSelFrom, i);
		   		}
		   		selectedCount++;
		 	}
	    }
		// Add the selected text/values in reverse order.
		// This will add the Options to the 'to' Select
		// in the same order as they were in the 'from' Select.
		for(i=selectedCount-1; i>=0; i--){
				if(selectedValues[i]!=-1){
					addOption(theSelTo, selectedText[i], selectedValues[i]);
				}
		    }
	}
			
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.powusradmdetails" /></label>
<font color="red"><html:errors /></font>
<html:form action="/PowerUserAdminDetailAction">
	<bean:define name="PowerUserAdminForm" property="vmsGroup" id="vmsGroup" type="String"/>
	<bean:define name="PowerUserAdminForm" property="vmsGroupName" id="vmsGroupName" type="String"/>
	<bean:define name="PowerUserAdminForm" property="operationType" id="operationType" type="String"/>
	<bean:define name="PowerUserAdminForm" property="passwordExpired" id="passwordExpired" type="java.lang.Boolean"/>
	
	<table border=1>
		<tr>
			<td><bean:message key="app.groupname" />:</td>
			<td><bean:write name="PowerUserAdminForm" property="vmsGroupName" /></td>
		</tr>
		<logic:equal name="PowerUserAdminForm" property="operationType" value="UPDATE">
		<bean:define name="PowerUserAdminForm" property="userName" id="userName" type="String"/>
		<html:hidden property="userName" value="${userName}"/>
		<tr>
			<td><bean:message key="app.username" />:</td>
			<td><bean:write name="PowerUserAdminForm" property="userName" /></td>
		</tr>
		</logic:equal>
		<logic:equal name="PowerUserAdminForm" property="operationType" value="SAVE">
		<tr>
			<td><bean:message key="app.username" /><font color="red">*</font>:</td>
			<td><html:text property="userName"></html:text></td>
		</tr>
		</logic:equal>		<%-- CEN_3 start --%>		<tr>			<td><bean:message key="app.adminPassword" /><font color="red">*</font>:</td>			<td><html:password property="adminPassword"></html:password></td>		</tr>
		<tr>
			<td><bean:message key="app.userPassword" />				<logic:equal name="PowerUserAdminForm" property="operationType" value="SAVE">					<font color="red">*</font>				</logic:equal>				:			</td>
			<td><html:password property="userPassword"></html:password></td>
		</tr>	    <%-- CEN_3 end --%>
		<logic:notEqual name="PowerUserAdminForm" property="vmsGroup" value="G4">
		<tr>
			<td><bean:message key="app.lanid" /><font color="red">*</font>:</td>
			<td><html:text name="PowerUserAdminForm" property="lanID"/></td>
		</tr>
		<tr>
			<td><bean:message key="app.lockind" />:</td>
			<td><html:checkbox name="PowerUserAdminForm" property="lockIND" value="Y"/></td>
		</tr>
		<tr>
			<td><bean:message key="app.passexpired" />:</td>
			<td><html:checkbox name="PowerUserAdminForm" property="passwordExpired" disabled="true"/></td>
		</tr>
		</logic:notEqual>
	</table>
	<logic:notEqual name="PowerUserAdminForm" property="vmsGroup" value="G4">
	<table>
	<tr><td colspan="3" align="center"><bean:message key="app.userfunctions" /></td></tr>
    <tr><td>
         <select id="selectedFunctions" multiple="multiple" size="8" name="selectedFunctions">
         	<option value="-1" disabled="disabled">  - - Functions Assigned to User - -  </option>
         	<logic:present name="PowerUserAdminForm" property="selectedFunctionDetails">
	    	<logic:iterate id="selectedFunctionDetails" name="PowerUserAdminForm" property="selectedFunctionDetails">
	    		 <bean:define id="functionCode" name="selectedFunctionDetails" property="functionCode"/>
	   			 <bean:define id="functionDescription" name="selectedFunctionDetails" property="functionDescription"/>
                 <option value="${functionCode}">${functionDescription}</option>
        	</logic:iterate>
		</logic:present>
		</select> 
    </td>
    <td>
        <table>
            <tr><td><input type="button" value="&lt; &lt; add" onclick="javascript:addUserfunctions(this.form.availableFunctions, this.form.selectedFunctions);" />
        </td></tr>
        <tr><td><input type="button" value="remove &gt; &gt;" onclick="javascript:removeUserfunctions(this.form.selectedFunctions, this.form.availableFunctions);" />
        </td></tr>
     </table>
    </td>
    <td>
	<select  id="availableFunctions" multiple="multiple" size="8" name="availableFunctions">
            <option value="-1" disabled="disabled">- - Available Functions - - </option>
	    <logic:present name="PowerUserAdminForm" property="availableFunctionDetails">
	    	<logic:iterate id="availableFunctionDetails" name="PowerUserAdminForm" property="availableFunctionDetails">
	    		 <bean:define id="functionCode" name="availableFunctionDetails" property="functionCode"/>
	   			 <bean:define id="functionDescription" name="availableFunctionDetails" property="functionDescription"/>
                 <option value="${functionCode}">${functionDescription}</option>
        	</logic:iterate>
		</logic:present>
     </select>
     </td>
     </tr>
   </table>
   </logic:notEqual>
   <table>
   <tr>
   			<logic:equal name="PowerUserAdminForm" property="operationType" value="SAVE">
   			<td><html:submit style="cursor:hand" onclick="submitPage('save')">
				<bean:message key="app.save" />
			</html:submit></td>
			</logic:equal>
			<logic:equal name="PowerUserAdminForm" property="operationType" value="UPDATE">
			<td><html:submit style="cursor:hand" onclick="submitPage('update')">
				<bean:message key="app.update" />
			</html:submit></td>
			<td><html:submit style="cursor:hand" onclick="submitPage('delete')">
				<bean:message key="app.delete" />
			</html:submit></td>
			</logic:equal>
		</tr>
   </table>
	<html:hidden property="dispatch"/>
	<html:hidden property="userFunctionCodes" value=""/>
	<html:hidden property="vmsGroup" value="${vmsGroup}"/>
	<html:hidden property="vmsGroupName" value="${vmsGroupName}"/>
	<html:hidden property="operationType" value="${operationType}"/>
	<html:hidden property="passwordExpired" value="${passwordExpired}"/>
	<% request.getSession(false).setAttribute("selectedFunctionDetails",((PowerUserAdminForm)request.getAttribute("PowerUserAdminForm")).getSelectedFunctionDetails()); 
	   request.getSession(false).setAttribute("availableFunctionDetails",((PowerUserAdminForm)request.getAttribute("PowerUserAdminForm")).getAvailableFunctionDetails());
	%>
	</html:form>
</body>
</html:html>