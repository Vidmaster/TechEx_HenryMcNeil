package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Vidmaster
 * A class to house common utility methods used by all servlets
 */
public class ServletUtilities {
	
	/**
	 * Checks if the session attribute "message" needs to be cleared, and if it does, clears it out
	 * If it has not been viewed, this sets the "clearMessage" attribute so it is cleared the next time
	 * @param request The HTTP request object
	 */
	public static void checkErrorMessage(HttpServletRequest request) {
		Object clear = request.getAttribute("clearMessage");
		boolean clearMsg = true;
		if (clear != null) {
			clearMsg = (boolean)clear;
		}
		if (clearMsg) {
			request.getSession().removeAttribute("message");
		} else {
			request.getSession().setAttribute("clearMessage", true);
		}
	}
	
	public static void failPost(HttpServletRequest request, HttpServletResponse response, ServletContext context, String dispatchUrl, String reason) throws IOException, ServletException {
		RequestDispatcher rd = context.getRequestDispatcher("/" + dispatchUrl);
		String message = "<font color=red>" + reason + "</font>";
		request.getSession().setAttribute("message", message);
		request.getSession().setAttribute("clearMessage", false);
		response.sendRedirect(dispatchUrl);
		rd.include(request, response);
	}
	
}
