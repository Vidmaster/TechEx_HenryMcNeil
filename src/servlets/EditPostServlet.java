package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtilities;
import domain.Post;

/**
 * Servlet implementation class EditPostServlet
 */
@WebServlet("/editPost.jsp")
public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("message");
		try {
			int postId = Integer.parseInt(request.getParameter("post"));
			Post post = getPost(postId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editPost.jsp?post=" + postId);
			request.setAttribute("post", post);
			dispatcher.forward(request,  response);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getSession().removeAttribute("message");
		int postId = Integer.parseInt(request.getParameter("post"));
		try {
			Connection conn = DatabaseUtilities.getDatabaseConnection();
			
			String insertSQL = "UPDATE posts SET subject = ?, body = ?, updated=CURRENT_TIMESTAMP, posted=posted WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1, request.getParameter("subject"));
			stmt.setString(2, request.getParameter("body"));
			stmt.setInt(3, postId);
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viewPost.jsp?post=" + postId);
			request.getSession().setAttribute("message", "<font color=green>Successfully updated!</font>");
			response.sendRedirect("viewPost.jsp?post=" + postId);
			dispatcher.include(request,  response);
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			String message = "<font color=red>Unable to update: " + ex.getMessage() + "</font>";
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editPost.jsp?post=" + postId);
			request.getSession().setAttribute("message", message);
			response.sendRedirect("editPost.jsp?post=" + postId);
			dispatcher.include(request,  response);
		}
	}

	private Post getPost(int postId) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseUtilities.getDatabaseConnection();
		
		String selectSQL = "SELECT posts.id as id, posts.subject as subject, "
				+ "users.name as userName, users.id as userId, left(posts.body, 500) as body, "
				+ "posts.posted as posted, posts.updated as updated, "
				+ "count(comments.id) as numComments \n"
				+ "FROM posts \n"
				+ "LEFT JOIN comments ON comments.post_id = posts.id \n"
				+ "INNER JOIN users ON users.id = posts.user_id \n"
				+ "WHERE users.id = posts.user_id \n"
				+ "AND posts.id = ? \n"
				+ "GROUP BY posts.id \n"
				+ "ORDER BY posts.posted desc";
		
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, postId);
		ResultSet rs = preparedStatement.executeQuery();
		
		Post post = new Post();
		if (rs.next()) {
			int id = rs.getInt("id");
			int userId = rs.getInt("userId");
			String subject = rs.getString("subject");
			String userName = rs.getString("userName");
			String body = rs.getString("body");
			Date posted = rs.getTimestamp("posted");
			Date updated = null;
			try {
				updated = rs.getDate("updated");
			} catch (Exception ex) {
				
			}
			int numComments = rs.getInt("numComments");
			post = new Post(id, userId, userName, subject, body, posted, updated, numComments);
		}
		
		return post;
	}
	
}
