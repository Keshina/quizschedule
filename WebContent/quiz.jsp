<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Retake Scheduler</title>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css
         "
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>
	$(function() {
		var requesturl = "http://localhost:8080/quizretakes/src/quizschedule";
		$("#admin").tabs();
		// $( "#quizDate" ).datepicker();

		//show new quiz form when add quiz clicked
		$("#addQuiz").click(function() {
			$("#newQuiz").show();
		});

		// For the repeat event
		$("#repeat").change(function() {
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

		$('#addNewQuiz').attr('action', requesturl);

	});

	//count for repeat weekly
	var getWeeklyEventCount = function(startDate, endDate) {
		var secondOccurrence = new Date(startDate.getFullYear(), startDate
				.getMonth(), startDate.getDate() + 7);
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
</head>
<body bgcolor="#DDEEDD">
	<center>
		<h2>GMU quiz retake scheduler</h2>
	</center>
	<hr />
	<div id="courseAuth">
		<form id="courseAuth" method="get" action="">
			<label>Select course id:</label><input name="courseID" id="courseID"
				type="text"></input>
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
				<form id="addNewQuiz" method="post" action="">
					<div id="newQuiz" style="display: none">

						<input name="req" id="req" style="display: none" value="addQuiz"></input><br>
						<label>Choose date and time for quiz:</label> <input
							id="quizDate" type="date" name="quizDate"
							pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" required> <input
							id="quizTime" type="time" name="quizTime"
							pattern="[0-9]{2}:[0-9]{2}" required><br> <input
							id="repeat" type="checkbox" name="repeat">Repeat Weekly</input><br>
						<div id="repeatDiv" style="display: none">
							Start Date: <input id="startDate" name="startDate" type="date"
								pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"></input><br> End Date:
							<input id="endDate" name="endDate" type="date"
								pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"></input><br>
						</div>
						<input type="submit" value="Add quiz">
					</div>
				</form>
		
		<table border=1>
			<tr>
				<td align='middle'>All quiz dates</td>
			</tr>
			<c:forEach items="${allQuizzes}" var="allQuiz">
				<tr>
					<td>${allQuiz}
				</tr>
				</td>
			</c:forEach>
		</table>
		</div>

		<!-- <div id="retake">
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
		<div id="appointments"></div> -->
	</div>
</body>
</html>