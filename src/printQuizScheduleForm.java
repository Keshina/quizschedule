import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
	
	private static final String dataLocation = "C:/Users/kesin/eclipse-workspace/quizretakes/src/";/// var/www/CS/webapps/offutt/WEB-INF/data/";
	private static final String courseBase = "course";
	private static final String quizzesBase = "quiz-orig";
	private static final String retakesBase = "quiz-retakes";
	private static final String apptsBase = "quiz-appts";

	// Filenames to be built from above and the courseID parameter
	private String courseFileName;
	private String quizzesFileName;
	private String retakesFileName;
	private String apptsFileName;


	printQuizScheduleForm(){
		
	}
	printQuizScheduleForm(quizzes quizList, retakes retakesList, courseBean course, int daysAvailable) {
		this.quizList = quizList;
		this.retakesList = retakesList;
		this.course = course;
		today = LocalDate.of(2019, 02, 14);// LocalDate.now();
		startSkip = course.getStartSkip();
		endSkip = course.getEndSkip();
		endDay = today.plusDays(new Long(daysAvailable));
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
		Duration duration = Duration.between(startSkip, endSkip);
		long diff = Math.abs(duration.toDays());
		endDay = endDay.plusDays(diff);
	}

	private LocalDate extendLastQuizDay(LocalDate quizDay, LocalDate lastAvailableDay) {
		if ((quizDay.isBefore(startSkip) && startSkip.isBefore(lastAvailableDay))
				|| (quizDay.isBefore(endSkip) && endSkip.isBefore(lastAvailableDay))) {
			lastAvailableDay = lastAvailableDay.plusDays(new Long(7));
		}
		return lastAvailableDay;
	}

	//sends all relevant data for adminView
	protected void printDataForAdmin(HttpServletRequest req, HttpServletResponse resp, String msg)
			throws ServletException, IOException {

		List<quizBean> allQuizzes = new ArrayList<>();
		List<retakeBean> allRetakes = new ArrayList<>();
		for (retakeBean r : retakesList) {
			allRetakes.add(r);
		}
		for (quizBean q : quizList) {
			allQuizzes.add(q);
		}

		req.setAttribute("allRetakes", allRetakes);
		req.setAttribute("allQuizzes", allQuizzes);
		req.setAttribute("message", msg);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin.jsp");
		dispatcher.forward(req, resp);

	}
	
	


	protected void printForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check for a week to skip
		String query = "addAppointment";
		System.out.println(thisServlet + "-------PrintFORM-------");
		String courseID = course.getCourseID();
		today = LocalDate.of(2019, 02, 14);// LocalDate.now();
		endDay = today.plusDays(new Long(daysAvailable));

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
			LocalTime retakeTime = r.getTime();

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
