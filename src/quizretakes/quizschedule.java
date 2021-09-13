package quizretakes;
// KB -Jan-2019

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.sun.xml.internal.txw2.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.time.*;
import java.lang.Long;
import java.lang.String;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Jeff Offutt Date: January, 2019
 *
 *         Wiring the pieces together: quizschedule.java -- Servlet entry point
 *         for students to schedule quizzes quizReader.java -- reads XML file
 *         and stores in quizzes. Used by quizschedule.java quizzes.java -- A
 *         list of quizzes from the XML file Used by quizschedule.java
 *         quizBean.java -- A simple quiz bean Used by quizzes.java and
 *         readQuizzesXML.java retakesReader.java -- reads XML file and stores
 *         in retakes. Used by quizschedule.java retakes.java -- A list of
 *         retakes from the XML file Used by quizschedule.java retakeBean.java
 *         -- A simple retake bean Used by retakes.java and readRetakesXML.java
 *         apptBean.java -- A bean to hold appointments
 * 
 *         quizzes.xml -- Data file of when quizzes were given retakes.xml --
 *         Data file of when retakes are given
 */

/*
 * TODO: 1. if courseID== null in any post action ask to reenter the courseId 2.
 * add new function for adding retake 
 */

@WebServlet("/quizretakes.quizschedule") // uncomment this to run locally
public class quizschedule extends HttpServlet {
	// Data files
	// location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
	private static final String dataLocation = serverUtils.getDatalocation();
	static private final String separator = serverUtils.getSeparator();
	private static final String courseBase = serverUtils.getCoursebase();
	private static final String quizzesBase = serverUtils.getQuizzesbase();
	private static final String retakesBase = serverUtils.getRetakesbase();
	private static final String apptsBase = serverUtils.getApptsbase();

	// Filenames to be built from above and the courseID parameter
	private String courseFileName;
	private String quizzesFileName;
	private String retakesFileName;
	private String apptsFileName;

	private static int daysAvailable = serverUtils.getDaysAvailable();

	// Passed as parameter and stored in course.xml file (format: "swe437")
	private String courseID;
	// To be set by getRequestURL()
	private String thisServlet = "";
	private String env = "csServer";
	private Boolean isAuthenticated = false;

	// doGet() : for get requests
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Whoami? (Used in form)
		thisServlet = (request.getRequestURL()).toString();

		// CS server has a flaw--requires https & 8443, but puts http & 8080 on the
		// requestURL
		if (env.equals("csServer")) {
			thisServlet = thisServlet.replace("http", "https");
			thisServlet = thisServlet.replace("8080", "8443");
		}

		// CourseID must be a parameter (also in course XML file, but we need to know
		// which course XML file ...)
		
