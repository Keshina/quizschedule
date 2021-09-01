1. quizschedule.java : change the env variable if need be. options: csServer, local, heroku
2. serverUtils.java: change the env variable if need be. options: csServer, local, heroku
3. In printQuizScheduleForm and printForm method of printQuizScheduleForm.java, change the localDate to now if need be.
4. This version runs in windows with tomcat 9.0.4sth and java 13. When imported to mac, didn't run locally but runs as is on windows
5. Changes for heroku run in github's heroku branch
6. deploy changes to heroku. Export war of the project and run heroku war:deploy quizschedule.war --includes data --app quizscheduler. data folder has all the xml files.
7. To check log of heroku: heroku logs --tail --app quizscheduler  
8. To get access to heroku server side: heroku run bash -a quizscheduler . Might need to install heroku plugin and login for all the heroku command
git push origin herokuQuiz
change today's date in printQuizScheduleForm.java file
