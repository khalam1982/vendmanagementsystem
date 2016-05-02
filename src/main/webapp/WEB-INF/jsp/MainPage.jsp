<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Vend Management System -- Version <%=((String)request.getSession(false).getAttribute("RELEASE_VERSION"))%></title>
<script type="text/javascript">
function alertUser(reminder){
	if(reminder>0){
		
		alert("Password expires in "+reminder + " days");
	}
}
</script>
</head>
<frameset rows="100,60,*" class="" onload="alertUser(<%=((Integer)request.getSession(false).getAttribute("REMINDER"))%>)" >
   <frame src="TitlePage.jsp" name="TopFrame" frameborder="0" noresize="noresize" scrolling="no"/>
   <frame src="MenuPage" name="MenuFrame" frameborder="0" noresize="noresize" scrolling="no"/>
   <frame src="Welcome.html" name="DisplayFrame" frameborder="0" />
</frameset>
</html>