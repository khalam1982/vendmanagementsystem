'##########################################################################
'This script opens "H:\Appdata\Program Files\Tech Test\Prepayment Testing\VMS
'Create Vend\VMS_Create_Vend.xls"
'The script then closes the VMS_Create_Vend.xls spreadsheets and quits the EXCEL
'session.
'Script created by Andrew Cunningham for Smart Metering
'##########################################################################

'==========================================================================================================================

'* OPTION EXPLICIT VARIABLES

Dim oConn, oFileSystem, oElement, sSQLCmd1, sData1, iRetVal1, A, B

'==========================================================================================================================

Set oConn = Createobject("ADODB.Connection")
Set oFileSystem = Createobject("Scripting.FileSystemObject")

'==========================================================================================================================

'* SQL QUERY STATEMENT 1

A = 1
B = 1

Do Until A > 1000

If A < 10 Then

	sSQLCmd1 = "insert into METER_DETAILS (MSN,PPKEY,FUELTYPE) VALUES('MSN2000000000" & A & "','2000000000000000000000000000000" & A & "','GAS')"
	iRetVal1=GetDatabaseValue1("CNRV0046","TVCPPY04","PREPAY_APP_USER","trespa55","1526",sSQLCmd1)
	sData1 = ""

	B = B + 1
	A = A + 1

Elseif A < 100 Then

	sSQLCmd1 = "insert into METER_DETAILS (MSN,PPKEY,FUELTYPE) VALUES('MSN200000000" & A & "','200000000000000000000000000000" & A & "','GAS')"
	iRetVal1=GetDatabaseValue1("CNRV0046","TVCPPY04","PREPAY_APP_USER","trespa55","1526",sSQLCmd1)
	sData1 = ""

	B = B + 1
	A = A + 1

Elseif A < 1000 Then

	sSQLCmd1 = "insert into METER_DETAILS (MSN,PPKEY,FUELTYPE) VALUES('MSN20000000" & A & "','20000000000000000000000000000" & A & "','GAS')"
	iRetVal1=GetDatabaseValue1("CNRV0046","TVCPPY04","PREPAY_APP_USER","trespa55","1526",sSQLCmd1)
	sData1 = ""

	B = B + 1
	A = A + 1

Elseif A < 10000 Then

	sSQLCmd1 = "insert into METER_DETAILS (MSN,PPKEY,FUELTYPE) VALUES('MSN2000000" & A & "','2000000000000000000000000000" & A & "','GAS')"
	iRetVal1=GetDatabaseValue1("CNRV0046","TVCPPY04","PREPAY_APP_USER","trespa55","1526",sSQLCmd1)
	sData1 = ""

	B = B + 1
	A = A + 1

Else

	WScript.Quit

End If

Loop

MsgBox "METER_DETAILS Insertered Successfully",vbInformation + vbOKOnly,"VBscript Complete"

'End SUb

'==========================================================================================================================
' Function:		GetDatabaseValue1()
' Purpose:		To execute sql command and return result
' Parameters:		sHost,sServiceName,sUsername,sPassword,sPortNumber
' Returns:		Result of the sql query
'==========================================================================================================================

Private Function GetDatabaseValue1(sHost,sServiceName,sUsername,sPassword,sPortNumber,sSQLCmd1)

	Dim sConn,oRecordSet
	On Error Resume Next 
	'SID was SERVICE_NAME
	' Create a database connection to the server
		sConn = "Driver={Microsoft ODBC for Oracle}; " & _
		"CONNECTSTRING=(DESCRIPTION=" & _
		"(ADDRESS=(PROTOCOL=TCP)" & _
		"(HOST="&sHost&")(PORT="&sPortNumber&"))" & _
		"(CONNECT_DATA=(SID="&sServiceName&"))); uid="&sUsername&";pwd="&sPassword&";"
	oConn.Open sConn

'* AFTER ESTABLISHING CONNECTION TO DATABASE EXECUTE sSQLCmd1 (SQL STATEMENT 1) AND PUT RESULT IN TEXT FILE

	Set oRecordSet = oConn.Execute(sSQLCmd1)
		If oRecordSet.BOF and oRecordSet.EOF then
			oRecordSet.Close
			set oRecordSet=Nothing
			iRetVal1 = XL_DISPATCH_FAIL
			GetDatabaseValue1=Err.Description
				Exit Function
		End If
	oRecordSet.MoveFirst
	Do until oRecordSet.EOF
		For Each oElement In oRecordSet.Fields
			sData1= oElement.value & ","
		Next
			'get rid of the last ','
			sData1= left (sData1,len(sData1)-1)
			sData1=sData1 & vbCrLf
			oRecordSet.MoveNext
	Loop
			'get rid of the last 'vbCrLf'
			sData1= left (sData1,len(sData1)-2)
			GetDatabaseValue1=sData1
	oRecordSet.Close
	Set oRecordSet=Nothing
	oConn.Close

End Function