<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<link rel="stylesheet" href="static/css/materialize.css" type="text/css"
	media="screen, projection" />
<link rel="stylesheet" href="static/css/style.css" type="text/css"
	media="screen, projection" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rules</title>
</head>
<body background="static/images/quesbg.jpg">

	<div style="height: 90px" id="header_footer">
		<img src="static/images/gdgjsslogoblue.png" class="left">
	</div>
	<div class="center-align blue-grey-text text-darken-3">
		<h4>Rules For Test Session</h4>
	</div>
	<div id="rules-page"  class="rules">
	<p>${rules}</p>
	</div>
	<div style="height: 60px;" id="header_footer">
		<div align="center">
			<form action="/gdgmcq/sessionQuestionController.html" method="post">
				<button class=" btn blue-grey darken-3">START</button>
			</form>
		</div>
	</div>
	<h6 class="center-align blue-grey-text">Logged in as
		${sessionName} (${sessionrollNo})</h6>
</body>
</html>