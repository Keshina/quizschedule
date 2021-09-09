package quizretakes;


import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This bean holds a single quiz retake appointment

 * @author Jeff Offutt
 */

public class apptBean implements Comparable<apptBean>
{
   private int quizID;
   private int retakeID;
   private String name;

   // *** Constructor *** //
   public apptBean (int retakeID, int quizID, String name)
   {
      this.retakeID = retakeID;
      this.quizID   = quizID;
      this.name     = name;
   }

   // *** Getters *** //
   public int getQuizID()
   {
      return quizID;
   }
   public int getRetakeID()
   {
      return retakeID;
   }
   public String getName()
   {
      return name;
   }

   public String toString()
   {
      return retakeID + ":" + quizID + ":" + name;
   }

@Override
public int compareTo(apptBean o) {
	// TODO Auto-generated method stub
	return 0;
}
   
//   public apptBean apptToPrint(ArrayList<apptBean> apptsList, HashMap retakeToPrint) {
//		List<String> appListToPrint = new ArrayList<>();
//
//		HashMap retakeAppt = new HashMap();
//		HashMap <Integer,HashMap>quizAppt = new HashMap();
//
//		for (apptBean a: apptsList) {
//
//		String studentDetail ="";
//		
//		
//		Integer rId = a.getRetakeID();
//		Integer qId = a.getQuizID();
//		String retakeDetail = (String) retakeToPrint.get(rId);
//		retakeAppt.put(rId, retakeDetail);
//		
//		if(quizAppt.containsKey(rId)) {
//			HashMap quizAndStudent = (HashMap) quizAppt.get(rId);
//			if(quizAndStudent.containsKey(qId)) {
//				String studentList = (String) quizAndStudent.get(qId);
//				studentList+=", "+a.getName();
//				quizAndStudent.put(qId,studentList);
//			}
//			else {
//				quizAndStudent.put(qId,a.getName());
//			}
//		}
//			else {
//				HashMap temp =  new HashMap();
//				temp.put(qId, a.getName());
//				quizAppt.put(rId, temp);
//			}
//	}
//	
//	for (Integer retakeId : quizAppt.keySet())	{	
//		
//		HashMap<Integer,String> temp = quizAppt.get(retakeId);
//		String retakeDetail = (String) retakeAppt.get(retakeId);
//		String studentDetail="";
//		for (Integer quizId : temp.keySet())	{	
//		 studentDetail += "Quiz "+quizId+" : "+temp.get(quizId)+"<br>";
//		}
//		String toPrint = retakeDetail+"<br>"+studentDetail;
//		appListToPrint.add(toPrint);
//	}	
//   }
}
