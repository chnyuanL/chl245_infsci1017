package finalAssignment;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

/*
album manager has:
public void createAlbum()
	creates an new album by putting in the correct parameter
public void updateAlbum()
	updates the album with new variables
public void deleteAlbum()
	deletes album with albumID
public Album findAlbum()
	finds an album with albumID
 */

public class AlbumManager {
	
	public void createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//begins a transaction
		em.getTransaction().begin();
		
		Album ab = new Album();
		
		ab.setTitle(title);
		ab.setAlbumID(java.util.UUID.randomUUID().toString());
		ab.setReleaseDate(releaseDate);
		ab.setRecordingCompany(recordingCompany);
		ab.setNumberOfTracks(numberOfTracks);
		ab.setPmrcRating(pmrcRating);
		ab.setLength(length);
		
		em.persist(ab);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}
	
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//begins a transaction
		em.getTransaction().begin();
		
		Album ab = em.find(Album.class, albumID);
		
		//checks to see if they are not null before we actually update them
		if(!title.equals("")) {
			ab.setTitle(title);
		}
		
		if(!releaseDate.equals("")) {
			ab.setReleaseDate(releaseDate);
		}
		
		if(!coverImagePath.equals("")) {
			ab.setCoverImagePath(coverImagePath);
		}
		
		if(!recordingCompany.equals("")) {
			ab.setRecordingCompany(recordingCompany);
		}
		
		if(numberOfTracks > 0) {
			ab.setNumberOfTracks(numberOfTracks);
		}
		
		if(!pmrcRating.equals("")) {
			ab.setPmrcRating(pmrcRating);
		}
		
		if(length > 0) {
			ab.setLength(length);
		}
		
		em.persist(ab);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	public Album getAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//begins a transaction
		em.getTransaction().begin();
		
		Album ab = em.find(Album.class, albumID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		return ab;
	}
	
	
	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//begins a transaction
		em.getTransaction().begin();
		
		Album ab = em.find(Album.class, albumID);
		em.remove(ab);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();	
	}
	public Album findAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		Album ab = em.find(Album.class, albumID);
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();	
		
		return ab;
		
	}
	public JSONArray getAlbumList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();

		
		// Note that you are querying the object grid, not the database!
		String qry = "SELECT a.albumID FROM Album a ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE a.title = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE a.title LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE a.title LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE a.title LIKE '%" + searchTerm + "%'";
			}
		}
		
		
		List<String> albumIDs = em.createQuery(qry).getResultList();
		JSONArray albumListJSON = new JSONArray();
		for(String albumID : albumIDs){
			Album a = em.find(Album.class, albumID);
			albumListJSON.put(a.toJSON());
		}
		em.close();
		emFactory.close();
		
		return albumListJSON;
	}
	
}
