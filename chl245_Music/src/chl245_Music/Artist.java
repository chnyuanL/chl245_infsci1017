package chl245_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;

	private DbUtilities db;
	
	
    //methods:
	
	//would generate a new artist record with the following parameters and insert into the sql database
	
	public Artist(String firstName, String lastName, String bandName) {
		db = new DbUtilities();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;

		String sql = "INSERT INTO artist (firstName, lastName, bandName) VALUES ('" + firstName + "', '" + lastName + "', '"+ bandName + "');";
		System.out.println(sql);
		db.executeQuery(sql);
    }
	////would retrieve an existing record from the database by using artisstID
    public Artist(String artistID) {
		this.artistID = artistID;
		db = new DbUtilities();
		String sql = "SELECT artist_id from artist WHERE artist_id = '" + this.artistID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.artistID = rs.getString("artist_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    //deletes a selected artist by using its artistID
    public void deleteArtist(String artistID) {
		db = new DbUtilities();
		this.artistID = artistID;
		String sql = "DELETE FROM artist WHERE artist_id = '" + this.artistID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()){
				this.artistID = rs.getString("artist_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	
	//getters and setters with regards to sql
	//getters returns the value
	//setters updates the value with the newly inputed value
	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID) {
		String sql = "UPDATE artist SET artist_id = '" +artistID + "' WHERE artist_id = " + this.artistID+ ";";
		this.artistID = artistID;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		String sql = "UPDATE artist SET first_name = '" + firstName + "' WHERE first_name = " + this.firstName+ ";";
		this.firstName = firstName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		String sql = "UPDATE artist SET last_name = '" + lastName + "' WHERE last_name = " + this.lastName+ ";";
		this.lastName = lastName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		String sql = "UPDATE artist SET bio = '" + bio + "' WHERE bio = " + this.bio+ ";";
		this.bio = bio;
		System.out.println(sql);
		db.executeQuery(sql);
	}

}
