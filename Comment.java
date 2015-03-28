import java.sql.Date;

public class Comment {
	private int commentID;
	private String user;
    private String comment;
    private Date date;
    private int movieid;
    
    
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment(int commentID, String user, String comment, Date date,
			int movieid) {
		super();
		this.commentID = commentID;
		this.user = user;
		this.comment = comment;
		this.date = date;
		this.movieid = movieid;
	}
	public Comment() {
		super();
	}
}
