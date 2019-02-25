package assignment6;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;

public class Song {
	private String songID;
	private String title;
	private int length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtist;
	private DbUtilities db;
	
	//methods
	//would generate a new song record with the following parameters and insert into the sql database
	public Song(String title, int length, String releaseDate, String recordDate){
		db = new DbUtilities();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = java.util.UUID.randomUUID().toString();	
		this.songArtist = new HashMap<String, Artist>();
		/*
		String sql = "INSERT INTO song (title, length, release_date, song_id) VALUES ('" + title + "', '" + length + "', '"+ releaseDate + "', '"+songID+"');";
		System.out.println(sql);
		db.executeQuery(sql);
		*/
		//Connection con = null;
		
		String sql = "INSERT INTO song (title, length, release_date, record_date, song_id) "
				+ "VALUES (?,?,?,?,?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.title); //1 specifies the first parameter in the query  
			stmt.setInt(2, this.length);  
			stmt.setString(3, this.releaseDate);
			stmt.setString(4, this.recordDate);
			stmt.setString(5, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");  
			System.out.println(sql);
		}	
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	//would retrieve an existing record from the database by using songID
	public Song(String songID){
		this.songID = songID;
		this.songArtist = new HashMap<String, Artist>();
		db = new DbUtilities();
		String sql = "SELECT * FROM song WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.songID);
			ResultSet rs = stmt.executeQuery();
			System.out.println(sql);
			if(rs.next()){
				this.songID = rs.getString("song_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "SELECT fk_artist_id FROM song_artist WHERE fk_song_id = ?;";
		try {
			PreparedStatement stmt1 = db.getConn().prepareStatement(sql1);  
			stmt1.setString(1, this.songID);
			ResultSet rs1 = stmt1.executeQuery();
			System.out.println(sql1);
			if(rs1.next()){
				this.songArtist.put(rs1.getString("fk_artist_id"), new Artist(rs1.getString("fk_artist_id")));
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
		//string sql is used to delete song from the song table
		String sql = "DELETE FROM song WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//string sq1 is used to delete song from playlist_song table
		String sql1 = "DELETE FROM playlist_song WHERE fk_song_id = ?;";
		try {
			PreparedStatement stmt1 = db.getConn().prepareStatement(sql1);
			stmt1.setString(1, this.songID);
			int i = stmt1.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//string sql2 is used to delete fk_song_id from song_artist
		String sql2 = "DELETE FROM song_artist WHERE fk_song_id = ?;";
		try {
			PreparedStatement stmt2 = db.getConn().prepareStatement(sql2);
			stmt2.setString(1, this.songID);
			int i = stmt2.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//string sql3 is used to delete song_id from song_genre
		String sql3 = "DELETE FROM song_genre WHERE fk_song_id = ?;";
		try {
			PreparedStatement stmt3 = db.getConn().prepareStatement(sql3);
			stmt3.setString(1, this.songID);
			int i = stmt3.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//string sql4 is used to delete song_id from album_song
		String sql4 = "DELETE FROM album_song WHERE song_id = ?;";
		try {
			PreparedStatement stmt4 = db.getConn().prepareStatement(sql4);
			stmt4.setString(1, this.songID);
			int i = stmt4.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//adds an artist to the song_artist table
	public void addArtist(Artist artist) {
		db = new DbUtilities();
		/*
		System.out.println(artist.getArtistID());
		if(this.songArtist == null) {
			System.out.println("123");
		}
		*/
		this.songArtist.put(artist.getArtistID(), artist);
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES (?, ?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.songID);
			stmt.setString(2, artist.getArtistID());
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//deletes an artist from the song_artist table using artistID
	//does not remove artist from the database, just within the song_artist table
	public void deleteArtist(String artistID) {
		this.songArtist.remove(artistID);
		db = new DbUtilities();
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//deletes an artist from the song_artist table
	//does not remove artist from the database, just within the song_artist table
	public void deleteArtist(Artist artist) {
		this.songArtist.remove(artist.getArtistID());
		db = new DbUtilities();
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, artist.getArtistID());
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//getters and setters with regards to sql
	//getters returns the value
	//setters updates the value with the newly inputed value
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		String sql = "UPDATE song SET title = ? WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.title);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
		String sql = "UPDATE song SET length = ? WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, this.length);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
		String sql = "UPDATE song SET release_date = ? WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.releaseDate);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
		String sql = "UPDATE song SET record_date = ? WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.recordDate);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.filePath);
			stmt.setString(2, this.songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Artist> getSongArtist() {
		return songArtist;
	}
	public DbUtilities getDb() {
		return db;
	}
	public void setDb(DbUtilities db) {
		this.db = db;
	}
	//forgot to write this getter
	public String getSongID() {
		return songID;
	}
		
}

