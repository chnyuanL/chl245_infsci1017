package assignment6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private HashMap<String,Song> albumSongs;
	private DbUtilities db;
	
	//methods
	//would generate a new album record with the following parameters and insert into the sql database
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length){
		db = new DbUtilities();
		this.albumID = java.util.UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumSongs = new HashMap<String, Song>();
		
		String sql = "INSERT INTO album(album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length)"
				+ "	 VALUES(?,?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID); //1 specifies the first parameter in the query  
			stmt.setString(2, this.title);
			stmt.setString(3, this.releaseDate);  
			stmt.setString(4, this.recordingCompany);
			stmt.setInt(5, this.numberOfTracks);
			stmt.setString(6, this.pmrcRating);
			stmt.setInt(7, this.length);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");  
			System.out.println(sql);
		}	
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	//would retrieve an existing record from the database by using albumID
	public Album(String albumID) {
		this.albumID = albumID;
		db = new DbUtilities();
		this.albumSongs = new HashMap<String, Song>();
		String sql = "SELECT * FROM album WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID);
			ResultSet rs = stmt.executeQuery();
			System.out.println(sql);
			if(rs.next()){
				this.albumID = rs.getString("album_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String sql1 = "SELECT fk_song_id FROM album_song WHERE fk_album_id = ?;";
		try {
			PreparedStatement stmt1 = db.getConn().prepareStatement(sql1);  
			stmt1.setString(1, this.albumID);
			ResultSet rs1 = stmt1.executeQuery();
			System.out.println(sql1);
			if(rs1.next()){
				this.albumSongs.put(rs1.getString("fk_song_id"), new Song(rs1.getString("fk_song_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//deletes an album by using its albumID
	public void deleteAlbum(String albumID) {
		db = new DbUtilities();
		this.albumID = albumID;
		
		String sql = "DELETE FROM album WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "DELETE FROM album_song WHERE fk_album_id =?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql1);  
			stmt.setString(1, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	// adds a song to album_song
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
		db = new DbUtilities();
		
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id)"
				+ "VALUES(?,?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID);
			stmt.setString(2, song.getSongID());
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//deletes a song from the album using songID
	//does not delete song from the database, just within album_song
	public void deleteSong(String songID) {
		db = new DbUtilities();
		this.albumSongs.remove(songID);
		String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID);
			stmt.setString(2, songID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//deletes a song from album_song
	//does not remove it from the database, just within album_song
	public void deleteSong(Song song) {
		db = new DbUtilities();
		this.albumSongs.remove(song.getSongID());
		String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.albumID);
			stmt.setString(2, song.getSongID());
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
	
	public String getAlbumID() {
		return albumID;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		String sql = "UPDATE album SET title = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.title);
			stmt.setString(2, this.albumID);
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
		String sql = "UPDATE album SET release_date = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.releaseDate);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
		String sql = "UPDATE album SET cover_image_path = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.coverImagePath);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
		String sql = "UPDATE album SET recording_company_name = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.recordingCompany);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
		String sql = "UPDATE album SET number_of_tracks = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, this.numberOfTracks);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
		String sql = "UPDATE album SET PMRC_rating = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.pmrcRating);
			stmt.setString(2, this.albumID);
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
		this.length= length;
		String sql = "UPDATE album SET length = ? WHERE album_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setInt(1, this.length);
			stmt.setString(2, this.albumID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}


	 
}
