package quizretakes;





import java.lang.*;
import java.io.IOException;


import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class authenticationReader {


static private final String separator = ",";

public auths read (String filename) throws IOException
{

   ArrayList<authBean> appts = new ArrayList<authBean>();
   auths authList = new auths();
   authBean auth;
   File file = new File(filename);
   if (!file.exists())
   {
      throw new IOException ("No auth file to read.");
   }
   else
   {
      FileReader fw = new FileReader(file.getAbsoluteFile());
      BufferedReader bw = new BufferedReader(fw);

      String line;
      while ((line = bw.readLine()) != null)
      {
    	  if (!line.isEmpty()) {
         String[] s = line.split(separator);
         System.out.println("sss"+line+"---");
         String u =  s[0].replaceAll("\\s","");
         String p= s[1].replaceAll("\\s","");
         auth = new authBean(u, p);

         authList.addAuth(auth);
      }}
      bw.close();
   }

//   System.out.println(authList);
   return authList;
} // end read

} // end class


