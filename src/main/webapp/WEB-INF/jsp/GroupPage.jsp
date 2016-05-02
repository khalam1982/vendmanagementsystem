<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@page import="com.centrica.vms.form.PowerUserAdminForm"%><html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Details Page</title>
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
		document.forms[0].action = "PowerUserAdminAction";
	}
	function groupName(groupName)
	{
	 	var num = groupName.selectedIndex;
	 	document.forms[0].vmsGroupName.value = groupName.options[num].text;
	}
	function keyEntered(){
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.groupdetails" /></label>
<font color="red"><html:errors /></font>
<html:form action="/PowerUserAdminAction">
	<table border=1>
		<tr>
			<td><bean:message key="app.vmsgroups" /><font color="red">*</font>:</td>
			<td><html:select property="vmsGroup" name="PowerUserAdminForm" onchange="groupName(this);">
				<html:option value="0">
					<bean:message key="app.vmsgroup.option1" />
				</html:option>
				<logic:present name="PowerUserAdminForm" property="groupDetails">
				<bean:define id="groups" name="PowerUserAdminForm" property="groupDetails"/>
				<html:options collection="groups" property="groupID" labelProperty="groupDescription"/>
				</logic:present>
			</html:select></td>
		</tr>
		<tr>
			<td><html:submit style="cursor:hand" onclick="submitPage()">
				<bean:message key="app.submit" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="detailPage" />
	<html:hidden property="vmsGroupName" />
	<% request.getSession(false).setAttribute("GroupDetails",((PowerUserAdminForm)request.getAttribute("PowerUserAdminForm")).getGroupDetails()); %>
</html:form>
</body>
</html:html>