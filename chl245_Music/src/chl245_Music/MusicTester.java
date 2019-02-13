package chl245_Music;
import java.sql.*;
import java.net.URL;

public class MusicTester {

	public static void main(String[] args) {
		//testing methods
		
		Song s= new Song("0304f2e8-f12b-4938-9d87-3323c51ee105");
		//s.setTitle("Stairway edited");
		//System.out.println(s.getTitle());
		//s.deleteSong("0304f2e8-f12b-4938-9d87-3323c51ee105");
		//System.out.println(s.getTitle());
		
		
		Artist ar = new Artist("0208d02c-191e-4e58-9103-f474b9253581");
		//ar.setArtistID("1");
		//System.out.println(ar.getArtistID());
		//ar.deleteArtist("0304f2e8-f12b-4938-9d87-3323c51ee105");
		//System.out.println(ar.getArtistID));
		
		Album am = new Album("00b066c1-b308-4902-8416-df37fa295261");
		//am.setAlbumID("2");
		//System.out.println(am.getAlbumID());
		//am.deleteAlbum("00b066c1-b308-4902-8416-df37fa295261");
		//System.out.println(am.getAlbumID());
		
		
		
	}
}
