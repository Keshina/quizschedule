package quizretakes;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * This bean holds information about a quiz

 * @author Jeff Offutt
 */
/* *****************************************
<quizzes>
  <quiz>
    <id>1</id> --integer > 0
    <dateGiven>
      <month>1</month> --01..12
      <day>10</day> --1..31
      <hour>15</hour> --0..23
      <minute>30</minute> --> 0-59
    </dateGiven>
  </quiz>
  <quiz>
    <id>2</id>
...
</quizzes>
***************************************** */

public class quizBean implements Comparable<quizBean>
{
   private int ID;
   private LocalDate dateGiven;
   private LocalTime timeGiven;
   private Integer date;
   private String monthSet,dayOfWeek, time;
   private Integer yearSet,hourSet,minuteSet;

   // *** Constructor *** //
   public quizBean (int quizID, int month, int day, int hour, int minute)
   {
      ID = quizID;
      int year = Year.now().getValue();
      dateGiven = LocalDate.of (year, month, day);
      timeGiven = LocalTime.of (hour, minute);
      
      monthSet = dateGiven.getMonth().toString();
      hourSet = hour;
      minuteSet =  minute;
      dayOfWeek = dateGiven.getDayOfWeek().toString();
      time = timeAsString().toString();
      date = dateGiven.getDayOfMonth();
    		  
   }

   @Override
   public int compareTo (quizBean quizB)
   {
      return this.ID - quizB.ID;
   }

   // *** Getters *** //
   public LocalDate getDate()
   {
      return dateGiven;
   }
   public int getID()
   {
      return ID;
   }
   public String toString()
   {
      return ID + ": " +
             dateGiven.toString() + ": " +
             dateGiven.getDayOfWeek() + ": " +
             timeGiven.toString();
   }

   // Date methods
   public Month getMonth()
   {
      return dateGiven.getMonth();
   }
   public int getMonthNum()
   {
      return dateGiven.getMonthValue();
   }
   public DayOfWeek getDayOfWeek()
   {
      return dateGiven.getDayOfWeek();
   }
   
   public LocalTime getTime() {
	   return timeGiven;
   }
   
   public String dateAsString ()
   {
      return dateGiven.toString();
   }
   
   
   public Integer getDateOfQuiz() {
	   
	return dateGiven.getDayOfMonth();
	   
   }
   
   public String onlyCapitalizeFirstLetter(String input) {
	   
	   String output = input.toLowerCase();
	   output= output.substring(0, 1).toUpperCase()+output.substring(1);
	   return output;
   }

   // Time methods
   public String timeAsString ()
   {
      return timeGiven.toString();
   }
   
   public String timeWithAMPM() {
	   String pattern = "hh:mm a";
       DateFormat dateFormat = new SimpleDateFormat(pattern);
        
       //1. LocalDate
       LocalTime time = getTime();
       return time.format(DateTimeFormatter.ofPattern(pattern));
//       System.out.println(now.format(DateTimeFormatter.ofPattern(pattern)));
   }
   
   public String quizToPrint() {
//	   HashMap<String, String> tempMap = new HashMap();
//		tempMap.put("dayOfWeek",r.getDayOfWeek().toString());
//		tempMap.put("month", r.getMonth().toString());
//		tempMap.put("date", r.getDateOfRetake().toString());
//		tempMap.put("time", r.timeAsString().toString());
//		tempMap.put("location",r.getLocation().toString());
//		retakeToPrint.put("id", tempMap);
	   
	   String result = "Quiz "+ getID()+" : " +onlyCapitalizeFirstLetter(getDayOfWeek().toString())+", "+onlyCapitalizeFirstLetter(getMonth().toString())+" "+getDateOfQuiz()+", at "
	   + timeAsString();
//			   +timeWithAMPM();
	   return result;
   }

/*
   public String getQuizId() {
      return quizId;
   }
*/

}
