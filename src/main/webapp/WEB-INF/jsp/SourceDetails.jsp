<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Source Details Page</title>
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
				document.forms[0].action = "SourceDetailsAction";
				document.forms[0].submit();
			}

			function onSourceSelect() {
				document.forms[0].action = "SourceDetailsAction";
				document.getElementById('dispatch').value = "onSourceSelect";
				document.forms[0].submit();
			}
		</script>
</head>

<body bgcolor=#F5F5F5>

<br/>

<label><bean:message key="app.sourcedetails" /></label>
<br/>

<font color="red"><html:errors /></font>

<font color="green">
<html:messages id="successMsg" message="true">
	<bean:write name="successMsg"/><br>
</html:messages>
</font>

<br/><br/>

<html:form action="/SourceDetailsAction">

<table border=1 style="margin-left:20px">

	<tr>
		<td><bean:message key="app.source" /><font color="red">*</font></td>
		<td>
			<html:select name="SourceDetailsForm" property="selectedSource" styleClass="dropdown" onchange="javascript:onSourceSelect()">
				<html:option value="-1">Please Select</html:option>
				<c:forEach var="sourceDet" items="${sourceDetList}" >
				<c:set var="disabled" value="false"></c:set>
				<c:if test="${sourceDet.vendConfigurable == null || sourceDet.vendConfigurable == 0}">
					<c:set var="disabled" value="true"></c:set>
				</c:if>
				<html:option value="${sourceDet.source}" disabled="${disabled}">${sourceDet.sourceDescription}</html:option>
				</c:forEach>
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td><bean:message key="app.vendlimit" /><font color="red">*</font></td>
		<td>
			<html:text name="SourceDetailsForm" property="vendLimit" size="5" maxlength="5" />
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="right">
			<html:submit onclick="javascript:submitPage()">
				<bean:message key="app.submit" />
			</html:submit>
		</td>
	</tr>
	
</table>

<input type="hidden" id="dispatch" name="dispatch" value="processSubmit" />
</html:form>

</body>

</html:html>
