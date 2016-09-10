import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtilities;
/**
 * Servlet Tutorial - Servlet Example
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		log("User="+name+"::description="+description);
		
		if (name == null || name.isEmpty()) {
			fail(request, response, "Name is required");
			return;
		}
		if (description == null || description.isEmpty()) {
			fail(request, response, "Description is required");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getDatabaseConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			fail(request, response, "Unable to get database connection. " + ex.getMessage());
			return;
		}
		
		try {
			String selectSQL = "INSERT INTO users (name, description) VALUES (?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			fail(request, response, "Unable to add user: " + ex.getMessage());
			return;
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		PrintWriter out= response.getWriter();
		out.println("<font color=green>New user created!</font>");
		rd.include(request, response);		
	}
	
	private void fail(HttpServletRequest request, HttpServletResponse response, String reason) throws IOException, ServletException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/newUser.jsp");
		PrintWriter out= response.getWriter();
		out.print("<font color=red>Unable to create new user: ");
		out.print(reason);
		out.println("</font>");
		rd.include(request, response);
	}
}