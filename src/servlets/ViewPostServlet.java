package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseUtilities;
import domain.Comment;
import domain.Post;
import domain.User;

/**
 * Servlet implementation class ViewPostServlet
 */
@WebServlet("/viewPost.jsp")
public class ViewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int postId = Integer.parseInt(request.getParameter("post"));
			Post post = getPost(postId);
			List<Comment> comments = getCommentsForPost(postId);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viewPost.jsp?post=" + postId);
			request.setAttribute("post", post);
			request.setAttribute("comments", comments);
	        dispatcher.forward(request,  response);
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
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
				updated = rs.getTimestamp("updated");
			} catch (Exception ex) {
				
			}
			int numComments = rs.getInt("numComments");
			post = new Post(id, userId, userName, subject, body, posted, updated, numComments);
		}
		
		return post;
	}
	
	private List<Comment> getCommentsForPost(int postId) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseUtilities.getDatabaseConnection();
		
		String selectSQL = "SELECT id, subject, author_name, text, posted FROM comments WHERE post_id = ? ORDER BY posted";
		PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
		preparedStatement.setInt(1, postId);
		ResultSet rs = preparedStatement.executeQuery();
		
		List<Comment> comments = new ArrayList<Comment>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String authorName = rs.getString("author_name");
			String subject = rs.getString("subject");
			String text = rs.getString("text");
			Date posted = rs.getTimestamp("posted");
			
			Comment comment = new Comment(id, postId, authorName, subject, text, posted);
			comments.add(comment);
		}
		
		return comments;
	}

}
