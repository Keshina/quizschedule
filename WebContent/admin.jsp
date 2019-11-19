<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Quiz Retake Scheduler</title>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js'></script> 

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>
	$(function() {
		$("#repeatCheckBoxQ").prop("checked", false);
		$("#repeatCheckBoxR").prop("checked", false);
		$("#startDateQ").datepicker();
		$("#endDateQ").datepicker();
		$("#editedQuizDate").datepicker();
		$("#startDateR").datepicker();
		$("#endDateR").datepicker();
		 $( "#quizDate" ).datepicker();

		var requesturl = "http://localhost:8080/quizretakes/quizschedule";
		$("#admin").tabs();
		

		//show new quiz form when add quiz clicked
		$("#addQuiz").click(function() {
			$("#newQuiz").show();
		});
		
		//show new quiz form when add quiz clicked
		$(".editButton").click(function() {
			var buttonElementId = $(this).attr('id');
			$("#editedQuiz").show();
			$("#editId").val(buttonElementId);
		});
		
		$("#cancelEditQuiz").click(function(){
			$("#editedQuiz").hide();
			$("#editQuiz").reset();
		});
		
		//show new quiz form when add quiz clicked
		$("#addRetake").click(function() {
			$("#newRetake").show();
		});

		// For the repeat event
		$("#repeatCheckBoxQ").change(function() {
			if (this.checked) {
				$("#repeatDivQ").show();
				$("#startDateQ").attr('required', true);
				$("#endDateQ").attr('required', true);
			} else {
				$("#repeatDivQ").hide();
				$("#startDateQ").attr('required', false);
				$("#endDateQ").attr('required', false);
			}

		});
		
		$("#repeatCheckBoxR").change(function() {
			if (this.checked) {
				$("#repeatDivR").show();
				$("#startDateR").attr('required', true);
				$("#endDateR").attr('required', true);
			} else {
				$("#repeatDivR").hide();
				$("#startDateR").attr('required', false);
				$("#endDateR").attr('required', false);
			}

		});
		
		//count for repeat weekly if repeat selected		
	
		$('#addQuizButton').click(function(){
		if($("#repeatCheckBoxQ").is(':checked')){
		var count = getWeeklyEventCount($("#startDateQ").datepicker("getDate"), $("#endDateQ").datepicker("getDate"));
		if($("#startDateQ").datepicker("getDate")>= $('#quizDate').datepicker("getDate") ){
	    	$("#repeatCountQ").val(count+1);}

		}
			});
		
		$('#addRetakeButton').click(function(){
			if($("#repeatCheckBoxR").is(':checked')){
			var count = getWeeklyEventCount($("#startDateR").datepicker("getDate"), $("#endDateR").datepicker("getDate"));
			if($("#startDateR").datepicker("getDate")>= $('#retakeDate').datepicker("getDate") ){
		    	$("#repeatCountR").val(count+1);}

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
							<label>Repeat Weekly: <input id="repeatCheckBoxQ" type="checkbox" name="repeatCheckBoxQ"></input></label><br>
						<div id="repeatDivQ" style="display: none">
						
							Start Date: <input id="startDate" name="startDate" type="text" placeholder="MM/DD/YYYY"
								></input><br> End Date:
							<input id="endDate" placeholder="MM/DD/YYYY" name="endDate" type="text"
								></input><br>
						</div>
						<input type="submit" placeholder="MM/DD/YYYY" id="addQuizButton" name="addQuizButton" value="Add quiz">
					</div>
				</form>
				
				
				<form id="editQuiz" method="post" action="quizschedule">
					<div id="editedQuiz" style="display: none">
						<br><br>Edit Quiz: 
						<input name="query" id="query" style="display: none" value="editQuiz"></input><br>
						<label>Quiz Id:</label><input type="text" name="editId" id="editId" value=""></input><br>
						<label>Choose date and time for quiz:</label> <input
							id="editedQuizDate" type="text" placeholder="MM/DD/YYYY" name="editedQuizDate"
							 required> <input
							id="editedQuizTime" type="time" name="editedQuizTime" placeholder="HH:MM"
							pattern="[0-9]{2}:[0-9]{2}" required>
							
						<input type="submit" placeholder="MM/DD/YYYY" id="editQuizButton" name="editQuizButton" value="Save quiz">
						<input type="submit" placeholder="MM/DD/YYYY" id="cancelEditQuiz" name="cancelEditQuiz" value="Cancel">
						
					</div>
				</form>
		
		<br>
		<br>
		<br>
		<table border=1>
			<tr>
				<td align='middle'>All quiz dates</td>
			</tr>
			<c:forEach items="${quizzesMap}" var="allQuiz">
				<tr>
					<td>${allQuiz.value} <button class="editButton" id="${allQuiz.key}"><i class='fas fa-edit'></i></button>
					<button > <i class='far fa-trash-alt'></i></button>
					
					

				</td>
								</tr>
				
			</c:forEach>
		</table>
		</div>

		<div id="retake">
				<a href="#" id="addRetake">Add new retake</a>
				<form id="addNewRetake" method="post" action="quizschedule">
					<div id="newRetake" style="display: none">
						<input name="repeatCount" id="repeatCount" style="display: none" value="0"></input><br>
						<input name="query" id="query" style="display: none" value="addRetake"></input><br>
						<label>Choose date and time for retake:</label> <input
							id="retakeDate" type="text" placeholder="MM/DD/YYYY" name="retakeDate"
							 required> <input
							id="retakeTime" type="time" name="retakeTime" placeholder="HH:MM"
							pattern="[0-9]{2}:[0-9]{2}" required>
							<label>Repeat Weekly: <input id="repeatCheckBoxR" type="checkbox" name="repeatCheckBoxR"></input></label><br>
						<div id="repeatDivR" style="display: none">
						
							Start Date: <input id="startDateR" name="startDateR" type="text" placeholder="MM/DD/YYYY"
								></input><br> End Date:
							<input id="endDateR" placeholder="MM/DD/YYYY" name="endDateR" type="text"
								></input><br>
						</div>
						<input type="submit" placeholder="MM/DD/YYYY" id="addRetakeButton" name="addRetakeButton" value="Add retake">
					</div>
				</form>
		
		<br>
		<br>
		<br>
		<table border=1>
			<tr>
				<td align='middle'>All retake dates</td>
			</tr>
			<c:forEach items="${allRetakes}" var="allRetake">
				<tr>
					<td>${allRetake} <button><i class='fas fa-edit'></i></button>
					<button > <i class='far fa-trash-alt'></i></button>

				</td>
								</tr>
				
			</c:forEach>
		</table>
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