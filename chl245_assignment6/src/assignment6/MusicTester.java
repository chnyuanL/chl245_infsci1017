package assignment6;
import java.sql.*;
import java.net.URL;

public class MusicTester {

	public static void main(String[] args) throws SQLException {
		//testing methods
		
		
		//Testing Song  4 parameters
		//Song s = new Song("Test_Title", 3, "2018-11-11", "2017-11-11");
		
		//Testing Song with 1 parameter
		/*
		Song s1 = new Song("0304f2e8-f12b-4938-9d87-3323c51ee105");
		System.out.println(s1);
		*/
		
		//DeleteSong(songID):
		//this is the song ID I created under the title of "Test_title"
		/*
		Song s2 = new Song("d78b09fe-d355-40d6-856f-6cffb6453b5f");
		s2.deleteSong("d78b09fe-d355-40d6-856f-6cffb6453b5f");
		*/
		
		//addArtist()
		/*
		Artist a3 = new Artist("a1833370-eae2-4ecf-9140-327af796e0aa");
		Song s3 = new Song("77b8d0d4-8315-449e-bbf8-c016b9e98518");
		s3.addArtist(a3);
		*/
		
		//set title, set length, set release date, set record date, set file path
		/*
		Song s = new Song("Test_Title_1", 3, "2018-11-11", "2017-11-11"); 
		s.setTitle("New_Test_Title");
		System.out.println(s.getTitle());
		
		s.setLength(666);
		System.out.println(s.getLength());
		
		s.setReleaseDate("1111-11-11");
		System.out.println(s.getReleaseDate());
		
		s.setRecordDate("2000-01-01");
		System.out.println(s.getRecordDate());
		
		s.setFilePath("test_path");
		System.out.println(s.getFilePath());
		*/
		
		//Testing Artist with 3 parameters
		//Artist a = new Artist("TestFirstName","TestLastName","TestBandName");
		
		//testing artist with 1 parameter
		/*
		Artist a = new Artist("0208d02c-191e-4e58-9103-f474b9253581");
		System.out.println(a);
		*/
		
		//deleteArtist
		/*
		Artist a2 = new Artist("c35f15a2-d278-4c6a-a175-3ff2ccb0a13e");
		a2.deleteArtist("c35f15a2-d278-4c6a-a175-3ff2ccb0a13e");
		*/
		
		//set first name, set last name, set band name, set bio
		/*
		Artist a = new Artist("TestFirstName","TestLastName","TestBandName");
		a.setFirstName("NewTestFirstName");
		a.setLastName("NewSetLastName");
		a.setBandName("NewTestBandName");
		a.setBio("NewSetBio");
		*/
		
		//testing album with 6 parameters
		//Album ab = new Album("TestAlbum","1111-11-11","TestRecordingCompany", 3,"Bad",55);
		
		//testing album with 1 parameter using the albumID from "TestAlbum"️
		//Album ab1 = new Album("3882ecee-1b65-4af8-b5a3-0a7fa35cd346");
		
		//delete album
		/*
		Album ab1 = new Album("3882ecee-1b65-4af8-b5a3-0a7fa35cd346");
		ab1.deleteAlbum(ab1.getAlbumID());
		*/
		
		//addSong
		/*
		Song ab_s = new Song("TestForAlbum", 11, "2018-11-11", "2017-11-11");
		Album ab = new Album("TestAlbum","1111-11-11","TestRecordingCompany", 3,"Bad",55);
		ab.addSong(ab_s);
		*/
		
		//deleteSong 
		/*
		Album ab = new Album("Test","1111-11-11","TestRC", 2,"Ew",3);
		Album ab1 = new Album("b2b20098-4871-4cca-9d30-1b61fb2a1f4b");
		ab.deleteSong("b2b20098-4871-4cca-9d30-1b61fb2a1f4b");
		*/
		
		//set title, set release date, set image path, set recording company
		// set pmrc rating, set number of tracks, set length
		/*
		Album ab = new Album("Test","1111-11-11","TestRC", 2,"Ew",3);
		
		ab.setTitle("TestSetTitle");
		System.out.println(ab.getTitle());
		
		ab.setReleaseDate("0000-00-00");
		System.out.println(ab.getReleaseDate());
		
		ab.setCoverImagePath("TestPath");
		System.out.println(ab.getCoverImagePath());
		
		ab.setRecordingCompany("TestSETRecordingCompany");
		System.out.println(ab.getRecordingCompany());
		
		ab.setPmrcRating("TestRating");
		System.out.println(ab.getPmrcRating());
		
		ab.setNumberOfTracks(111);
		System.out.println(ab.getNumberOfTracks());
		
		ab.setLength(999);
		System.out.println(ab.getLength());
		*/
		
		
	}
}
