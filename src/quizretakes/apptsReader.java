package quizretakes;
// JO, 6-Jan-2019
// Readsappointments
// Stores in a ArrayList and returns


import java.lang.*;
import java.io.IOException;


import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class apptsReader
{

static private final String separator = ",";

public appts read (String filename) throws IOException
{

   // read appointments file
   appts apptList = new appts();
   apptBean appt;
   File file = new File(filename);
   if (!file.exists())
   {
      throw new IOException ("No appointments to read.");
   }
   else
   {
      FileReader fw = new FileReader(file.getAbsoluteFile());
      BufferedReader bw = new BufferedReader(fw);

      String line;
      while ((line = bw.readLine()) != null)
      {
         String[] s = line.split(separator);
         int r =  Integer.parseInt(s[0].replaceAll("\\s",""));
         int q= Integer.parseInt(s[1].replaceAll("\\s",""));
         String n = s[2];
         appt = new apptBean(r, q, n);
         apptList.addAppt(appt);
      }
      bw.close();
   }

   return apptList;
} // end read

} // end class
