package me.oldscarflabs.lyrics;

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
	
	/**
	 * Parses Rap Genius lyrics to grab the lyrics of the artist we are interested in
	 * 
	 * @param artistDesired
	 * @return
	 */
	public String getArtistsLyricsFromSong(String artistDesired){
		String lyricsByArtistDesired = "";
	
		for(String segment : this.songLyrics.split("\\[")){
			
			if(segment.indexOf(']') == -1 && segment.indexOf('[') == -1){
				lyricsByArtistDesired += segment;
			}
			
			if(segment.length() == 0 || segment.indexOf(']') == -1){
				continue;
			}
			
			String title = segment.substring(0, segment.indexOf(']'));

			if(title.contains(artistDesired) || (!title.contains(":") && artistDesired.equals(this.primaryArtistOnSong))){
				try{
					lyricsByArtistDesired += segment.substring(segment.indexOf(']') + 3);
				}catch(Exception e){
					continue;
				}	
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
