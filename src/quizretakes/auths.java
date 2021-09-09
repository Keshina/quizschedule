package quizretakes;



import java.util.*;

/**
 * This class holds a collection of appointments

 * @author Kesina 
 */

public class auths implements Iterable<authBean>
{
   private ArrayList<authBean> auths;

   // ***** Constructors //
   public auths ()
   {
	   auths = new ArrayList<authBean>();
   }

   public auths ( String username, String pass)
   {  // Adds one appt to a new list
      auths = new ArrayList<authBean>();
      authBean ab = new authBean (username, pass);
      auths.add(ab);
   }

   public auths (authBean ab)
   {
	   auths = new ArrayList<authBean>();
	   auths.add (ab);
   }
//
//   // *** sorting and iterating *** //
//   public void sort ()
//   {
//      Collections.sort (appts);
//   }

   @Override
   public Iterator<authBean> iterator()
   {
       return auths.iterator();
   }

   // ***** setters & getters //
   public void addAuth (authBean ab)
   {
	   auths.add (ab);
   }

   public String toString ()
   {
      return (Arrays.toString(auths.toArray()));
   }
   public int getSize ()
   {
      return auths.size();
   }

}

