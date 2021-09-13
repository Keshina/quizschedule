<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
function changeAction() {
   
    if( window.location.pathname.includes('servlet') ) {
    	document.getElementById("authenticate").action= 'quizretakes.quizschedule';
    }
	   else
		   {
		   document.getElementById("authenticate").action= 'servlet/quizretakes.quizschedule';
		   }
}
	
</script>
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
  text-align:center;
  font-family: Arial, Helvetica, sans-serif;
  font-size:18px;
  color:#123d6a;
    margin-bottom:46px
  
  

}


.msg{
  font-family:  Arial, Helvetica, sans-serif;

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
  display: block;
  font-size: 14px;
  margin: auto;
  cursor: pointer;
}

.divColor{
background: #b9cd6d;
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

input, select {
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
<body bgcolor="#EBD5CA" onload="changeAction()">
<center><h2 style="color:#123d6a">Quiz Retake Scheduler Instructor Login</h2></center>
<hr />
<br><br>
<div id="welcomeMsg">

<span id="message">${message}</span>


<form name="authenticate" id="authenticate" method="post" action="/">
			<input name="query" id="query" style="display: none" value="authenticate"></input><br>
    <input type="text" name="username" id="username" size="25" autofocus placeholder="Username" required>
    <p><p>
    <input type="password" size="15" name="password" id="password" placeholder="Password" required>
    <p><p>
    <input type="submit" class="btn" value="Submit"><p>
    <input type="reset" class="btn" value="Reset">
</form>
</div>
<div id="footer">
	<p style="font-size:12px; font-family:monospace; color:#ab3b61">
Copyright&#169; Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>Sep 2021
</p></div>
</body>
</html>