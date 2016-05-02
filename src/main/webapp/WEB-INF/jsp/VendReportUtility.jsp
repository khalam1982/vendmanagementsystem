<%@ page language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vend History Search Page</title>
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
		document.forms[0].action = "VendReportAction";
	}
</script>

<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>  
<script>

	$(function() {
						
		
		$( "#startDate" ).datepicker({
			
			maxDate: '0',
			
			defaultDate: "+1w",
	
			changeMonth: true,

			numberOfMonths: 3,

			onSelect: function( selectedDate ) {

				$( "#endDate" ).datepicker( "option", "minDate", selectedDate );

			}
	
		});

		$( "#endDate" ).datepicker({

			maxDate: 0,
		
			defaultDate: "+1w",
		
			changeMonth: true,
		
			numberOfMonths: 3,
		
			onSelect: function( selectedDate ) {

				var date = new Date($("#startDate").datepicker('getDate'));
				date.setMonth(date.getMonth()+1,date.getDate());
				if (new Date(selectedDate).getTime() > date.getTime()) {
					alert("Maximum date range should be 1 Month");
					$("#endDate").val('');
				} else {
					$( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
				}
	
			}
		
		});
		
	});

     function update(what,which) {
         if ( document.form.time.value == "" ) {
             if ( which == "h" ) {
                 document.form.time.value = what + ":--:--";
             } else if ( which == "m" ) {
                 document.form.time.value = "--:" + what + ":--";
             } else if ( which == "ampm" ) {
                 document.form.time.value = "--:--:" + what;
             }
         } else {
             var time = document.form.time.value.split(":");
             if ( which == "h" ) {
                 document.form.time.value = what + ":" + time[1] + ":" + time[2];
             } else if ( which == "m" ) {
                 document.form.time.value = time[0] + ":" + what + ":" + time[2];
             } else if ( which == "ampm" ) {
                 document.form.time.value = time[0] + ":" + time[1] + ":" + what;
             }
         }
     }
</script>

</head>
<body bgcolor=#F5F5F5>
<label><bean:message key="app.vendreport" /></label>
<font color="red"><html:errors /></font>
<html:form action="/VendReportAction">
	<table border=1>
		<tr>
			<td><bean:message key="app.startdate" /><font color="red">*</font></td>
			<td><input type="text" name="startDate" id="startDate" value='<bean:write name="VendReportRequestForm" property="startDate" />'/></td>
			<td><bean:message key="app.time" /><font color="red">*</font></td>
			<td>
				<html:select property="menuStartHour" styleClass="dropdown">
					
                	<html:options name="menuStartHourList"/>

            	</html:select>  

				
				<html:select property="menuStartMinute" styleClass="dropdown">

                	<html:options name="menuStartMinuteList"/>

            	</html:select>  
            	 
            	<html:select property="menuStartMerid" styleClass="dropdown">

                	<html:options name="menuStartMeridList"/>

            	</html:select> 
      			
      		</td>
		</tr>
		<tr>
			<td><bean:message key="app.enddate" /><font color="red">*</font></td>
			<td><input type="text" name="endDate" id="endDate" value='<bean:write name="VendReportRequestForm" property="endDate" />'/></td>
			<td><bean:message key="app.time" /><font color="red">*</font></td>
			<td>
			
			<html:select property="menuEndHour" styleClass="dropdown">

                	<html:options name="menuEndHourList"/>

            	</html:select>  

				
				<html:select property="menuEndMinute" styleClass="dropdown">

                	<html:options name="menuEndMinuteList"/>

            	</html:select>  
            	 
            	<html:select property="menuEndMerid" styleClass="dropdown">

                	<html:options name="menuEndMeridList"/>

            	</html:select> 
            	            	
      		</td>
		</tr>
				
		<tr>
		<td><bean:message key="app.sourceselection" /></td>
		<td>
		
			<html:select property="selectedSource" styleClass="dropdown">

					<html:option value=""></html:option>
					
                	<html:options collection="selectedSourceDetails" property="key" labelProperty="value"/>

            </html:select> 
            	
    	</td>
		</tr>
		
		<tr>
		<td><bean:message key="app.statusselection" /></td>
		<td>
		
			<html:select property="selectedStatus" styleClass="dropdown">
			
					<html:option value=""></html:option>

                	<html:options collection="selectedStatusDetails" property="key" labelProperty="value"/>

            </html:select> 
            	
    	</td>
		</tr>
		
		<tr>

			<td colspan=2 align="center"><html:submit style="cursor:hand" onclick="javascript:submitPage()">

				<bean:message key="app.generate" />

			</html:submit></td>

		</tr>
	</table>
	
	
	<html:hidden property="dispatch" value="submit" />

</html:form>

</body>
</html:html>