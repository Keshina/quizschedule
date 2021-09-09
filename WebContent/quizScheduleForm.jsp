<?xml version="1.0" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style>

#footer {
 position: fixed;
 bottom: 0px;
  background-color: #edc8b6;
  width: 100%;
  height: 46px;
  text-align: center;

}

body{
min-height:400px;
margin-bottom:46px;
clear:both
}

table {
  font-family:  Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
margin-left:auto;
margin-right:auto;
background: #EBD5CA; /*ffffff*/
}

h2{
  font-family:  Arial, Helvetica, sans-serif;

}

#welcomeMsg{
 margin: auto;
  width: 80%;
  padding: 10px;
  font-family: Arial, Helvetica, sans-serif;
  font-size:18px;
  color:#123d6a;
  margin-bottom:46px
  
  

}

.msg{
  font-family: Arial, Helvetica, sans-serif;
    text-align:left;
  margin-left:-76px;
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
  font-family: Arial, Helvetica, sans-serif;

}

.btn{
  font-family:  Arial, Helvetica, sans-serif;
    background-color: #28a745; /* Green */
  border: none;
  color: white;
  padding: 11px 32px;
  text-align: center;
  text-decoration: none;
  display: block;
  font-size: 18px;
  margin: auto;
  cursor: pointer;
}
td {
 /* border: 1px solid #dddddd;*/
  text-align: left;
  padding: 8px;
  font-family: Arial, Helvetica, sans-serif;
}
/* table tr:hover {background-color: #ddd;}
 */

th {
  border: 1px solid #dddddd;
  text-align: middle;
  padding: 8px;
  background-color:#b9cd6d;
  font-family: Arial, Helvetica, sans-serif;
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
  font-family: Arial, Helvetica, sans-serif;
  
}

.retakeRow{
background: #edc8b8;
border: none;
border-collapse: collapse;
 
}

.quizRow{
background: #ede2dc;
border-collapse: collapse;

}

.labels{
	font-size: 18px;
	color:#ab3b61
}
</style>
<title>Quiz retake scheduler</title>
<script type='text/javascript'>
	
</script>

</head>
<!-- <body bgcolor="#DDEEDD"> -->
<body bgcolor="#EBD5CA"> <!-- overall background -->
	<center>
		<h2 style="color:#123d6a">Quiz Retake Scheduler</h2>
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
				<label for="studentName" class="labels">Name: </label>
					<input type="text" id="studentName" name="studentName" autofocus></input> <br> <br>
									</div>
						
		<table><!--  style='background-color: #99dd99'> -->

			<c:forEach items="${selectedRetakes}" var="r">

				<tr class="retakeRow">
				
					<td class ="retakeRow"  colspan="2">
					${r.onlyCapitalizeFirstLetter(r.getDate().getDayOfWeek())}, ${r.onlyCapitalizeFirstLetter(r.getDate().getMonth())}
						${r.getDate().getDayOfMonth()}, at ${r.timeAsString(r.getTimeStart())} - ${r.timeAsString(r.getTimeEnd())} in
						${r.getLocation()}
						</td>
				</tr>

				<c:forEach items="${selectedQuizes.get(r.getID())}" var="q">
					<tr class="quizRow">
						<td align='right'>Quiz ${q.getID()} from ${q.onlyCapitalizeFirstLetter(q.getDayOfWeek())},
							${q.onlyCapitalizeFirstLetter(q.getDate().getMonth())} ${q.getDate().getDayOfMonth()}</td>

						<td class= "quizRow"><input type='checkbox' name='retakeReqs'
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
				<td align='middle'  style="color:#ab3b61;font-size: large;text-align: center;  text-transform: uppercase;">
				All quiz retake opportunities</td>
			</tr>
			<c:forEach items="${allRetakes}" var="allRetake">
				<tr>
					<td>${allRetake}				</td>
					
				</tr>
			</c:forEach>
		</table>

	</div>

	<div id="footer">
	<p style="font-size:12px; font-family:monospace; color:#ab3b61">
Copyright&#169; Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>Sep 2021
</p></div>

</body>
</html>