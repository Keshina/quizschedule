package quizretakes;
// JO 9-Jan-2019
// Utilities for servlets ... shared methods


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Jeff Offutt
 *         Date: January, 2019
 * Used by all servlets
 *
 */

public class servletUtils
{

static void displayMessage(String message, String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 request.setAttribute("message", message);
     RequestDispatcher dispatcher =request.getRequestDispatcher(address);
     dispatcher.forward(request, response);
	
}

static void redirectURL(String url, HttpServletRequest request,HttpServletResponse response,  String message) throws IOException {

//         HttpSession session = request.getSession();
//         session.setAttribute("message", message);
        response.sendRedirect(url);


}
} // end servletUtils class
