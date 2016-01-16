import lyrics.Artist;
import lyrics.RapGenius;
import lyrics.Song;

public class Client {
	
	public static void commandLine(){
		
	}
	
	public static void main(String args[]){
		Artist testArtist = RapGenius.searchArtist("drake");
		
		RapGenius.populateSongsFromArtistPage(testArtist);
		RapGenius.populateSongLyricsFromSongs(testArtist);
		testArtist.populateArtistMarkov();
		System.out.println(testArtist.getMarkovModel().generate(200));
	}
}
