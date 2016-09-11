package servlets;

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
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("post"));
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			
			String insertSQL = "INSERT INTO comments (post_id, subject, author_name, text) values (?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1, postId);
			stmt.setString(2, request.getParameter("subject"));
			stmt.setString(3, request.getParameter("author_name"));
			stmt.setString(4, request.getParameter("text"));
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewPost.jsp?postId=" + postId);
			request.setAttribute("message", "<font color=green>Comment added!</font>");
			dispatcher.forward(request,  response);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("WEB-INF/viewPost.jsp?postId=" + postId);
			PrintWriter out= response.getWriter();
			out.print("<font color=red>Unable to add comment: ");
			out.print("Unable to post: " + ex.getMessage());
			out.println("</font>");
			rd.include(request, response);
		}
	}
	

}
