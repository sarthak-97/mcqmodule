

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="static/css/materialize.css" type="text/css"
	media="screen, projection" />
	<title>GDG Assessment</title>
</head>
<body background="static/images/quesbg.jpg">

	<div style="height: 90px" id="header_footer">
		<img src="static/images/gdgjsslogoblue.png" class="left">
	</div>
	<br/>
	<br/>
	<h5 class="center-align blue-grey-text text-darken-3">Thanks ${sessionName}<br/><br/>
	Your response has been successfully recorded. Results will be declared soon.<br/><br/>Good Luck !!!
	</h5>
	<br/>
	<div align="center">
		<form action="/gdgmcq/index" method="get">
			<input type="submit" value="Home page" class=" btn blue-grey darken-3"/>
		</form>
	</div>
	<br/>
	<br/>
	<h6 class="center-align blue-grey-text text-darken-3">Techie !<br/>
	Stay tuned to GDG JSS for upcoming events and other technology stack.<br/><br/></h6>
	<h6 class="center-align blue-grey-text text-darken-3">OFFICIAL GDG WEBSITE<br/>
	<a href="http://gdgjss.in">gdgjss.in</a><br/><br/>
	FACEBOOK PAGE<br/>
	www.facebook.com/gdgjss
	</h6>
	
	
</body>

</html>
