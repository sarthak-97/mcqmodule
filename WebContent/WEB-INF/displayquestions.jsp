
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>GDG Assessment</title>
<meta name="description" content="reverse clock plugin for jQuery.">
<meta name="keywords" content="jQuery, plugin, count down">
<meta name="viewport" content="width=device-width,initial-scale=1">

<!--CSS FOR THE PAGE  -->

<!-- materialize.css is mutilated, and does not support materialize-CSS radio input tag. @author Tilhari-->
<link rel="stylesheet" href="static/css/materialize.css" type="text/css"
	media="screen, projection" />
<link rel="stylesheet" href="static/css/style.css" type="text/css"
	media="screen, projection" />
<link rel="stylesheet" type="text/css"
	href="static/css/jquery.countdownTimer.css" />
<link rel="stylesheet" href="static/css/tabs.css" type="text/css"
	media="screen, projection" />

<!--JS FOR TIMER -->
<script type="text/javascript" src="static/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="static/js/jquery.countdownTimer.js"></script>
<script src="static/js/style.js"></script>

<!--JS FOR QUESTON NAVIGATOR  -->
<script type="text/javascript"
	src="static/js/jquery-ui-1.7.custom.min.js"></script>

	<!--TODO : warning before page reload  -->
<!-- 	<script type="text/javascript">
	window.addEventListener("beforeunload", function() {
	     confirm("Are you sure?");
	     });
	</script> -->

</head>
<body background="static/images/quesbg.jpg">
	<div style="height: 90px" id="header_footer">
		<img src="static/images/gdgjsslogoblue.png" class="left">
		<div class="right blue-grey darken-3"
			style="border-radius: 4px; margin-top: 20px; width: 150px; height: 60px;">
			<div style="margin-top: 8px; margin-left: 14px;">
				<span style="color: white; font-size: 12px;">HOUR : MINS :
					SECS </span> </br> <span id="hms_timer" class="size_md"></span>
			</div>
		</div>
	</div>


	<%
		int i = 1;
	%>
	<form action="/gdgmcq/sessionSubmitSolution" method="post" , id="myForm"
		onsubmit="return submitConfirm();">
		<div id="page-wrap">
			<div id="tabs">
				<c:forEach var="questionlist" items="${ques}">
					<%
						String nameid = "ques" + Integer.toString(i);
							request.setAttribute("nameid", nameid);
							String fragmentid = "fragment-" + Integer.toString(i);
							request.setAttribute("fragmentid", fragmentid);
							String tabId = nameid + "navtab";
							request.setAttribute("tabId", tabId);
							i++;
					%>
					<c:choose>
						<c:when test="${fragmentid=='fragment-1'}">
							<div id=${fragmentid } class="ui-tabs-panel">
						</c:when>
						<c:otherwise>
							<div id=${fragmentid } class="ui-tabs-panel ui-tabs-hide">
						</c:otherwise>
					</c:choose>
					<h6 style="color:grey">Question</h6>
					<p>${questionlist.question}</p>
					<h6 style="color:grey">Options</h6>
					<h6>
						<input type="radio" value="A" name="${nameid}"
							onclick="changetabAttributes('${tabId}')" />
						${questionlist.optionA}
					</h6>
					<h6>
						<input type="radio" value="B" name="${nameid}"
							onclick="changetabAttributes('${tabId}')" />
						${questionlist.optionB}
					</h6>
					<h6>
						<input type="radio" value="C" name="${nameid}"
							onclick="changetabAttributes('${tabId}')" />
						${questionlist.optionC}
					</h6>
					<h6 style="padding-bottom: inherit;">
						<input type="radio" value="D" name="${nameid}"
							onclick="changetabAttributes('${tabId}')" />
						${questionlist.optionD}
					</h6>
			</div>
			</c:forEach>

			<div>
				<h5 style="color: grey">Question Navigator</h5>
			</div>
			<%
				int j = 1;
			%>
			<ul id="ques_nav_bar">
				<c:forEach var="questionNo" items="${ques}">
					<%
						String navtabId = "ques" + Integer.toString(j) + "navtab";
							request.setAttribute("navtabId", navtabId);
							String fragmentref = "'#fragment-" + Integer.toString(j) + "'";
							request.setAttribute("fragmentref", fragmentref);
							String fragmentNo = "";
							if (j < 10)
								fragmentNo = "0" + Integer.toString(j);
							else
								fragmentNo = Integer.toString(j);
							boolean breakpoint = false;
							if (j % 15 == 0)
								breakpoint = true;

							request.setAttribute("breakpoint", breakpoint);
							request.setAttribute("fragmentNo", fragmentNo);
							j++;
					%>
					<li><a href=${fragmentref } id='${navtabId}'>${fragmentNo}</a></li>
					<c:if test="${breakpoint=='true'}">
						</br>
						</br>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		</div>

		<div style="height: 60px;" id="header_footer">
			<div align="center">
				<input type="submit" name="Submit" value="Submit"
					class=" btn blue-grey darken-3" />
			</div>
		</div>
	</form>
	<h6 class="center-align blue-grey-text">Logged in as
		${sessionName} (${sessionrollNo})</h6>


	<!-- User defined script @author Tilhari -->
	<script type="text/javascript">
		$(function() {
			$('#hms_timer').countdowntimer({
				hours : "${myhr}",
				minutes : "${mymin}",
				seconds : "${mysec}",
				size : "lg",
				timeUp : myFunction
			});
		});
	</script>

	<script type="text/javascript">
		$(function() {

			var $tabs = $('#tabs').tabs();

			$(".ui-tabs-panel")
					.each(
							function(i) {

								var totalSize = $(".ui-tabs-panel").size() - 1;

								if (i != totalSize) {
									next = i + 2;
									$(this)
											.append(
													"<a href='#' id='mover-btn' class='next-tab mover btn blue-grey darken-3' rel='" + next + "'>Next &#187;</a>");
								}

								if (i != 0) {
									prev = i;
									$(this)
											.append(
													"<a href='#' id='mover-btn' class='prev-tab mover btn blue-grey darken-3' rel='" + prev + "'>Prev</a>");
								}

							});

			$('.next-tab, .prev-tab').click(function() {
				$tabs.tabs('select', $(this).attr("rel"));
				return false;
			});

		});
	</script>


	<script type="text/javascript">
		function myFunction() {
			alert("Time up !!! Your responses have been automatically submitted.");
			document.getElementById("myForm").submit();
		}
	</script>
	<script type="text/javascript">
		function changetabAttributes(catched) {
			document.getElementById(catched).style.color = "#006400";
			document.getElementById(catched).style.fontWeight = "900";

		}
	</script>


</body>
</html>