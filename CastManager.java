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

public class CastManager {
    
	DataSource ds;
	
	public CastManager()
	{	
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/UserMovieActorDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// create a cast
	public Cast createCast(Cast newCast)
	{
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getMovieid());
			statement.setInt(3, newCast.getActorid());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newCast;
	}

	public List<Cast> readAllCast()
	{
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select * from cast";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setCastId(results.getInt("castId"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("movieid"));
				cast.setActorid(results.getInt("actorid"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	List<Cast> readAllCastForActor(int actorId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		
		String sql = "select cast.castId, cast.characterName, cast.movieid, cast.actorid"
				   + "from cast, user "
				   + "where actor.actorId=cast.actorid";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setCastId(results.getInt("castId"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("movieid"));
				cast.setActorid(results.getInt("actorid"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}

	List<Cast> readAllCastForMovie(int movieId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		
		String sql = "select cast.castId, cast.characterName, cast.movieid, cast.actorid"
				   + "from cast, movie "
				   + "where movie.id=cast.movieid";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				cast.setCastId(results.getInt("castId"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("movieid"));
				cast.setActorid(results.getInt("actorid"));
				casts.add(cast);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;
	}
	
	Cast readCastForId(int castId)
	{
		Cast cast = new Cast();
		
		String sql = "select * from cast where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				cast.setCastId(results.getInt("castId"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("movieid"));
				cast.setActorid(results.getInt("actorid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cast;
	}
	
	// update a cast by id
	public Cast updateComment(int castId, Cast newCast)
	{		
		Cast cast = new Cast();
		String sql = "update cast set cast=? where commentID=?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getCastId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cast;
	}
	// delete a cast by id
	public int deleteCastt(int castId)
	{
		String sql = "delete from cast where castId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, castId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//
}