

import java.util.*;

/**
 * This class holds a collection of appointments

 * @author Kesina 
 */

public class appts implements Iterable<apptBean>
{
   private ArrayList<apptBean> appts;

   // ***** Constructors //
   public appts ()
   {
	   appts = new ArrayList<apptBean>();
   }

   public appts (int quizID, int retakeID, String name)
   {  // Adds one appt to a new list
      appts = new ArrayList<apptBean>();
      apptBean ab = new apptBean (quizID, retakeID, name);
      appts.add (ab);
   }

   public appts (apptBean ab)
   {
	   appts = new ArrayList<apptBean>();
	   appts.add (ab);
   }
//
//   // *** sorting and iterating *** //
//   public void sort ()
//   {
//      Collections.sort (appts);
//   }

   @Override
   public Iterator<apptBean> iterator()
   {
       return appts.iterator();
   }

   // ***** setters & getters //
   public void addAppt (apptBean ab)
   {
	   appts.add (ab);
   }

   public String toString ()
   {
      return (Arrays.toString(appts.toArray()));
   }

}
