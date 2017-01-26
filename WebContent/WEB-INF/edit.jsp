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
<form name=frm action=modify method=post>
question id <input type=text name=id value="${qid} ${norec}" readonly><br>
enter new question<input type=text name=ques value="${ques}"><br>
enter new option A<input type=text name=opta value="${opta}"><br>
enter new option B<input type=text name=optb value="${optb}"><br>
enter new option C<input type=text name=optc value="${optc}"><br>
enter new option D<input type=text name=optd value="${optd}"><br>
enter new  ANS<input type=text name=ans value="${ans}"><br>
<input type=submit>
</form>
<center>
<h1> ${rec} </h1></center>
</body>
</html>