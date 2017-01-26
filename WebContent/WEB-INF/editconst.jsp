<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit page</title>
</head>
<body>
edit the details 
<br>
<form name=frm action=modifyconst method=post>
field <input type=text name=field value="${field} ${norec}" readonly><br>
enter value<input type=text name=value value="${value}"><br>

<input type=submit>
</form>
<center>
<h1> ${rec} </h1></center>
</body>
</html>