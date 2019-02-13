package chl245_Music;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Artist artist;
	private Map<String, Artist> songArtist;
	private DbUtilities db;
	
	//methods
	
	//would generate a new song record with the following parameters and insert into the sql database
	public Song(String title, int length, String releaseDate) {
		db = new DbUtilities();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.songID = java.util.UUID.randomUUID().toString();	
		
		String sql = "INSERT INTO song (title, length, release_date, song_id) VALUES ('" + title + "', '" + length + "', '"+ releaseDate + "', '"+songID+"');";
		System.out.println(sql);
		db.executeQuery(sql);
		
	}
	//would retrieve an existing record from the database by using songID
	public Song(String songID){
		this.songID = songID;
		db = new DbUtilities();
		String sql = "SELECT song_id from song WHERE song_id = '" + this.songID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.songID = rs.getString("song_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//deletes a song from the database by its songID
	public void deleteSong(String songID) {
		db = new DbUtilities();
		this.songID = songID;
		String sql = "DELETE FROM song WHERE song_id = '" + this.songID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.songID = rs.getString("song_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	/*public void addArtist(Artist artist) {
		db = new DbUtilities();
		this.artist = artist;
		String sql = ""
		
	}
	public void deleteArtist(String artistID) {

	}
	
	public void deleteArtist(Artist artist) {
		
	}
	
	*/
	
	//getters and setters with regards to sql
	//getters returns the value
	//setters updates the value with the newly inputed value
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		String sql = "UPDATE song SET title = '" + title + "' WHERE title = " + this.title + ";";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		String sql = "UPDATE song SET length = '" + length + "' WHERE length = " + this.length + ";";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE song SET release_date = '" +releaseDate + "' WHERE release_date = " + this.releaseDate + ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		String sql = "UPDATE song SET record_date = '" +recordDate + "' WHERE record_date = " + this.recordDate + ";";
		this.recordDate = recordDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}
	public Map<String, Artist> getSongArtist() {
		return songArtist;
	}
		
}

