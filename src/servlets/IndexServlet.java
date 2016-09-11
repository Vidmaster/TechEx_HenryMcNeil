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
import domain.Post;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DatabaseUtilities.getDatabaseConnection();
			
			String selectSQL = "SELECT posts.id as id, posts.subject as subject, "
					+ "users.name as userName, users.id as userId, left(posts.body, 500) as body, "
					+ "posts.posted as posted, posts.updated as updated, "
					+ "count(comments.id) as numComments \n"
					+ "FROM posts \n"
					+ "LEFT JOIN comments ON comments.post_id = posts.id \n"
					+ "INNER JOIN users ON users.id = posts.user_id \n"
					+ "WHERE users.id = posts.user_id \n"
					+ "GROUP BY posts.id \n"
					+ "ORDER BY posts.posted desc";
			System.out.println(selectSQL);
			
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			List<Post> posts = new ArrayList<Post>();
			while (rs.next()) {
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
				Post post = new Post(id, userId, userName, subject, body, posted, updated, numComments);
				
				posts.add(post);
				System.out.println(post.toString());
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
			request.setAttribute("posts", posts);
	        dispatcher.forward(request,  response);
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
