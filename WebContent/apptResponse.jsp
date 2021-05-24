<?xml version="1.0\" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
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

#welcomeMsg{
 margin: auto;
  width: 60%;
  padding: 10px;
    font-size: 18px;
  
  text-align:center;

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
<body bgcolor="#DDEEDD">
<center><h2>Quiz Retake Scheduler</h2></center>
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


<p style="font-size:80%; font-family:monospace; color:#888888">
 Kesina Baral &amp; Rasika Mohod &amp; Jeff Offutt
<br/>May 2021
</p>

</body>
</html>