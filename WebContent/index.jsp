<?xml version="1.0" encoding="UTF-8"?>
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

#welcomeMsg{
 margin: auto;
  width: 80%;
  padding: 10px;
  font-family: Arial, Helvetica, sans-serif;
  font-size:18px;
  color:#123d6a;
  margin-bottom:55px
  
  
  

}

.msg{
  font-family:  Arial, Helvetica, sans-serif;
  text-align:left;
  margin-left:-76px;

}

.labels{
	font-size: 18px;
	color:#ab3b61
}

.errorMsg{
  font-family:  Arial, Helvetica, sans-serif;
    color: red;
  

}

.btn{
  font-family:  Arial, Helvetica, sans-serif;
  background-color: #28a745; /* Green */
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
  font-family:  Arial, Helvetica, sans-serif;
  
}
table tr:hover {background-color: #ddd;}


th {
  border: 1px solid #dddddd;
  text-align: middle;
  padding: 8px;
  background-color:#b9cd6d;
  font-family:  Arial, Helvetica, sans-serif;
  
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

body{
min-height:400px;
margin-bottom:46px;
clear:both
}

</style>
<title>Quiz Retake Scheduler</title>
<script type='text/javascript'>
</script>

</head>
<body bgcolor="#EBD5CA">
<center><h2 style="color:#123d6a">Quiz Retake Scheduler</h2></center>
<hr />

<div id="welcomeMsg">
<span class="errorMsg" id="message">${message}</span>
	<form name="getCourseId" method="get" action="quizschedule">
		<span class="msg">Please enter the course ID given to you by your instructor.</span>
			<br></br>
		<div style="text-align:center">
		<label for="courseID" class="labels">Course ID:</label> 
		<input type="text" id="courseID" name="courseID" placeholder="Enter CourseID here" autofocus></input>
		<button id= "submitRequest" type="submit" class="btn">Submit</button>
		<span id="hint" style=" font-family:monospace; color:#888888"></span>		
</div>
	</form>

</div>
<div id="footer">
	<p style="font-size:12px; font-family:monospace; color:#ab3b61">
Copyright&#169; Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>Sep 2021
</p></div>

</body>
</html>