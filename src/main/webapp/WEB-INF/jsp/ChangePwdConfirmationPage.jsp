<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password Confirmation Page</title>
<style type="text/css">
label {
	padding-left: 25em;
	font-family: Arial;
	color: Black;
	font-size: 0.5cm;
}
</style>
<script type="text/javascript">
	function keyEntered(){
	}
</script>
</head>
<body bgcolor=#F5F5F5>
<html:form action="/ChangePwdAction" >
	<logic:messagesPresent message="true">
		<html:messages id="ConfirmationMessage" message="true">
			<font color="red" size="5"><bean:write name="ConfirmationMessage" /></font>
			<br />
		</html:messages>
	</logic:messagesPresent>
</html:form>
</body>
</html>