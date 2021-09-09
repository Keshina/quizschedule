<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<script type='text/javascript'>
function myFunction() {
	history.go(-2);
 }
</script>
</script>

</head>
<body bgcolor="#EBD5CA">
<center><h2 style="color:#123d6a">Quiz Retake Scheduler</h2></center>
<hr />

<div id="welcomeMsg">
<span id="message">${message}</span>
<c:if test="${result == 'success'}">
<br></br>Please arrive in time to finish the quiz before the end of the retake period.<br></br>If you cannot make it, please cancel by sending email to your professor.
</c:if>
<c:if test="${result == 'failed'}">
Please try again.
</c:if>
			<br></br>
 <button id= "back" onClick="myFunction()" class="btn">Back</button>

</div>
<div id="footer">
	<p style="font-size:12px; font-family:monospace; color:#ab3b61">
Copyright&#169; Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>Sep 2021
</p></div>

</body>
</html>