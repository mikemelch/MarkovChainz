package me.oldscarflabs.lyrics;

public class Song {
	private String songTitle;
	private String rapGeniusLink;
	private Lyrics songLyrics;
	
	public Song(){}
	
	public Song(String rapGeniusLink){
		this.setRapGeniusLink(rapGeniusLink);
		this.setSongLyrics(new Lyrics());
	}
	
	public Song(String songTitle, String rapGeniusLink){
		this.setSongTitle(songTitle);
		this.setRapGeniusLink(rapGeniusLink);
		this.setSongLyrics(new Lyrics());
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getRapGeniusLink() {
		return rapGeniusLink;
	}

	public void setRapGeniusLink(String rapGeniusLink) {
		this.rapGeniusLink = rapGeniusLink;
	}

	public Lyrics getSongLyrics() {
		return songLyrics;
	}

	public void setSongLyrics(Lyrics songLyrics) {
		this.songLyrics = songLyrics;
	}
}
