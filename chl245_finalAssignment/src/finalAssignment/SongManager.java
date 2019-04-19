package finalAssignment;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;


/*
 
Song manager has these method:
public void createSong()
	creates a song put it into the table
public void updateSong()
	updates a song with new variables
public void deleteSong()
	deletes a song from the database using songid
public Song findSong()
	finds a song using songid
 */

public class SongManager {

	public void createSong(String title, int length, String filePath, String releaseDate, String recordDate) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Song s = new Song();
		
		s.setSongID(java.util.UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setFilePath(filePath);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		
		em.persist(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();	
	}
	
	public void updateSong(String songID, String title, int length, String filePath, String releaseDate, String recordDate) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//check to see if they are not null before we update them
		if(!title.equals("")) {
			s.setTitle(title);
		}
		
		if(length > 0) {
			s.setLength(length);
		}

		if(!filePath.equals("")) {
			s.setFilePath(filePath);
		}
		
		if(!releaseDate.equals("")) {
			s.setReleaseDate(releaseDate);
		}
		
		if(!recordDate.equals("")) {
			s.setRecordDate(recordDate);
		}
		
		em.persist(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	public void deleteSong(String songID) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);	
		
		em.remove(s);
		
		//Commit transaction.
		em.getTransaction().commit();
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
	}
	
	public Song findSong(String songID) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		//starts a transaction
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return s;	
	}
	
	public Song getSong(String songID) {
		//Create and activate persistence manager connection.
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		//Close connection to persistence manager.
		em.close();
		emFactory.close();
		
		return s;
		
	}

	public JSONArray getSongList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("finalAssignment");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		String qry = "SELECT s.songID FROM Song s ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE s.title = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE s.title LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE s.title LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE s.title LIKE '%" + searchTerm + "%'";
			}
		}
		
		List<String> songIDs = em.createQuery(qry).getResultList();
		JSONArray songListJSON = new JSONArray();
	
		for(String songID : songIDs){
			Song s = em.find(Song.class, songID);
			songListJSON.put(s.toJSON());
		}
		
		em.close();
		emFactory.close();
		
		return songListJSON;
	}
	
	
}
