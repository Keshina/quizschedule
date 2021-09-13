package quizretakes;


import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This bean holds a single quiz retake appointment

 * @author Jeff Offutt
 */

public class apptBean
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
   

}
