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
<form name=frm action=modifyreg method=post>
rollno <input type=text name=roll value="${rollno} ${norec}" readonly><br>
enter new name<input type=text name=name value="${name}"><br>
enter new pass<input type=text name=pass value="${pass}"><br>
enter new email<input type=text name=email value="${email}"><br>
enter new branch<input type=text name=branch value="${branch}"><br>
enter new college<input type=text name=college value="${college }"><br>
enter new  contact<input type=text name=contact value="${contact}"><br>
<input type=submit>
</form>
<center>
<h1> ${rec} </h1></center>
</body>
</html>