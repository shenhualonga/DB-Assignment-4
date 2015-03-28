import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentManager {
    
	DataSource ds;
	
	public CommentManager()
	{	
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/UserMovieActorDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// create a comment
	public Comment createComment(Comment newComment)
	{
		String sql = "insert into comment values (null,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getUser());
			statement.setString(2, newComment.getComment());
			statement.setDate(3, newComment.getDate());
			statement.setInt(4, newComment.getMovieid());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newComment;
	}
	// retrieve all movies
	public List<Comment> readAllUsers()
	{
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setCommentID(results.getInt("commentID"));
				comment.setUser(results.getString("user"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("movieid"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	List<Comment> readAllCommentsForUsername(String username)
	{
		List<Comment> comments = new ArrayList<Comment>();
		
		String sql = "select comment.comment, comment.date, comment.commentID, comment.movieid, comment.user"
				   + "from comment, user "
				   + "where user.username=comment.user";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setCommentID(results.getInt("commentID"));
				comment.setUser(results.getString("user"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("movieid"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	List<Comment> readAllCommentsForMovie(int movieId)
	{
		List<Comment> comments = new ArrayList<Comment>();
		
		String sql = "select comment.comment, comment.date, comment.commentID, comment.movieid, comment.user"
				   + "from comment, movie "
				   + "where movie.id=comment.movieid";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				comment.setCommentID(results.getInt("commentID"));
				comment.setUser(results.getString("user"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("movieid"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	Comment readCommentForId(int commentId)
	{
		Comment comment = new Comment();
		
		String sql = "select * from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				comment.setCommentID(results.getInt("commentID"));
				comment.setUser(results.getString("user"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("movieid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
	
	// update a comment by id
	public Comment updateComment(int commentId, String newComment)
	{		
		Comment comment = new Comment();
		String sql = "update comment set comment=? where commentID=?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setInt(2, commentId);
			statement.executeUpdate();
			
			sql = "select * from comment where commentID=?";
			statement.setInt(1, commentId);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				comment.setCommentID(results.getInt("commentID"));
				comment.setUser(results.getString("user"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("movieid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
	// delete a comment by id
	public int deleteComment(int commentId)
	{
		String sql = "delete from comment where commentID=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, commentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//
}

