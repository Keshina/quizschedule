

import java.util.*;

/**
 * This class holds a collection of retakes

 * @author Jeff Offutt
 */

public class retakes implements Iterable<retakeBean>
{
   private ArrayList<retakeBean> retakes;

   // ***** Constructors //
   public retakes ()
   {
      retakes = new ArrayList<retakeBean>();
   }

   public retakes (int ID, String location, int month, int day, int starthour, int startminute, int endhour, int endminute)
   {
      retakes = new ArrayList<retakeBean>();
      retakeBean qr = new retakeBean (ID, location, month, day, starthour, startminute, endhour, endminute);
      retakes.add (qr);
   }

   public retakes (retakeBean qr)
   {
      retakes = new ArrayList<retakeBean>();
      retakes.add (qr);
   }

   // ***** //
   public void sort ()
   {
      Collections.sort (retakes);
   }

   @Override
   public Iterator<retakeBean> iterator()
   {
       return retakes.iterator();
   }

   // ***** adders & getters //
   public void addRetake (retakeBean qr)
   {
      retakes.add (qr);
   }

   public String toString ()
   {
      return (Arrays.toString(retakes.toArray()));
   }

}
