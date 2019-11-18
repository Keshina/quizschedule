<?xml version="1.0\" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Quiz retake scheduler</title>
<script type='text/javascript'>
	
</script>

</head>
<body bgcolor="#DDEEDD">
	<center>
		<h2>GMU quiz retake scheduler</h2>
	</center>
	<hr />
	<div id="welcomeMsg">
		<span id="message">${message}</span>
		<p>You can sign up for quiz retakes within the next two weeks.
			Enter your name (as it appears on the class roster), then select
			which date, time, and quiz you wish to retake from the following
			list.</p>
		<p>
			Today is ${today.getDayOfWeek()}, ${today.getMonth()}
			${today.getDayOfMonth() }
			<p>Currently scheduling quizzes for the next two weeks, until
				${endDay.getDayOfWeek()}, ${endDay.getMonth()}
				${endDay.getDayOfMonth() }</p>
	</div>
				
			<form name="setRetake" method="post" action="quizschedule">

				<p>
					Name: <input type="text" id="studentName" name="studentName"
						size="50"></input></p> <br> <br>
		<table border=1 style='background-color: #99dd99'>

			<c:forEach items="${selectedRetakes}" var="r">

				<tr>
					<td>${r.getDate().getDayOfWeek()},${r.getDate().getMonth()}
						${r.getDate().getDayOfMonth()} , at ${r.timeAsString()} in
						${r.getLocation()}</td>
				</tr>

				<c:forEach items="${selectedQuizes.get(r.getID())}" var="q">
					<tr>
						<td align='right'>Quiz ${q.getID()} from ${q.getDayOfWeek()},
							${q.getDate().getMonth()} ${q.getDate().getDayOfMonth()}</td>

						<td><input type='checkbox' name='retakeReqs'
							value='${r.getID()}, ${q.getID()}'
							id='q${q.getID()}r${r.getID()}'></input></td>
					</tr>
				</c:forEach>

			</c:forEach>
			<tr>
				<td align='middle'><button id='submitRequest' type='submit'
						name='submitRequest' style='font-size: large'>Submit
						request</button></td></tr>
		</table>
						
		</form>
		<br></br>
		
		
		<table border=1>
			<tr>
				<td align='middle'>All quiz retake opportunities</td>
			</tr>
			<c:forEach items="${allRetakes}" var="allRetake">
				<tr>
					<td>${allRetake}				</td>
					
				</tr>
			</c:forEach>
		</table>

	</div>

	<p style="font-size: 80%; font-family: monospace; color: #888888">
		Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt <br />November 2019
	</p>

</body>
</html>