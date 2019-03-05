package assignment7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.persistence.*;

/*
Below I have mapped:
Each class to @Entity and @Table
Each relevant attribute to @Column 
Each irrelevant attribute to @Transient
 */

@Entity
@Table(name = "album")

public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "album_id")
	private String albumID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name = "cover_image_path")
	private String coverImagePath;
	
	@Column(name = "recording_company_name")
	private String recordingCompany;
	
	@Column(name = "number_of_tracks")
	private int numberOfTracks;
	
	@Column(name = "PMRC_rating")
	private String pmrcRating;
	
	@Column(name = "length")
	private int length;
	
	@Transient
	private HashMap<String,Song> albumSongs;

	//private DbUtilities db;
	
	//methods
	//generates an uuid for albumID
	public Album(){
		this.albumID = java.util.UUID.randomUUID().toString();
		this.albumSongs = new HashMap<String, Song>();
	}
	//adds a song
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
	}
	//deletes a song using songID
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}
	//deletes a song
	public void deleteSong(Song song) {
		this.albumSongs.remove(song.getSongID());
	}

	//getters and setters
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
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}
	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length= length;
	}
	
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}

	 
}
