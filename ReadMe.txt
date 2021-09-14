Deployment notes for CS server 
The package default name cannot be used. Needs to have some name. 
Change the env variable to csServer in quizschedule.java, serverUtils.java 
Expression language used in .jsp files reads obj.attribute if it has a getter not obj.getter() directly. 
In printQuizScheduleForm and printForm method of printQuizScheduleForm.java, change the localDate to now. 
 CS server deployment url for servlets is https://cs.gmu.edu:8443/kbaral4/servlet/packageName.servletClassName. 
CS server deployment url for jsp files is https://cs.gmu.edu:8443/kbaral4/***.jsp. Jsp needs to be stored as kbaral4/***.jsp on server. To move it elsewhere, change code too.
The xml files go in /var/www/CS/webapps/kbaral4/WEB-INF/data/. This path is specified in serverUtils.java.
CS server might show 500 error for some time when a new application is added to server. Waiting a day for the server to acknowledge the addition (probably a server reboot happening) solves itself.
Admin access at: https://cs.gmu.edu:8443/kbaral4/instructor.jsp

Additional notes:

Retakes xml have start and end time instead of just start time. Changes in xml and retakesReader.java 
The application runs in windows with tomcat 9.0.4sth and java 13. When imported to mac, runs but changes in code not reflected in the build locally but runs as is on windows.
Local compilation url: localhost:port/contextRoot/some-url-mapping. ContextRoot in project properties, some url mapping is the servlet url mapping.
The package can be default when running locally