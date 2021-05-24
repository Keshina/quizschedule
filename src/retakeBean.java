

import java.time.*;
import java.util.HashMap;

/**
 * This bean holds information about a quiz retake session

 * @author Jeff Offutt
 */
/* *****************************************
<retakes>
  <retake>
    <id>1</id> <!-- Should be unique and in order -->
    <location>Inn 204</location> --String, building & room
    <whenOffered>
      <month>2</month> --01..12
      <day>7</day> --1..31
      <hour>10</hour> --0..23
      <minute>00</minute> --> 0-59
    </whenOffered>
  </retake>
  <retake>
    <id>2</id>
...
</retakes>
***************************************** */

public class retakeBean implements Comparable<retakeBean>
{
   private int retakeID;
   private String location;
   private LocalDate whenOffered;
   private LocalTime timeOffered;
   private int date;

   public retakeBean (int ID, String location, int month, int day, int hour, int minute)
   {
      retakeID      = ID;
      this.location = location;
      int year      = Year.now().getValue();
      Integer date = day;
      whenOffered   = LocalDate.of (year, month, day);
      timeOffered   = LocalTime.of (hour, minute);
   }

   @Override
   public int compareTo (retakeBean quizR)
   {
      return this.retakeID - quizR.retakeID;
   }

   // *** Getters *** //
   public LocalDate getDate()
   {
      return whenOffered;
   }
   public String getLocation()
   {
      return location;
   }
   public int getID()
   {
      return retakeID;
   }
   public String toString()
   {
      return retakeID + ": " +
             location + ": " +
             whenOffered.toString() + ": " +
             whenOffered.getDayOfWeek() + ": " +
             timeOffered.toString();
   }

   // Date methods
   public Month getMonth()
   {
      return whenOffered.getMonth();
   }
   
   public LocalTime getTime() {
	   return timeOffered;
   }
   
   public int getMonthNum()
   {
      return whenOffered.getMonthValue();
   }
   public DayOfWeek getDayOfWeek()
   {
      return whenOffered.getDayOfWeek();
   }
   public String dateAsString ()
   {
      return whenOffered.toString();
   }
   
   public Integer getDateOfRetake() {
	   
	return whenOffered.getDayOfMonth();
	   
	   
   }

   // Time methods
   public String timeAsString ()
   {
      return timeOffered.toString();
   }

 public String onlyCapitalizeFirstLetter(Object i) {
	 String input = i.toString();
	   
	   String output = input.toLowerCase();
	   output= output.substring(0, 1).toUpperCase()+output.substring(1);
	   return output;
   }
   public String retakeToPrint() {
//	   HashMap<String, String> tempMap = new HashMap();
//		tempMap.put("dayOfWeek",r.getDayOfWeek().toString());
//		tempMap.put("month", r.getMonth().toString());
//		tempMap.put("date", r.getDateOfRetake().toString());
//		tempMap.put("time", r.timeAsString().toString());
//		tempMap.put("location",r.getLocation().toString());
//		retakeToPrint.put("id", tempMap);
	   
	   String result = "Retake "+ getID()+" : "+onlyCapitalizeFirstLetter(getDayOfWeek().toString())+", "+onlyCapitalizeFirstLetter(getMonth().toString())+" "+getDateOfRetake()+", at "+ timeAsString()+" in "+getLocation();
	   return result;
   }
/*
   public String getQuizId() {
      return quizId;
   }
*/

}
