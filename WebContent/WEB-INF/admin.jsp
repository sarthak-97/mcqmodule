<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin pannel</title>
</head>
<body>
ADD QUESTIONS
<form name=frm action=add method=post>
enter questionId <input type=text name=id><br><BR>

	enter question <textarea name=ques></textarea><br>
	optionA <input type=text name=opta><br>
	optionB <input type=text name=optb><br>

 optionC <input type=text name=optc><br>
 optionD <input type=text name=optd><br>
 answer <input type=text name=ans><br>
 <input type=submit>
</form>
<BR>
DELETE QUESTIONS
<form name=frm action=del method=post>
enter quesid <input type=text name=id>
<br>
<input type=submit>

</form>
EDIT QUESTIONS
<form name=frm3 action=edit method=post>
enter quesid<input type=text name=id>
<br>
<input type=submit>

</form>
<br>
<form name=allques action=alques method=post>
click here to see all ques details<input type=submit>
</form>
<HR>
<HR>
<H1>USER REGISTRATIONS CONTROLL</H1>
<form action=newreg method=post>
enter roll no<input type=text name=roll><br>
enter name<input type=text name=name><br>
 college<input type=text name=clg><br>
	 branch<input type=text name=branch><br>
 contact<input type=text name=contact><br>
email<input type=text name=email><br>
pass<input type=password name=pass><br>
<input type=submit>
</form>
<br>
DELETE REGISTRATIONS
<form name=frm action=delreg method=post>
enter ROLLNO<input type=text name=rollno>
<br>
<input type=submit>

</form><br>
EDIT REGISTRATIONS
<form action=editreg method=post>
enter ROLLNO<input type=text name=rollno>
<input type=submit>
</form>
<BR>
<form action=allreg method=post>
click here to see all registrations<input type=submit>
</form>
<br>
<hr>
<hr> RESULTS
<form action=res method=post>
CLICK HERE FOR RESULT TABLE<input type=submit>
</form>
<br>
<br>
<hr>
<hr>
CONSTRAINTS DETAILS
<BR>
<form action=const method=post>
enter field<input type=text name=id><br>
click here to edit constraints

<input type=submit>
</form>
<br>
<form action=cons method=post>
click here to see all constraints<input type=submit>
</form>
</body>
</html>