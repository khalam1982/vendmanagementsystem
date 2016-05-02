<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html:html>
<head>
<STYLE TYPE="text/css">
.logintable {
	background-color: White;
	color: #545454;
	font-family: Modern;
	font-weight: 600;
	font-size: 1pc;
	font-style: italic
}
</STYLE>

<title>Vend Login Page</title>
<script language="javascript">
	function submitPage() {
		document.forms[0].action = "VMSHome";
	}
</script>
</head>
<body bgcolor="#ADD2D3">
<html:form action="/VMSHome" method="post">
	<iframe src="Banner.html" height="270" width="1200" frameborder="0"
		scrolling="no" align="top"></iframe>
	<table border=1 align="center" class="logintable">
		<tr>
			<td colspan=2><font color="red"> <html:errors /> <html:messages
				message="true" id="loginerror" property="loginerror">
				<bean:write name="loginerror" />
			</html:messages> </font></td>

		</tr>
		<tr>
			<td colspan=2 align="center">
			<H3><bean:message key="app.logindetails" /></H3>
			</td>
		</tr>
		<tr>
			<td><bean:message key="app.username" /><font color="red">*</font>:</td>
			<td><html:text property="userName"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="app.password" /><font color="red">*</font>:</td>
			<td><html:password property="password"></html:password></td>
		</tr>		
		<tr>
			<td colspan=2 align="center"><html:submit style="cursor:hand"
				onclick="javascript:submitPage()">
				<bean:message key="app.submit" />
			</html:submit></td>
		</tr>
	</table>
	<html:hidden property="dispatch" value="loginSubmit" />
</html:form>
</body>
</html:html>