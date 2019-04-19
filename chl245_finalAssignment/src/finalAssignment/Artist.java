package finalAssignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;


/*
Below I have mapped:
Each class to @Entity and @Table
Each relevant attribute to @Column 
Each irrelevant attribute to @Transient
 */

@Entity
@Table(name = "artist")

public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "artist_id")
	private String artistID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "band_name")
	private String bandName;
	
	@Column(name = "bio")
	private String bio;
	
	////private DbUtilities db;
	
	//getters and setters
	//getters returns the value
	//setters updates the value with the newly inputed value
	
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}
	
	public String getArtistID() {
		return artistID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBandName() {
		return bandName;
	}
	
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	public JSONObject toJSON(){
		JSONObject artistJSON = new JSONObject();
		try {
			artistJSON.put("artist_id", this.artistID);
			artistJSON.put("first_name", this.firstName);
			artistJSON.put("last_name", this.lastName);
			artistJSON.put("band_name", this.bandName);
			artistJSON.put("bio", this.bio);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artistJSON;
		
	}
}



	
