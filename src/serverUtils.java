import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 
 */

/**
 * @author kesin
 *
 */
//TO DO: need to fix dataLocation
public class serverUtils {
	// Data files
		// location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
		// These names show up in all servlets
		private static final String dataLocation =getDir();
				//"C:/Users/Kesina/eclipse-workspace/quizschedule/src/";/// var/www/CS/webapps/offutt/WEB-INF/data/";
		static private final String separator = ",";
		private static final String courseBase = "course";
		private static final String quizzesBase = "quiz-orig";
		private static final String retakesBase = "quiz-retakes";
		private static final String apptsBase = "quiz-appts";
		private static final String env = "heroku";

		private static final String projectName = "quizschedule";
		// Filenames to be built from above and the courseID parameter
		private String courseFileName;
		private String quizzesFileName;
		private String retakesFileName;
		private String apptsFileName;

		
		public static String getDir()  {
			String home = System.getProperty("user.home");
			String path="";
			if(env.equals("local"))
				path=home+File.separator+"eclipse-workspace"+File.separator+projectName+File.separator+"src"+File.separator;
			else if (env.equals("heroku"))
				path=home+File.separator+"data"+File.separator;
			else if(env.equals("csServer"))
				path ="var/www/CS/webapps/offutt/WEB-INF/data/";
//			String path="";
			/*
			 * try { path = new File("quizschedule/src/").getCanonicalPath().toString(); }
			 * catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); path=""; }
			 */
		//	path = Paths.get("").toAbsolutePath().toString();
			System.out.println(path);
			return path;
		}
		/**
		 * @return the courseFileName
		 */
		public String getCourseFileName() {
			return courseFileName;
		}

		/**
		 * @param courseFileName the courseFileName to set
		 */
		public void setCourseFileName(String courseFileName) {
			this.courseFileName = courseFileName;
		}

		/**
		 * @return the quizzesFileName
		 */
		public String getQuizzesFileName() {
			return quizzesFileName;
		}

		/**
		 * @param quizzesFileName the quizzesFileName to set
		 */
		public void setQuizzesFileName(String quizzesFileName) {
			this.quizzesFileName = quizzesFileName;
		}

		/**
		 * @return the retakesFileName
		 */
		public String getRetakesFileName() {
			return retakesFileName;
		}

		/**
		 * @param retakesFileName the retakesFileName to set
		 */
		public void setRetakesFileName(String retakesFileName) {
			this.retakesFileName = retakesFileName;
		}

		/**
		 * @return the apptsFileName
		 */
		public String getApptsFileName() {
			return apptsFileName;
		}

		/**
		 * @param apptsFileName the apptsFileName to set
		 */
		public void setApptsFileName(String apptsFileName) {
			this.apptsFileName = apptsFileName;
		}

		/**
		 * @return the courseID
		 */
		public String getCourseID() {
			return courseID;
		}

		/**
		 * @param courseID the courseID to set
		 */
		public void setCourseID(String courseID) {
			this.courseID = courseID;
		}

		/**
		 * @return the daysAvailable
		 */
		public static int getDaysAvailable() {
			return daysAvailable;
		}

		/**
		 * @param daysAvailable the daysAvailable to set
		 */
		public void setDaysAvailable(int daysAvailable) {
			this.daysAvailable = daysAvailable;
		}

		/**
		 * @return the thisServlet
		 */
		public String getThisServlet() {
			return thisServlet;
		}

		/**
		 * @param thisServlet the thisServlet to set
		 */
		public void setThisServlet(String thisServlet) {
			this.thisServlet = thisServlet;
		}

		/**
		 * @return the datalocation
		 */
		public static String getDatalocation() {
			return dataLocation;
		}

		/**
		 * @return the separator
		 */
		public static String getSeparator() {
			return separator;
		}

		/**
		 * @return the coursebase
		 */
		public static String getCoursebase() {
			return courseBase;
		}

		/**
		 * @return the quizzesbase
		 */
		public static String getQuizzesbase() {
			return quizzesBase;
		}

		/**
		 * @return the retakesbase
		 */
		public static String getRetakesbase() {
			return retakesBase;
		}

		/**
		 * @return the apptsbase
		 */
		public static String getApptsbase() {
			return apptsBase;
		}

		// Passed as parameter and stored in course.xml file (format: "swe437")
		private String courseID;
		// Stored in course.xml file, default 14
		// Number of days a retake is offered after the quiz is given
		private static int daysAvailable = 14;

		// To be set by getRequestURL()
		private String thisServlet = "";
		
		
		protected printQuizScheduleForm readAllData(String courseID, courseBean course) throws IOException {

			printQuizScheduleForm pf = new printQuizScheduleForm();
			// Filenames to be built from above and the courseID
			String quizzesFileName = dataLocation + quizzesBase + "-" + courseID + ".xml";
			String retakesFileName = dataLocation + retakesBase + "-" + courseID + ".xml";
			String apptsFileName = dataLocation + apptsBase + "-" + courseID + ".txt";

			// Load the quizzes and the retake times from disk
			quizzes quizList = new quizzes();
			retakes retakesList = new retakes();
			appts apptsList = new appts();
			quizReader qr = new quizReader();
			retakesReader rr = new retakesReader();
			apptsReader aa = new apptsReader();

			try { // Read the files and print the form
				quizList = qr.read(quizzesFileName);
				retakesList = rr.read(retakesFileName);
				apptsList = aa.read(apptsFileName);

				pf = new printQuizScheduleForm(quizList, retakesList, apptsList, course, daysAvailable);
			} catch (Exception e) {
				System.err.println("Error reading quiz, retake or appt for admin view in serverUtils.java");
			}
			return pf;

		}
}
