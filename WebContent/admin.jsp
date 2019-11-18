<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Quiz Retake Scheduler</title>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>
	$(function() {
		$("#repeatCheckBox").prop("checked", false);
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		 $( "#quizDate" ).datepicker();

		var requesturl = "http://localhost:8080/quizretakes/quizschedule";
		$("#admin").tabs();
		

		//show new quiz form when add quiz clicked
		$("#addQuiz").click(function() {
			$("#newQuiz").show();
		});

		// For the repeat event
		$("#repeatCheckBox").change(function() {
			if (this.checked) {
				$("#repeatDiv").show();
				$("#startDate").attr('required', true);
				$("#endDate").attr('required', true);
			} else {
				$("#repeatDiv").hide();
				$("#startDate").attr('required', false);
				$("#endDate").attr('required', false);
			}

		});
		
		//count for repeat weekly if repeat selected		
	
		$('#addQuizButton').click(function(){
		if($("#repeatCheckBox").is(':checked')){
		var count = getWeeklyEventCount($("#startDate").datepicker("getDate"), $("#endDate").datepicker("getDate"));
		if($("#startDate").datepicker("getDate")>= $('#quizDate').datepicker("getDate") ){
	    	$("#repeatCount").val(count+1);}

		}
			});
		
		//$('#addNewQuiz').attr('action', requesturl);

		//$('#courseAuth').attr('action', requesturl);
		

	});
	
	
	var getWeeklyEventCount = function (startDate, endDate) {
	    var secondOccurrence = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()+7);
	    if (endDate.getTime() >= secondOccurrence.getTime()) {
	        return 1 + getWeeklyEventCount(secondOccurrence, endDate);
	    }
	    if (endDate.getTime() >= startDate.getTime()) {
	        return 1;
	    }
	    return 0;
	}; 
	

	
</script>

<style>
#tabs-1 {
	font-size: 14px;
}

.ui-widget-header {
	background: #b9cd6d;
	border: 1px solid #b9cd6d;
	color: #FFFFFF;
	font-weight: bold;
}
</style>
<title>Quiz Retake Scheduler</title>
</head>
<body bgcolor="#DDEEDD">
	<center>
		<h2>GMU quiz retake scheduler</h2>
	</center>
	<hr />
	<div id="courseAuthDiv">
		<span id="message">${message}</span>
		<form id="courseAuth" method="get" action="quizschedule">
		<input name="query" id="query" style="display: none" value="admin"></input><br>
		<label>Select course id:</label><input name="courseID" id="courseID"
				type="text"></input>
		<input type="submit" value="Submit">		
		</form>
		<br> <br>
	</div>
	<div id="admin" class="tab">
		<ul>
			<li><a href="#quiz">Quiz</a></li>
			<li><a href="#retake">Retake</a></li>
			<li><a href="#course">Course</a></li>
			<li><a href="#appointments">Appointments</a></li>

		</ul>
		<div id="quiz">
				<a href="#" id="addQuiz">Add new quiz</a>
				<form id="addNewQuiz" method="post" action="quizschedule">
					<div id="newQuiz" style="display: none">
						<input name="repeatCount" id="repeatCount" style="display: none" value="0"></input><br>
						<input name="query" id="query" style="display: none" value="addQuiz"></input><br>
						<label>Choose date and time for quiz:</label> <input
							id="quizDate" type="text" placeholder="MM/DD/YYYY" name="quizDate"
							 required> <input
							id="quizTime" type="time" name="quizTime" placeholder="HH:MM"
							pattern="[0-9]{2}:[0-9]{2}" required>
							<label>Repeat Weekly: <input id="repeatCheckBox" type="checkbox" name="repeatCheckBox"></input></label><br>
						<div id="repeatDiv" style="display: none">
						
							Start Date: <input id="startDate" name="startDate" type="text" placeholder="MM/DD/YYYY"
								></input><br> End Date:
							<input id="endDate" placeholder="MM/DD/YYYY" name="endDate" type="text"
								></input><br>
						</div>
						<input type="submit" placeholder="MM/DD/YYYY" id="addQuizButton" name="addQuizButton" value="Add quiz">
					</div>
				</form>
		
		<br>
		<br>
		<br>
		<table border=1>
			<tr>
				<td align='middle'>All quiz dates</td>
			</tr>
			<c:forEach items="${allQuizzes}" var="allQuiz">
				<tr>
					<td>${allQuiz}
				</td>
								</tr>
				
			</c:forEach>
		</table>
		</div>

		<div id="retake">
			<label>Choose date and time for retake:</label> <input
				id="retakeDate" type="datetime-local" name="retakeDate"
				pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required>
			<input type="submit" value="Add retake">

		</div>
		<div id="course">
			<label>Choose date and time for quiz:</label> <input id="courseInfo"
				type="datetime-local" name="courseInfo"
				pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required>
			<input type="submit" value="Add course Info">

		</div>
		<div id="appointments"></div>
	</div>
</body>
</html>