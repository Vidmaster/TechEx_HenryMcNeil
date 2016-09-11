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
 * Servlet implementation class PostServlet
 */
@WebServlet("/newPost.jsp")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("message");
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
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/newPost.jsp");
			request.setAttribute("users", users);
			dispatcher.forward(request,  response);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("message");
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			String insertSQL = "INSERT INTO posts (subject, body, user_id, posted) values (?, ?, ?, current_timestamp)";
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1, request.getParameter("subject"));
			stmt.setString(2, request.getParameter("body"));
			stmt.setString(3, request.getParameter("user"));
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			request.getSession().setAttribute("message", "<font color=green>Successfully posted!</font>");
			response.sendRedirect("index.jsp");
			dispatcher.include(request,  response);
			
		} catch (SQLException | ClassNotFoundException ex) {
			
			System.out.println("Exception occurred: " + ex.getMessage());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/newPost.jsp");
			PrintWriter out= response.getWriter();
			out.print("<font color=red>Unable to post: ");
			out.print(ex.getMessage());
			out.println("</font>");
			rd.include(request, response);
		}
		
	}

}
