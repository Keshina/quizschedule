<?xml version="1.0\" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style>
table {
  font-family:  Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
margin-left:auto;
margin-right:auto;
background: #ffffff;
}

h2{
  font-family:  Arial, Helvetica, sans-serif;

}

#welcomeMsg{
 margin: auto;
  width: 80%;
  padding: 10px;
  text-align:center;

}

.msg{
  font-family: Arial, Helvetica, sans-serif;

}

.errorMsg{
  font-family:  Arial, Helvetica, sans-serif;
    color: red;
  

}
#name{
 margin: auto;
  width: 60%;
  padding: 10px;
  text-align:center;

}

.btn{
  font-family:  Arial, Helvetica, sans-serif;
    background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 11px 32px;
  text-align: center;
  text-decoration: none;
  display: block;
  font-size: 14px;
  margin: auto;
  cursor: pointer;
}
td {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
/* table tr:hover {background-color: #ddd;}
 */

th {
  border: 1px solid #dddddd;
  text-align: middle;
  padding: 8px;
  background-color:#b9cd6d
}

.divColor{
background: #b9cd6d;
}
/* tr:nth-child(even) {
  background-color: #f2f2f2;
} */
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
   margin: auto;
  width: 60%;
  
}

</style>
<title>Quiz retake scheduler</title>
<script type='text/javascript'>
	
</script>

</head>
<body bgcolor="#DDEEDD">
	<center>
		<h2>Quiz Retake Scheduler</h2>
	</center>
	<hr />
	<div id="welcomeMsg">
		<span id="message">${message}</span>
		<div class="msg">You can sign up for quiz retakes within the next two weeks.<br />
			Today is <em>${today.getDayOfWeek()}, ${today.getMonth()}
			${today.getDayOfMonth()}</em>.
			Currently scheduling quizzes for the next two weeks, until
				<em>${endDay.getDayOfWeek()}, ${endDay.getMonth()}
				${endDay.getDayOfMonth()}</em>.</div><br>
			<div class="msg">
			Enter your name (as it appears on the class roster), then select
			which date, time, and quiz you wish to retake from the following
			list.</div><br></br>
		
			
	</div>
			<form name="setRetake" method="post" action="quizschedule">
			<input name="query" id="query" style="display: none" value="addAppointment"></input><br>
<div id="name" class="name">
				<label for="studentName">Name: </label>
					<input type="text" id="studentName" name="studentName"></input> <br> <br>
									</div>
						
		<table border=1 style='background-color: #99dd99'>

			<c:forEach items="${selectedRetakes}" var="r">

				<tr>
				
					<td>${r.onlyCapitalizeFirstLetter(r.getDate().getDayOfWeek())}, ${r.onlyCapitalizeFirstLetter(r.getDate().getMonth())}
						${r.getDate().getDayOfMonth()} , at ${r.timeAsString()} in
						${r.getLocation()}</td>
				</tr>

				<c:forEach items="${selectedQuizes.get(r.getID())}" var="q">
					<tr>
						<td align='right'>Quiz ${q.getID()} from ${q.onlyCapitalizeFirstLetter(q.getDayOfWeek())},
							${q.onlyCapitalizeFirstLetter(q.getDate().getMonth())} ${q.getDate().getDayOfMonth()}</td>

						<td><input type='checkbox' name='retakeReqs'
							value='${r.getID()}, ${q.getID()}'
							id='q${q.getID()}r${r.getID()}'></input></td>
					</tr>
				</c:forEach>

			</c:forEach>
			<tr>
				<td align='middle'><button class="btn" id='submitRequest' type='submit'
						name='submitRequest'>Submit
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
		 Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt <br />Jan 2021
	</p>

</body>
</html>