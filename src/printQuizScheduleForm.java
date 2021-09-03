import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */

/**
 * @author kesin
 *
 */
public class printQuizScheduleForm {
	boolean skip = false;
	boolean retakePrinted = false;
	quizzes quizList;
	retakes retakesList;
	appts apptsList;
	courseBean course;
	LocalDate startSkip;
	LocalDate endSkip;
	LocalDate today;
	LocalDate endDay;
	LocalDate origEndDay;
	String thisServlet = "";
	PrintWriter out;
	int daysAvailable;
	static private final String separator = ",";
	
	
	/*
	 * private static final String dataLocation =
	 * "C:/Users/kesin/eclipse-workspace/quizretakes/src/";///
	 * var/www/CS/webapps/offutt/WEB-INF/data/"; private static final String
	 * courseBase = "course"; private static final String quizzesBase = "quiz-orig";
	 * private static final String retakesBase = "quiz-retakes"; private static
	 * final String apptsBase = "quiz-appts";
	 */

	// Filenames to be built from above and the courseID parameter
	private String courseFileName;
	private String quizzesFileName;
	private String retakesFileName;
	private String apptsFileName;


	printQuizScheduleForm(){
		
	}
	
	
	printQuizScheduleForm(quizzes quizList, retakes retakesList, appts apptsList, courseBean course, int daysAvailable) {
		System.out.println(course+"PRETINT");

		this.quizList = quizList;
		this.retakesList = retakesList;
		this.apptsList = apptsList;
		this.course = course;
		//today = LocalDate.of(2021, 01, 10)
		today = LocalDate.now();
		startSkip = course.getStartSkip();
		endSkip = course.getEndSkip();
		endDay = today.plusDays(Long.valueOf(daysAvailable));
		origEndDay = endDay;
		this.daysAvailable = daysAvailable;
	}

	private boolean isSkipInTwoWeeks() {// changed-Kesina
		if (!((today.isBefore(startSkip) && endDay.isBefore(startSkip))
				|| (today.isAfter(endSkip) && endDay.isAfter(endSkip)))) {

			skip = true;
		}
		return skip;
	}

