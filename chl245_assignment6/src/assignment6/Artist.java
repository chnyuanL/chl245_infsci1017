package assignment6;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;


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
		this.artistID = java.util.UUID.randomUUID().toString();
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES (?,?,?,?);";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.artistID); //1 specifies the first parameter in the query  
			stmt.setString(2, this.firstName);
			stmt.setString(3, this.lastName);  
			stmt.setString(4, this.bandName);
			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");  
			System.out.println(sql);
		}	
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
	////would retrieve an existing record from the database by using artisstID
    public Artist(String artistID) {
		this.artistID = artistID;
		db = new DbUtilities();
		String sql = "SELECT * from artist WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);  
			stmt.setString(1, this.artistID); //1 specifies the first parameter in the query  
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs + " records selected"); 
			System.out.println(sql);
			if(rs.next()) {
				this.artistID = rs.getString("artist_id");
			}
		}	
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	
    }
    //deletes a selected artist by using its artistID
    public void deleteArtist(String artistID) {
		db = new DbUtilities();
		this.artistID = artistID;
		//string sql is used to delete artist from the artist table
		String sql = "DELETE FROM artist WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//string sql1 is used to delete artist from the song_artist table using artistID
		String sql1 = "DELETE FROM song_artist WHERE fk_artist_id = ?;";
		try {
			PreparedStatement stmt1 = db.getConn().prepareStatement(sql1);
			stmt1.setString(1, this.artistID);
			int i = stmt1.executeUpdate();
			System.out.println(i + " records deleted");
			System.out.println(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
	
	//getters and setters with regards to sql
	//getters returns the value
	//setters updates the value with the newly inputed value
	public String getArtistID() {
		return this.artistID;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		String sql = "UPDATE artist SET first_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.firstName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		String sql = "UPDATE artist SET last_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.lastName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getBandName() {
		return this.bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
		String sql = "UPDATE artist SET band_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.bandName);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		try {
			PreparedStatement stmt = db.getConn().prepareStatement(sql);
			stmt.setString(1, this.bio);
			stmt.setString(2, this.artistID);
			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
