import lyrics.Artist;
import lyrics.RapGenius;
import lyrics.Song;

public class Client {
	
	public static void main(String args[]){
		Artist testArtist = new Artist("Kanye West");
		testArtist.addSong(new Song("http://genius.com/Kanye-west-facts-lyrics"));
		RapGenius.populateSongLyricsFromSongs(testArtist);
		System.out.println(testArtist.getSongs().get(0).getSongLyrics().getArtistsLyricsFromSong("Kanye West"));
	}
}
