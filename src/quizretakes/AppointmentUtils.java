package quizretakes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author kesin
 *
 */
public class AppointmentUtils {
	
	// Data files
			// location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
			// These names show up in all servlets
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
			private String thisServlet;
			
	protected void addAppointment(HttpServletResponse response, HttpServletRequest request, String courseID) 
			throws IOException, ServletException, ParserConfigurationException, SAXException, TransformerException {
			boolean IOerrFlag = false; 
			String IOerrMessage = ""; 
			String apptsFileName =	dataLocation + apptsBase + "-" + courseID + ".txt";

			// Get name and list of retake requests from parameters
			String studentName = request.getParameter("studentName");
			String[] allIDs =	request.getParameterValues("retakeReqs");
			printQuizScheduleForm pf = new printQuizScheduleForm();
			if (allIDs != null && studentName != null && studentName.length() > 0) { 
			//	Append the new appointment to the file
				try {
				File file = new File(apptsFileName); 
				synchronized (file) { // Only one student should touch this file at a time.
					if (!file.exists()) {
						file.createNewFile(); 
						} 
					FileWriter	fw = new FileWriter(file.getAbsoluteFile(), true); // append mode
					BufferedWriter bw = new BufferedWriter(fw);
						for (String oneIDPair : allIDs) { //oneIDPair consists of retakeID, quizID
							System.out.println(oneIDPair);
							bw.write(oneIDPair + separator + studentName + "\n"); }

							bw.flush();
							bw.close(); 
							} // end synchronize block
				} catch (IOException e) {
					System.out.println(e);
						IOerrFlag = true;
}

				// Respond to the student
				if (IOerrFlag) { 
					IOerrMessage = "failure";
					pf.printApptScheduleResponse(request, response, IOerrMessage,"");
				}
				else {
				if (allIDs.length ==1) {
					IOerrMessage ="successOne";
					pf.printApptScheduleResponse(request, response, IOerrMessage,studentName);
				}
				else {
					
					IOerrMessage ="successMultiple";
					pf.printApptScheduleResponse(request, response, IOerrMessage,studentName);}
					}

		}else{ 
			if (allIDs == null)
				pf.printApptScheduleResponse(request, response, "noQuiz",studentName);
			else if (studentName == null || studentName.length() == 0)
				pf.printApptScheduleResponse(request, response, "noName",studentName);
		}
	}		
}