	private boolean isSkipForQuiz(LocalDate quizDay, LocalDate retakeDay, LocalDate lastAvailableDay) {// changed-Kesina
		if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) && !today.isAfter(retakeDay)
				&& !retakeDay.isAfter(endDay)) {

			skip = true;
		}
		return skip;
	}

	private void extendEndDay() {// changed-Kesina
		long diff = ChronoUnit.DAYS.between(startSkip, endSkip);
		endDay = endDay.plusDays(diff);
	}

	private LocalDate extendLastQuizDay(LocalDate quizDay, LocalDate lastAvailableDay) {
		if ((quizDay.isBefore(startSkip) && startSkip.isBefore(lastAvailableDay))
				|| (quizDay.isBefore(endSkip) && endSkip.isBefore(lastAvailableDay))) {
			lastAvailableDay = lastAvailableDay.plusDays(new Long(7));
		}
		return lastAvailableDay;
	}
	
	protected void printApptScheduleResponse(HttpServletRequest req, HttpServletResponse resp, String msg, String name) throws ServletException, IOException {
		
		String message = "";
		String result = "";
		if (msg.equals("failure")){
			message= "Oops! There was a glitch and your appointment could not be saved. If the error persists, contact your TA to schedule the retake.";
			result="failed";

		}
		else if(msg.equals("noName") ) {
			message ="You didn't give a name. No anonymous quiz retakes allowed.";
			result="failed";

		}
		else if (msg.equals("noQuiz")) {
			message= "You didn't choose any quizzes to retake.";
			result="failed";

		}
		else if(msg.equals("successOne")) {
			message= name + ", your appointment has been scheduled.";
			result="success";

		}
		
		else if (msg.equals("successMultiple")) {
			message = name + ", your appointments have been scheduled."; 
			result="success";
		}
		req.setAttribute("message", message);
		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/apptResponse.jsp");
		dispatcher.forward(req, resp);
	
	}

	//sends all relevant data for adminView
	protected void printDataForAdmin(HttpServletRequest req, HttpServletResponse resp, String msg)
			throws ServletException, IOException {

		List<quizBean> allQuizzes = new ArrayList<>();
		Map<Integer, quizBean> quizzesMap = new HashMap();
		List<retakeBean> allRetakes = new ArrayList<>();
		Map<Integer,String> retakeToPrint = new HashMap();
		Map<Integer, String> quizToPrint = new HashMap();
		Map<Integer,HashMap> apptToPrint = new HashMap();

		List<apptBean> allAppts = new ArrayList<>();
		List<String> appListToPrint = new ArrayList<>();
		List<String> retakeListToPrint = new ArrayList<>();
		Map<Integer, String> quizMapToPrint = new HashMap();


		
		
		for (retakeBean r : retakesList) {
			allRetakes.add(r);
			retakeListToPrint.add(r.retakeToPrint());
			Integer id = r.getID();
			retakeToPrint.put(id,r.retakeToPrint());
			
			
		}
		for (quizBean q : quizList) {
			allQuizzes.add(q);
			quizzesMap.put(Integer.valueOf(q.getID()),q); //old way for admin.jsp
			quizMapToPrint.put(Integer.valueOf(q.getID()),q.quizToPrint());
//			System.out.println(q);
//			System.out.println(q.toString()+"-----------");
			
		}
		HashMap retakeAppt = new HashMap();
		HashMap <Integer,HashMap>quizAppt = new HashMap();
		for (apptBean a: apptsList) {
			
			String studentDetail ="";
			Integer rId = a.getRetakeID();
			Integer qId = a.getQuizID();
			String retakeDetail = retakeToPrint.get(rId);
			retakeAppt.put(rId, retakeDetail);
			
			if(quizAppt.containsKey(rId)) {
				HashMap quizAndStudent = (HashMap) quizAppt.get(rId);
				if(quizAndStudent.containsKey(qId)) {
					String studentList = (String) quizAndStudent.get(qId);
					studentList+=", "+a.getName();
					quizAndStudent.put(qId,studentList);
				}
				else {
					quizAndStudent.put(qId,a.getName());
				}
			}
				else {
					HashMap temp =  new HashMap();
					temp.put(qId, a.getName());
					quizAppt.put(rId, temp);
				}
		}
		
		for (Integer retakeId : quizAppt.keySet())	{	
			
			HashMap<Integer,String> temp = quizAppt.get(retakeId);
			String retakeDetail = (String) retakeAppt.get(retakeId);
			String studentDetail="&emsp;";
			for (Integer quizId : temp.keySet())	{	
			 studentDetail += "Quiz "+quizId+" : "+temp.get(quizId)+"<br>&emsp;";
			}
			String toPrint = retakeDetail+"<br>"+studentDetail;
			appListToPrint.add(toPrint);
		}		

		req.setAttribute("allRetakes", retakeListToPrint); //old way for admin.jsp was allRetakes
		req.setAttribute("allQuizzes", allQuizzes);
//		req.setAttribute("quizzesMap", quizzesMap); //old way for admin.jsp
		req.setAttribute("quizzesMap", quizMapToPrint);
		req.setAttribute("allAppts", appListToPrint); //old way for admin.jsp allApppts
		req.setAttribute("message", msg);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(req, resp);

	}
	
	


	protected void printForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check for a week to skip
		String query = "addAppointment";
		System.out.println(thisServlet + "-------PrintFORM-------");
		String courseID = course.getCourseID();
		//today = LocalDate.of(2021, 2, 10);
		today = LocalDate.now();
		endDay = today.plusDays(Long.valueOf(daysAvailable));

		if (isSkipInTwoWeeks())
			extendEndDay();

		req.setAttribute("today", today);
		req.setAttribute("endDay", endDay);

		System.out.println("TODAY AND END DAY SET");

		List<retakeBean> selectedRetakes = new ArrayList<>();
		Map<Integer, List> selectedQuizes = new HashMap<>();
		List<retakeBean> allRetakes = new ArrayList<>();

		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			LocalTime retakeTime = r.getTimeStart();

			if (!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
				// if skip && retakeDay is after the skip week, print a white bg message
				if (skip && retakeDay.isAfter(endSkip)) // changed-Kesina
				{ // A "skip" week such as spring break.

					// Just print for the FIRST retake day after the skip week

					String message = "Skipping a week, no quiz or retakes.";
					skip = false;
					req.setAttribute("message", message);

				}

				// checks if retake dates fall during skipweek
				if (!((retakeDay.isAfter(startSkip) && retakeDay.isBefore(endSkip)) || retakeDay.equals(startSkip)
						|| retakeDay.equals(endSkip))) {

					retakePrinted = true;
					selectedRetakes.add(r);

					List<quizBean> selectedQuizList = new ArrayList<quizBean>();

					for (quizBean q : quizList) {
						LocalDate quizDay = q.getDate();
						LocalTime quizTime = q.getTime();
						LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));
						lastAvailableDay = extendLastQuizDay(quizDay, lastAvailableDay);
						// To retake a quiz on a given retake day, the retake day must be within two
						// ranges:
						// quizDay <= retakeDay <= lastAvailableDay --> (!quizDay > retakeDay) &&
						// !(retakeDay > lastAvailableDay)
						// today <= retakeDay <= endDay --> !(today > retakeDay) && !(retakeDay >
						// endDay)

						// if(isSkipForQuiz(quizDay, retakeDay, lastAvailableDay))
						if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay)
								&& !today.isAfter(retakeDay) && !retakeDay.isAfter(endDay)) {
							if (!(retakeDay.equals(lastAvailableDay) && retakeTime.isAfter(quizTime))) {

								selectedQuizList.add(q);

							}
						}
					}
					selectedQuizes.put(r.getID(), selectedQuizList);
				}
			}

		}

		for (retakeBean r : retakesList) {
			allRetakes.add(r);

		}
		req.setAttribute("selectedRetakes", selectedRetakes);
		req.setAttribute("selectedQuizes", selectedQuizes);
		req.setAttribute("allRetakes", allRetakes);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/quizScheduleForm.jsp");
		dispatcher.forward(req, resp);
	}
}
