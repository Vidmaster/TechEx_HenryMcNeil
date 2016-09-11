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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtilities;
import domain.User;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtilities.checkErrorMessage(request);
		
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			
			String selectSQL = "SELECT id, name, description from users";
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
			System.out.println(ex.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtilities.checkErrorMessage(request);
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		log("User="+name+"::description="+description);
		
		if (name == null || name.isEmpty()) {
			ServletUtilities.failPost(request, response, getServletContext(), "newUser.jsp", "Name is required");
			return;
		}
		if (description == null || description.isEmpty()) {
			ServletUtilities.failPost(request, response, getServletContext(), "newUser.jsp", "Description is required");
			return;
		}
		
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			
			String insertSQL = "INSERT INTO users (name, description) VALUES (?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ServletUtilities.failPost(request, response, getServletContext(), "addUser.jsp", "Unable to add user: " + ex.getMessage());
			return;
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");
		request.getSession().setAttribute("message", "<font color=green>New user created!</font>");
		response.sendRedirect("index.jsp");
		rd.include(request, response);		
	}

}