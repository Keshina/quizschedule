import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author kesin
 *
 */
public class QuizUtils {

	
	private void addQuiz(HttpServletResponse response, HttpServletRequest request)
			throws IOException, ServletException, ParserConfigurationException, SAXException, TransformerException {
		// No saving if IOException
		boolean IOerrFlag = false;
		String IOerrMessage = "";
		String quizFileName = dataLocation + quizzesBase + "-" + courseID + ".xml";
				//dataLocation + "quizFile" + courseID + ".xml";

		courseBean course;
		courseReader cr = new courseReader();
		courseFileName = dataLocation + courseBase + "-" + courseID + ".xml";
		course = cr.read(courseFileName);
		printQuizScheduleForm pf = readAllData(courseID,course);
		
		
		// Get date and time for new quiz from parameters
		String quizDate = request.getParameter("quizDate");
		String quizTime = request.getParameter("quizTime");
		String repeatCount = request.getParameter("repeatCount");


		if (quizDate != null && quizDate.length() > 0 && quizTime != null && quizTime.length() > 0) {
			// add quiz to new file
			try {
				File file = new File(quizFileName);
				synchronized (file) { // Only one student should touch this file at a time.
					if (!file.exists()) {
						file.createNewFile();

						try {
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
							org.w3c.dom.Document doc = dBuilder.newDocument();

							// root element
							Element rootElement = doc.createElement("quizzes");
							doc.appendChild(rootElement);

							// root element
							Element quizElement = doc.createElement("quiz");
							rootElement.appendChild(quizElement);

							// id element
							Element id = doc.createElement("id");
							id.appendChild(doc.createTextNode("1"));

							quizElement.appendChild(id);
							// dateGiven element
							Element dateGiven = doc.createElement("dateGiven");
							quizElement.appendChild(dateGiven);

							// month element
							Element month = doc.createElement("month");
							month.appendChild(doc.createTextNode(quizDate.split("/")[0]));
							dateGiven.appendChild(month);

							// day element
							Element day = doc.createElement("day");
							day.appendChild(doc.createTextNode(quizDate.split("/")[1]));
							dateGiven.appendChild(day);

							// hour element
							Element hour = doc.createElement("hour");
							hour.appendChild(doc.createTextNode(quizTime.split(":")[0]));
							dateGiven.appendChild(hour);

							// minute element
							Element minute = doc.createElement("minute");
							minute.appendChild(doc.createTextNode(quizTime.split(":")[1]));
							dateGiven.appendChild(minute);

							// write the content into xml file
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							StreamResult result = new StreamResult(new File(quizFileName));
							transformer.transform(source, result);
							
							/*RequestDispatcher dispatcher = getServletContext()
									.getRequestDispatcher(thisServlet.replace("quizschedule", "admin.jsp"));
							dispatcher.forward(request, response);*/
						} catch (Exception e) {
							e.printStackTrace();
							IOerrMessage = "I failed and could not save your quiz.";
							/*String destinationAddress = "/admin.jsp";
							servletUtils.displayMessage(IOerrMessage, destinationAddress, request, response);*/
							
							pf.printDataForAdmin(request, response, IOerrMessage);


						}
					} else
						appendNewQuiz(request, response, quizFileName); // add quiz to existing xml file
					// TO DO: need to add request Dispatcher code here
				}// end synchronize block
				
				//might have to replace port and http to https here

				String successMessage = "Successfully saved the quiz";
				
				pf = readAllData(courseID,course);

				pf.printDataForAdmin(request, response, successMessage);

				
				/*String destinationAddress = "/admin.jsp";
				servletUtils.displayMessage(successMessage, destinationAddress, request, response);*/
			}

			catch (IOException e) {
				IOerrFlag = true;
				IOerrMessage = "I failed and could not save your quiz." + e;
			}

			// Respond to the student
			if (IOerrFlag) {
				/*String destinationAddress = "/admin.jsp";
				servletUtils.displayMessage(IOerrMessage, destinationAddress, request, response);*/
				pf.printDataForAdmin(request, response, IOerrMessage);

			} 

		} else {
			/*TO BE DELETED
			 * // quizDate == null or quizDate.length() > 0 or quizTime !=null or
			// quizTime.length()>0
			//out.println("<body bgcolor=\"#DDEEDD\">");
			//if (quizDate == null || quizTime == null)
*/				IOerrMessage ="You didn't specify the quiz date and time.";

			thisServlet = (request.getRequestURL()).toString();
			System.out.println("thisServlet");
			// CS server has a flaw--requires https & 8443, but puts http & 8080 on the
			// requestURL
			//thisServlet = thisServlet.replace("http", "https");
			//thisServlet = thisServlet.replace("8080", "8443");
			/*String destinationAddress = "/admin.jsp";
			servletUtils.displayMessage(IOerrMessage, destinationAddress, request, response);*/

			pf = readAllData(courseID,course);

			pf.printDataForAdmin(request, response, IOerrMessage);

			/*out.println(
					"<p><a href='" + thisServlet + "?courseID=" + courseID + "'>You can try again if you like.</a>");*/
		}

	}
}
