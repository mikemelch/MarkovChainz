package lyrics;

public class Lyrics {
	private String songLyrics;
	private String primaryArtistOnSong; /* This is the song's primary artist -- must compare 
									   to artist to determine if song lyrics are accurate */
	
	public Lyrics(String songLyrics, String primaryArtistOnSong){
		this.setSongLyrics(songLyrics);
		this.setPrimaryArtistOnSong(primaryArtistOnSong);
	}
	
	public Lyrics() {

	}
	
	public String getArtistsLyricsFromSong(){
		//TODO
		return null;
	}

	public String getSongLyrics() {
		return songLyrics;
	}

	public void setSongLyrics(String songLyrics) {
		this.songLyrics = songLyrics;
	}

	public String getPrimaryArtistOnSong() {
		return primaryArtistOnSong;
	}

	public void setPrimaryArtistOnSong(String primaryArtistOnSong) {
		this.primaryArtistOnSong = primaryArtistOnSong;
	}
}
