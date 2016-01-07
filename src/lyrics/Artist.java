package lyrics;

import java.util.ArrayList;

import markov.Markov;

public class Artist {
	private String artistName;
	private ArrayList<Song> songs;
	private int numberOfSongs;
	private String rapGeniusArtistLink;
	Markov markovModel;
	
	public Artist(String artistName){
		this.setArtistName(artistName);
		this.songs = new ArrayList<Song>();
		this.setNumberOfSongs(0);
		this.markovModel = new Markov();
		this.setRapGeniusArtistLink("");
	}
	
	public void addSong(Song s){
		this.setNumberOfSongs(this.getNumberOfSongs() + 1);
		this.songs.add(s);
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public ArrayList<Song> getSongs(){
		return this.songs;
	}

	public int getNumberOfSongs() {
		return numberOfSongs;
	}

	public void setNumberOfSongs(int numberOfSongs) {
		this.numberOfSongs = numberOfSongs;
	}

	public String getRapGeniusArtistLink() {
		return rapGeniusArtistLink;
	}

	public void setRapGeniusArtistLink(String rapGeniusArtistLink) {
		this.rapGeniusArtistLink = rapGeniusArtistLink;
	}
	
	public void populateArtistMarkov(){
		//TODO
	}
}
