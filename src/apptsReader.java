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
//   ArrayList<apptBean> appts = new ArrayList<apptBean>();
   appts apptList = new appts();
   apptBean appt;
//   apptBean a;
   File file = new File(filename);
   if (!file.exists())
   {
//      throw new IOException ("No appointments to read.");
	   System.err.println("No appointments to read");
	   return apptList;
   }
   else
   {
      FileReader fw = new FileReader(file.getAbsoluteFile());
      BufferedReader bw = new BufferedReader(fw);

      String line;
      System.out.println(bw.readLine()+"---A");
      while ((line = bw.readLine()) != null)
      {
          System.out.println("lineeee"+line+filename);

         String[] s = line.split(separator);
         int r =  Integer.parseInt(s[0].replaceAll("\\s",""));
         int q= Integer.parseInt(s[1].replaceAll("\\s",""));
         String n = s[2];
         appt = new apptBean(r, q, n);
//         appt = new apptBean (Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2].toString());
//         a = new apptBean (Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2]);   appts.add(a);
         apptList.addAppt(appt);
      }
      bw.close();
   }

//   return (appts);
//   System.out.println(apptList);
   return apptList;
} // end read

} // end class
