package assignment7;

import javax.persistence.*;

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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
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
	
	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		Album ab = em.find(Album.class, albumID);
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();	
		
		return ab;
		
	}
}
