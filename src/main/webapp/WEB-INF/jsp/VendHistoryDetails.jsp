<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vend History Details</title>
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
		document.forms[0].dispatch.value = 'searchPage';
		document.forms[0].action = 'VendHistorySearchAction';
	}
</script>
</head>
<body  bgcolor=#F5F5F5>
<label><bean:message key="app.vendhistory" /></label>
<font color="red"><html:errors /></font>
<html:form action="VendHistorySearchAction">
	<logic:messagesPresent message="true">
		<html:messages id="MESSAGE" message="true">
			<font color="red" size="5"><bean:write name="MESSAGE" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
	<table width="*" border=1>
		<tr>
			<td colspan="1"><bean:message key="app.pan" /> :&nbsp;&nbsp;&nbsp;<B><bean:write name="VendHistoryForm" property="pan" /></B></td>
			<td colspan="*"><br><br><br></td>
		</tr>
		
		<tr>
			<td colspan="*">
			<table   border="1" bordercolor="blue">
				<tr>
					<td><B><bean:message key="app.trantype" /></B></td>
					<td><B><bean:message key="app.paychannel" /></B></td>
					<td><B><bean:message key="app.creditvaluepence" /></B></td>
					<td><B><bean:message key="app.vendcode" /></B></td>
					<td><B><bean:message key="app.timestamp" /></B></td>
					<td><B><bean:message key="app.transtatus" /></B></td>
				</tr>
				<logic:iterate id="vendHistoryDetail" name="VendHistoryForm" property="vendHistoryDetails"> 
				<tr>
					<td><bean:write name="vendHistoryDetail" property="transactionType" /></td>					<logic:notEmpty name="vendHistoryDetail" property="sourceDetails">											<bean:define name="vendHistoryDetail" id="sourceDetails"  property="sourceDetails"> </bean:define>											<td><bean:write name="sourceDetails" property="sourceDescription" /></td>										</logic:notEmpty>										<logic:empty name="vendHistoryDetail" property="sourceDetails">											<td><bean:write name="vendHistoryDetail" property="emptyValue" /></td>										</logic:empty>										<td><bean:write name="vendHistoryDetail" property="creditValue" /></td>										
					<td><bean:write name="vendHistoryDetail" property="vendCode" /></td>
					<td><bean:write name="vendHistoryDetail" property="actualTimeStamp" /></td>
					<td><bean:write name="vendHistoryDetail" property="statusDesc" /></td>					
				</tr>
				</logic:iterate>
			</table> <br><br><br>
			</td>
		</tr>
		<tr>
			<td colspan="*" align="right"><html:submit style="cursor:hand"
				onclick="javascript:submitPage('searchPage')">
				<bean:message key="app.cancel" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" />
</html:form>
</body>
</html>