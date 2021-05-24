<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Quiz Retake Scheduler</title>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

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
table {
  font-family:  Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 70%;
margin-left:auto;
margin-right:auto;
background: #ffffff;
}

h2{
  font-family:  Arial, Helvetica, sans-serif;

}
#courseAuthDiv{
 margin: auto;
  width: 60%;
  padding: 10px;

}

.btn{
  font-family:  Arial, Helvetica, sans-serif;
    background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 11px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  cursor: pointer;
}
td {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
table tr:hover {background-color: #ddd;}


th {
  border: 1px solid #dddddd;
  text-align: middle;
  padding: 8px;
  background-color:#b9cd6d
}

.divColor{
background: #b9cd6d;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
#tabs-1 {
	font-size: 14px;
}

.ui-widget-header {
	background: #b9cd6d;
	border: 1px solid #b9cd6d;
	color: #FFFFFF;
	font-weight: bold;
}

.ui-widget-content{
background:none}

input[type=text], select {
  width: 40%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

</style>
<title>Quiz Retake Scheduler</title>
</head>
<body bgcolor="#DDEEDD">

	<center>
		<h2>Quiz Retake Scheduler</h2>
	</center>
	<hr />
	<div id="courseAuthDiv">
		<span id="message">${message}</span>
		<form id="courseAuth" method="get" action="quizschedule">
		<input name="query" id="query" style="display: none" value="admin"></input><br>
		<label for="courseID">Select course id:</label>
		<input name="courseID" id="courseID" type="text" placeholder="Enter Course ID"></input>
		<input class="btn" type="submit" value="Submit">
		<span id="hint" style=" font-family:monospace; color:#888888"><em>Use courseID: cs123 </em></span>		
		</form>
		<br> <br>
	</div>
	<div id="admin" class="tab">
		<ul>
			<li><a href="#quiz">Quiz</a></li>
			<li><a href="#retake">Retake</a></li>
			<!-- <li><a href="#course">Course</a></li> -->
			<li><a href="#appointments">Appointments</a></li>

		</ul>
		<div id="quiz">
<!-- 		 DISABLED FOR HEROKU DEPLOYMENT		<a href="#" id="addQuiz">Add new quiz</a>
 -->				<form id="addNewQuiz" method="post" action="quizschedule">
					<div id="newQuiz" style="display: none">
						<input name="repeatCount" id="repeatCount" style="display: none" value="0"></input><br>
						<input name="query" id="query" style="display: none" value="addQuiz"></input><br>
						<label for="quizDate">Choose date and time for quiz:</label> <input
							id="quizDate" type="text" placeholder="MM/DD/YYYY" name="quizDate"
							 required> <input
							id="quizTime" type="time" name="quizTime" placeholder="HH:MM"
							pattern="[0-9]{2}:[0-9]{2}" required>
							<label for="repeatCheckBoxQ">Repeat Weekly: <input id="repeatCheckBoxQ" type="checkbox" name="repeatCheckBoxQ"></input></label><br>
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
						<label for="editId">Quiz Id:</label><input type="text" name="editId" id="editId" value=""></input><br>
						<label for="editedQuizDate">Choose date and time for quiz:</label> <input
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
				<th align='middle'>All Quiz Dates</th>
			</tr>

			<c:forEach items="${quizzesMap}" var="allQuiz">
			<tr>				
			<td>
	<%-- 	JSTL USE			<tr>
    <td><c:out value="${allQuiz.value.ID}"/>  </td>
    <td><c:out value="${allQuiz.value.dayOfWeek}, "/>
    <c:out value="${allQuiz.value.monthSet} "/>${allQuiz.value.date}, at ${allQuiz.value.time}"/> </td>
    <td>Italy</td>
  </tr> --%>


			${allQuiz.value} <%--  DISABLED FOR HEROKU DEPLOYMENT <button class="editButton" id="${allQuiz.key}"><i class='fas fa-edit'></i></button>
					<button > <i class='far fa-trash-alt'></i></button> --%>
					
			</td>
			</tr>
				
			</c:forEach>
		</table>
		</div>

		<div id="retake">
<!-- 		 DISABLED FOR HEROKU DEPLOYMENT		<a href="#" id="addRetake">Add new retake</a>
 -->				<form id="addNewRetake" method="post" action="quizschedule">
					<div id="newRetake" style="display: none">
						<input name="repeatCount" id="repeatCount" style="display: none" value="0"></input><br>
						<input name="query" id="query" style="display: none" value="addRetake"></input><br>
						<label for="retakeDate">Choose date and time for retake:</label> <input
							id="retakeDate" type="text" placeholder="MM/DD/YYYY" name="retakeDate"
							 required> <input
							id="retakeTime" type="time" name="retakeTime" placeholder="HH:MM"
							pattern="[0-9]{2}:[0-9]{2}" required>
							<label for="repeatCheckBoxR">Repeat Weekly: <input id="repeatCheckBoxR" type="checkbox" name="repeatCheckBoxR"></input></label><br>
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
				<th align='middle'>All Retake Dates</th>
			</tr>
			<c:forEach items="${allRetakes}" var="allRetake">
				<tr>
					<td>${allRetake}<!--  DISABLED FOR HEROKU DEPLOYMENT <button><i class='fas fa-edit'></i></button>
					<button > <i class='far fa-trash-alt'></i></button> -->
					</td>
				</tr>
				
			</c:forEach>
		</table>
		</div>
		<!-- DISABLED FOR HEROKU DEPLOYMENT <div id="course">
			<label>Choose date and time for quiz:</label> <input id="courseInfo"
				type="datetime-local" name="courseInfo"
				pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required>
			<input type="submit" value="Add course Info">

		</div> -->
		<div id="appointments">
		<br>
		<br>
		<br>
		<table border=1>
			<tr>
				<th align='middle'>All Appointment Dates</th>
			</tr>
			
			<c:forEach items="${allAppts}" var="allAppt">
				<tr>
				 	<td>${allAppt}<%--  DISABLED FOR HEROKU DEPLOYMENT  <button class="editButton" id="${allAppt.key}"><i class='fas fa-edit'></i></button>
					<button > <i class='far fa-trash-alt'></i></button> --%>
					</td> 
				</tr>
				
			</c:forEach>
		</table></div>
	</div>
	<p style="font-size:80%; font-family:monospace; color:#888888">
Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>Jan 2021
</p>
</body>
</html>