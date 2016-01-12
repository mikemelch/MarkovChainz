import lyrics.Artist;
import lyrics.RapGenius;
import lyrics.Song;

public class Client {
	
	public static void main(String args[]){
		Artist testArtist = new Artist("Travis Scott");
		testArtist.addSong(new Song("http://genius.com/Kanye-west-monster-lyrics"));
		RapGenius.populateSongLyricsFromSongs(testArtist);
		testArtist.getSongs().get(0).getSongLyrics().getArtistsLyricsFromSong("Bon Iver");
	}
}
