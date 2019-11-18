/**
 * 
 */

/**
 * @author kesin
 *
 */
public class serverUtils {
	// Data files
		// location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
		// These names show up in all servlets
		private static final String dataLocation = "C:/Users/kesin/eclipse-workspace/quizretakes/src/";/// var/www/CS/webapps/offutt/WEB-INF/data/";
		static private final String separator = ",";
		private static final String courseBase = "course";
		private static final String quizzesBase = "quiz-orig";
		private static final String retakesBase = "quiz-retakes";
		private static final String apptsBase = "quiz-appts";

		// Filenames to be built from above and the courseID parameter
		private String courseFileName;
		private String quizzesFileName;
		private String retakesFileName;
		private String apptsFileName;

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
		public int getDaysAvailable() {
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
		private int daysAvailable = 14;

		// To be set by getRequestURL()
		private String thisServlet = "";
}
