import lyrics.Artist;
import lyrics.RapGenius;
import lyrics.Song;

public class Client {
	
	public static void main(String args[]){
		Artist testArtist = RapGenius.searchArtist("Drake");
		RapGenius.populateSongsFromArtistPage(testArtist);
		RapGenius.populateSongLyricsFromSongs(testArtist);
		testArtist.populateArtistMarkov();
		System.out.println(testArtist.getMarkovModel().toString());
	}
}
