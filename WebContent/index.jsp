<?xml version="1.0\" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Quiz retake scheduler</title>
<script type='text/javascript'>
</script>

</head>
<body bgcolor="#DDEEDD">
<center><h2>GMU quiz retake scheduler</h2></center>
<hr />
<div id="welcomeMsg">
<span id="message">${message}</span>
<form name="getCourseId" method="get" action="quizschedule">
<p>Please enter the course ID given to you by your instructor. 
It is probably the same as the university course Id, with no spaces.
<br/>
<p>courseID: 
<input type="text" autofocus id="courseID" name="courseID" size="15"></input>
<br/><br/>
<button id= "submitRequest" type="submit" style="font-size:large;">Submit</button>
</p></p>
</form>

</div>


<p style="font-size:80%; font-family:monospace; color:#888888">
Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>November 2019
</p>

</body>
</html>