public class Cast {
	private int castId;
    private String characterName;
    private int movieid;
    private int actorid;

	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public int getActorid() {
		return actorid;
	}

	public void setActorid(int actorid) {
		this.actorid = actorid;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Cast(int castId, String characterName, int movieid, int actorid) {
		super();
		this.castId = castId;
		this.characterName = characterName;
		this.movieid = movieid;
		this.actorid = actorid;
	}

	public Cast() {
		super();
	}
}
