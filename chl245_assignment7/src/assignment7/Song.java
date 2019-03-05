package assignment7;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.persistence.*;

/*
Below I have mapped:
Each class to @Entity and @Table
Each relevant attribute to @Column 
Each irrelevant attribute to @Transient
 */

@Entity
@Table (name="song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "song_id")
	private String songID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "length")
	private int length;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name = "record_date")
	private String recordDate;
	
	@Transient
	private Map<String, Artist> songArtist;
	
	//private DbUtilities db;
	
	// adds an artist
	public void addArtist(Artist artist) {
		this.songArtist.put(artist.getArtistID(), artist);
	}
	// deletes an artist with its ID
	public void deleteArtist(String artistID) {
		this.songArtist.remove(artistID);
	}
	// deletes an artist
	public void deleteArtist(Artist artist) {
		this.songArtist.remove(artist.getArtistID());
	}
	
	//getters and setters 
	//getters returns the value
	//setters updates the value with the newly inputed value
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Map<String, Artist> getSongArtist() {
		return songArtist;
	}
	
	/*
	public DbUtilities getDb() {
		return db;
	}
	public void setDb(DbUtilities db) {
		this.db = db;
	}
	*/
	
	//forgot to write this getter and this setter
	public String getSongID() {
		return songID;
	}
	public void setSongID(String songID) {
		this.songID = songID;
	}
		
}

