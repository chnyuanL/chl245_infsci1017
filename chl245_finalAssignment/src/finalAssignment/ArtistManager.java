package finalAssignment;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
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
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
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
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
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
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Artist at = em.find(Artist.class, artistID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return at;
	}
	public Artist getArtist(String artistID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);

		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return a;
	}
	public JSONArray getArtistList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		Query q = em.createQuery("SELECT a.artistID FROM Artist a WHERE a.bandName LIKE ?1");
		
		if(!searchTerm.equals("")) {
			if(searchType.equalsIgnoreCase("equals")) {
				q.setParameter(1, searchTerm);
			} 
			else if(searchType.equalsIgnoreCase("begins")) {
				q.setParameter(1, searchTerm + "%");				
			} 
			else if(searchType.equalsIgnoreCase("ends")) {
				q.setParameter(1, "%" + searchTerm);					
			} 
			else if (searchType.equalsIgnoreCase("contains")){
				q.setParameter(1, "%" + searchTerm + "%");									
			} 
			else {
				q.setParameter(1, "%");
			}
		} 
		else {
			q.setParameter(1, "%");
		}
		
		String qry = "SELECT a.artistID FROM Artist a ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE a.bandName = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE a.bandName LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE a.bandName LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE a.bandName LIKE '%" + searchTerm + "%'";
			}
		}
		
		
		List<String> artistIDs = em.createQuery(qry).getResultList();
		JSONArray artistListJSON = new JSONArray();
		
		for(String artistID : artistIDs){
			Artist a = em.find(Artist.class, artistID);
			artistListJSON.put(a.toJSON());
		}
		
		em.close();
		emFactory.close();
		
		return artistListJSON;
	}

	
	
	
}
