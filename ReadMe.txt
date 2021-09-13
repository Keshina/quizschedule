1. Change the package default name to quizretakes. CS server deployment url is https://cs.gmu.edu/kbaral4/servlet/packageName.servletClassName.
2. Change the env variable to csServer in quizschedule.java, serverUtils.java Other options: csServer, local, heroku
3. In printQuizScheduleForm and printForm method of printQuizScheduleForm.java, change the localDate to now.
4. This version runs in windows with tomcat 9.0.4sth and java 13. When imported to mac, runs but changes in code not reflected in the build locally
 but runs as is on windows
5. Local run url: localhost:port/contextRoot/some-url-mapping. ContextRoot in project properties, some url mapping is the servlet url mapping.
6. printQuizSchedule has some html content for appointment list. Appointment list has some logical structure of if-else so haven't moved it to view alone.
7. 