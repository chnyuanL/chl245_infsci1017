package chl245_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	//private Map<String,Song> albumSongs;
	
	private DbUtilities db;
	private String songID;
	
	//methods
	//would generate a new album record with the following parameters and insert into the sql database
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		db = new DbUtilities();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;

		String sql = "INSERT INTO album (title, release_date, recording_company, number_of_tracks, PMRC_rating, length) VALUES ('" + title + "', '" + releaseDate + "', '"+ recordingCompany+ "', '" +numberOfTracks+ "' , '" +pmrcRating+ "','" +length+"');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	//would retrieve an existing record from the database by using albumID
	public Album(String albumID) {
		this.albumID = albumID;
		db = new DbUtilities();
		String sql = "SELECT album_id from album WHERE album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.albumID = rs.getString("album_id");
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
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.albumID = rs.getString("fk_album_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//having troubles with hashmap
	/*
	public static void addSong(Song song) {
	
	}
	*/
	//deletes a song from the album using songID
	//*******but in album, there is no variable named  songID, so i created songID at line 19 solely
	//for the purpose of making this function compile
	public void deleteSong(String songID) {
		db = new DbUtilities();
		this.songID = songID;
		String sql = "DELETE FROM album_song WHERE fk_song_id = " + this.songID + ";";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.songID = rs.getString("fk_song_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	/*public static void deleteSong(Song song) {
		
	}
	*/
	
	
	//getters and setters with regards to sql
	//getters returns the value
	//setters updates the value with the newly inputed value
	public String getAlbumID() {
		return albumID;
	}

	public void setAlbumID(String albumID) {
		String sql = "UPDATE album SET album_id = '" + albumID + "' WHERE album_id = " + this.albumID+ ";";
		this.albumID = albumID;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "UPDATE album SET title = '" + title + "' WHERE title = " + this.title+ ";";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE album SET release_date = '" + albumID + "' WHERE release_date = " + this.releaseDate+ ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		String sql = "UPDATE album SET cover_image_path = '" + coverImagePath + "' WHERE cover_image_path = " + this.coverImagePath+ ";";
		this.coverImagePath = coverImagePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		String sql = "UPDATE album SET recording_company = '" + recordingCompany + "' WHERE recording_company = " + this.recordingCompany+ ";";
		this.recordingCompany = recordingCompany;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "UPDATE album SET number_of_tracks  = '" + numberOfTracks + "' WHERE number_of_tracks = " + this.numberOfTracks+ ";";
		this.numberOfTracks = numberOfTracks;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		String sql = "UPDATE album SET PMRC_rating = '" + pmrcRating + "' WHERE PMRC_rating = " + this.pmrcRating+ ";";
		this.pmrcRating = pmrcRating;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "UPDATE album SET length = '" + length + "' WHERE length = " + this.length+ ";";
		this.length= length;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	/*public Map<Song> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(Map<Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
	 */
}
