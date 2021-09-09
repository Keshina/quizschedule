package quizretakes;
// JO, 3-Jan-2019
// Checking XML and reading with DOM parser
// No xsd or validation as yet



import java.lang.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

// XML parsers are so needy
// package dom; // in the documentation I found
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// These classes read the sample XML file and manage output:
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class retakesReader
{

public retakes read (String filename)
       throws IOException, ParserConfigurationException, SAXException
{
   retakes retakeList = new retakes();
   retakeBean retake;


   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   Document document = builder.parse (new File (filename));

   // Get all the nodes
   NodeList nodeList = document.getDocumentElement().getChildNodes();
   for (int i = 0; i < nodeList.getLength(); i++)
   {
      // XML structure is simple--a bunch of quizzes
      // Not validating the data values
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE)
      {
         Element elem = (Element) node;

         // retake IDs should be unique
         Integer ID = Integer.parseInt(elem.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
         // Location is a string (building and room probably)
         String location = elem.getElementsByTagName("location").item(0).getChildNodes().item(0).getNodeValue();
         // month is an integer 1..12
         Integer month = Integer.parseInt(elem.getElementsByTagName("month").item(0).getChildNodes().item(0).getNodeValue());
         // day is integer 1..31
         Integer day = Integer.parseInt(elem.getElementsByTagName("day").item(0).getChildNodes().item(0).getNodeValue());
         // hour is integer 0..23
         Integer startHour = Integer.parseInt(elem.getElementsByTagName("startHour").item(0).getChildNodes().item(0).getNodeValue());
         Integer endHour = Integer.parseInt(elem.getElementsByTagName("endHour").item(0).getChildNodes().item(0).getNodeValue());

         // minute is integer 0..59
         Integer startMinute = Integer.parseInt(elem.getElementsByTagName("startMinute").item(0).getChildNodes().item(0).getNodeValue());
         Integer endMinute = Integer.parseInt(elem.getElementsByTagName("endMinute").item(0).getChildNodes().item(0).getNodeValue());

         // Put one XML record into a bean and add it to the list
         retake = new retakeBean (ID, location, month, day, startHour, startMinute, endHour, endMinute);
         retakeList.addRetake (retake);

      } // end if
   } // end for

   // XML file may not be sorted
   retakeList.sort();

   return (retakeList);

} // end read


} // end class
