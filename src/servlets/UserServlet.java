package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtilities;
import domain.User;
/**
 * Servlet Tutorial - Servlet Example
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getDatabaseConnection();
			
			String selectSQL = "SELECT id, name, description from USERS";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				User user = new User(id, name, description);
				users.add(user);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("userList", users);
			dispatcher.forward(request,  response);
		} catch (SQLException | ClassNotFoundException ex) {
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		System.out.println(request.getPathInfo());
		log("User="+name+"::description="+description);
		
		if (name == null || name.isEmpty()) {
			failPost(request, response, "Name is required");
			return;
		}
		if (description == null || description.isEmpty()) {
			failPost(request, response, "Description is required");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getDatabaseConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			failPost(request, response, "Unable to get database connection. " + ex.getMessage());
			return;
		}
		
		try {
			String insertSQL = "INSERT INTO users (name, description) VALUES (?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			failPost(request, response, "Unable to add user: " + ex.getMessage());
			return;
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
		request.setAttribute("message", "<font color=green>New user created!</font>");
		rd.include(request, response);		
	}
	
	private void failPost(HttpServletRequest request, HttpServletResponse response, String reason) throws IOException, ServletException {
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/newUser.jsp");
		PrintWriter out= response.getWriter();
		out.print("<font color=red>Unable to create new user: ");
		out.print(reason);
		out.println("</font>");
		rd.include(request, response);
	}
}