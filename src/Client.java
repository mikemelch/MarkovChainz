import lyrics.Artist;
import lyrics.RapGenius;
import lyrics.Song;

public class Client {
	
	public static void main(String args[]){
		Artist testArtist = RapGenius.searchArtist("Kanye West");
		System.out.println(RapGenius.populateSongsFromArtistPage(testArtist));
		//testArtist.addSong(new Song("http://genius.com/Kanye-west-facts-lyrics"));
		//RapGenius.populateSongLyricsFromSongs(testArtist);
		//System.out.println(testArtist.getSongs().get(0).getSongLyrics().getArtistsLyricsFromSong("Kanye West"));
		RapGenius.searchArtist("Kanye West");
	}
}
