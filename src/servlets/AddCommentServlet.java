package servlets;

import java.io.IOException;
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
		ServletUtilities.checkErrorMessage(request);
		
		int postId = Integer.parseInt(request.getParameter("post"));
		String subject = request.getParameter("subject");
		String author = request.getParameter("author_name");
		String text = request.getParameter("text");
		
		if (subject == null || subject.isEmpty()) {
			ServletUtilities.failPost(request, response, getServletContext(), "viewPost.jsp?post=" + postId, "Subject is required");
			return;
		}
		if (author == null || author.isEmpty()) {
			ServletUtilities.failPost(request, response, getServletContext(), "viewPost.jsp?post=" + postId, "Author is required");
			return;
		}
		if (text == null || text.isEmpty()) {
			ServletUtilities.failPost(request, response, getServletContext(), "viewPost.jsp?post=" + postId, "Text is required");
			return;
		}
		
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			
			String insertSQL = "INSERT INTO comments (post_id, subject, author_name, text, posted) values (?, ?, ?, ?, current_timestamp)";
			
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1, postId);
			
			stmt.setString(2, subject);
			stmt.setString(3, author);
			stmt.setString(4, text);
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viewPost.jsp?post=" + postId);
			request.getSession().setAttribute("message", "<font color=green>Comment added!</font>");
			request.getSession().setAttribute("clearMessage", false);
			response.sendRedirect("viewPost.jsp?post=" + postId);
			dispatcher.include(request,  response);
			
		} catch (SQLException | ClassNotFoundException ex) {
			String message = "<font color=red>Unable to add comment: " + ex.getMessage() + "</font>";
			ServletUtilities.failPost(request, response, getServletContext(), "viewPost.jsp?post=" + postId, message);
		}
	}
	

}
