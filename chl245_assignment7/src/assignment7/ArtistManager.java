package assignment7;

import javax.persistence.*;

/*
Artist manager has :
public void createArtist()
	creates an artist given the parameters
public void updateArtist()
	updates an artist with new variable
public void deleteArtist()
	deletes an artist with artistID
Public Artist findArtist()
	finds an artist with artistID

 */

public class ArtistManager {
	
	public void createArtist(String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Artist at = new Artist();
		
		at.setArtistID(java.util.UUID.randomUUID().toString());
		at.setFirstName(firstName);
		at.setLastName(lastName);
		at.setBandName(bandName);
		at.setBio(bio);
		
		em.persist(at);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Artist at = em.find(Artist.class, artistID);
		
		//checks if they are not null before we update them
		if(!firstName.equals("")) {
			at.setFirstName(firstName);
		}

		if(!lastName.equals("")) {
			at.setLastName(lastName);
		}
		
		if(!bandName.equals("")) {
			at.setBandName(bandName);
		}
		
		if(!bio.equals("")) {
			at.setBio(bio);
		}
		
		em.persist(at);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}
	public void deleteArtist(String artistID) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Artist at = em.find(Artist.class, artistID);
		
		em.remove(at);
		
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
	}
	public Artist findArtist(String artistID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("assignment7");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Artist at = em.find(Artist.class, artistID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return at;
	}
	
	
	
}