		courseID = request.getParameter("courseID");
		String query = request.getParameter("query");
		System.out.println(query + "query");
		String destinationAddress;
		if (courseID == null || courseID.isEmpty()) { 
			String message="";
			if(request.getParameterMap().containsKey("courseID"))
			 message = "Please enter a course ID and try again.";
			else
				message="";
			if ((query == null || query.isEmpty())) {// && !isAuthenticated) { //if used others can't access unless admin logs out.
				destinationAddress = "/index.jsp";
			} else
				destinationAddress = "/admin.jsp";
			servletUtils.displayMessage(message, destinationAddress, request, response);
		} else {

			courseBean course;
			courseReader cr = new courseReader();
			courseFileName = dataLocation + courseBase + "-" + courseID + ".xml";
			try {
				course = cr.read(courseFileName);
			} catch (Exception e) {
				String message = "We don't support quiz scheduling of course " + courseID
						+ " right now. Please contact kbaral4@gmu.edu if you want to make it available.";
				if (query == null || query.isEmpty()) {
					destinationAddress = "/index.jsp";
				} else
					destinationAddress = "/admin.jsp";
				servletUtils.displayMessage(message, destinationAddress, request, response);
				return;
			}

			daysAvailable = Integer.parseInt(course.getRetakeDuration());

			serverUtils srvrUtils = new serverUtils();

			try {
				printQuizScheduleForm pf = srvrUtils.readAllData(courseID, course);
				if (query == null || query.isEmpty()) {
					pf.printForm(request, response);
				} else
					pf.printDataForAdmin(request, response, "", isAuthenticated);
			} catch (Exception e) {
				System.out.println("exception-----------------" + e);
				String message = "We don't support quiz scheduling of course " + courseID
						+ " right now. Please contact kbaral4@gmu.edu if you want to make it available.";
				if (query == null || query.isEmpty()) {
					destinationAddress = "/index.jsp";
				} else
					destinationAddress = "/admin.jsp";
				servletUtils.displayMessage(message, destinationAddress, request, response);

			}

		}

	}

	// doPost for all post requests
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		thisServlet = request.getRequestURL().toString();
		// Filename to be built from above and the courseID
		String query = request.getParameter("query");
		if (query.equals("addQuiz")) {
			try {
				QuizUtils qu = new QuizUtils();
				qu.addQuiz(response, request, courseID, isAuthenticated);
			} catch (ParserConfigurationException | SAXException | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (query.equals("addRetake")) {
			try {
				RetakeUtils ru = new RetakeUtils();
				ru.addRetake(response, request, courseID);
			} catch (ParserConfigurationException | SAXException | TransformerException e) {
				e.printStackTrace();
			}

		} else if (query.equals("addCourse")) {
			try {
				CourseUtils cu = new CourseUtils();
				cu.addCourse(response, request, courseID);
			} catch (ParserConfigurationException | SAXException | TransformerException e) {
				e.printStackTrace();
			}
		} else if (query.equals("addAppointment")) {
			try {
				AppointmentUtils au = new AppointmentUtils();
				au.addAppointment(response, request, courseID);
			} catch (ParserConfigurationException | SAXException | TransformerException e) {
				e.printStackTrace();
			}
		}

		else if (query.equals("authenticate")) {

			System.out.println("AUTHETICATION REQ");
			String destinationAddress;
			serverUtils srvrUtils = new serverUtils();
			isAuthenticated = srvrUtils.verifyUser(request.getParameter("username"), request.getParameter("password"));
			if (isAuthenticated) {
				System.out.println("AUTHETICATED ");
				String message = "";
				Cookie loginCookie = new Cookie("user", request.getParameter("username")); // setting cookie to expiry
																							// in 30 mins
				loginCookie.setMaxAge(30 * 60);
				response.addCookie(loginCookie);
				destinationAddress = "/admin.jsp"; 
				servletUtils.displayMessage(message, destinationAddress, request, response);
			} else {
				System.out.println("NOt AUTHETICATIED");
				String message = "Login unsuccessful. Try again"; 
				destinationAddress = "/instructor.jsp";
				servletUtils.displayMessage(message, destinationAddress, request, response);
			}
		}

		else if (query.equals("logout")) {
			String destinationAddress;
			String message = "";
			isAuthenticated = false;
			response.setContentType("text/html");
			Cookie loginCookie = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user")) {
						loginCookie = cookie;
						break;
					}
				}
			}
			if (loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
			destinationAddress = "/instructor.jsp";
			//servletUtils.displayMessage(message, destinationAddress, request, response); // avoid this because after logout have to enter info twice
			// To redirect instead of forward: issue is the uri changes after logging out
			thisServlet = (request.getRequestURL()).toString();
			if (env.equals("csServer")) {
				thisServlet = thisServlet.replace("http", "https");
				thisServlet = thisServlet.replace("8080", "8443");
				destinationAddress = thisServlet.split("/servlet/")[0] + "/instructor.jsp";
			}
			response.sendRedirect(destinationAddress);

		}

	}

}

// end quizschedule class
