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
	
	public String getArtistsLyricsFromSong(String artistDesired){
		String lyricsByArtistDesired = "";
		
		for(String segment : this.songLyrics.split("\\[")){
			if(segment.length() == 0 || !segment.contains(":")){
				continue;
			}
			
			String title = segment.substring(0, segment.indexOf(']'));
			if(title.contains(artistDesired)){
				lyricsByArtistDesired += segment.substring(segment.indexOf(']') + 3);
			}
		}
		return lyricsByArtistDesired;
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
