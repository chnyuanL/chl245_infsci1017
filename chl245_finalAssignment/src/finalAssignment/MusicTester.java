package finalAssignment;
import java.sql.*;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class MusicTester {

	public static void main(String[] args) throws SQLException {
		
		//testing album and album manager:
		
		//AlbumManager am = new AlbumManager();
		
		//am.createAlbum("Test Title", "2011-11-11", "test path", "test company", 11, "Trash", 22);
	
		//am.updateAlbum("6528936d-7286-4bde-a4c8-71d4c87cb936", "NewTestTitle", "2012-12-12", "updated test path", "updatedCompany", 12, "NewTrash", 23);
		
		//am.deleteAlbum("6528936d-7286-4bde-a4c8-71d4c87cb936");
		
		//testing artist and artist manager:
		
		//ArtistManager atm = new ArtistManager();
		
		//atm.createArtist("TestFirst", "TestLast", "TestBand", "TestBio");
		//atm.updateArtist("64ccd606-c94c-4e7d-8058-b61ee35cb810", "NewTestFirst", "NewTestLast", "NewTestBand", "NewTestBio");
		//atm.deleteArtist("64ccd606-c94c-4e7d-8058-b61ee35cb810");
		
		//testing song and song manager:
		
		//SongManager sm = new SongManager();
		
		//sm.createSong("TestTitle", 666, "TestFilePath", "2011-11-11", "2012-12-12");
		//sm.updateSong("5663d71e-1138-428d-9726-529782173b4b", "NewTestTitle", 777, "NewFilePth", "2012-12-12", "2011-11-11");
		//sm.deleteSong("5663d71e-1138-428d-9726-529782173b4b");
		
		/*
		AlbumManager am = new AlbumManager();
		Album a = am.getAlbum("03ee2905-60ca-4ff4-a91e-73e6f3281428");
		System.out.print(a.getTitle());
				

		JSONObject albumJSON = new JSONObject();
		try {
			albumJSON.append("album_id","03ee2905-60ca-4ff4-a91e-73e6f3281428");
			albumJSON.append("Title", "The Way Up");
			albumJSON.append("release_date", "2014-10-08 00:00:00");
			albumJSON.append("cover_image_path", "images/revolver.jpg");
			albumJSON.append("recording_company_name", "Columbia");
			albumJSON.append("number_of_tracks", "14");
			albumJSON.append("PMRC_rating","PAL");
			albumJSON.append("length", "102.4");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		AlbumManager am = new AlbumManager();
		Album a = am.getAlbum("03ee2905-60ca-4ff4-a91e-73e6f3281428");
		System.out.println(a.toJSON().toString());
		
		
	}
}
