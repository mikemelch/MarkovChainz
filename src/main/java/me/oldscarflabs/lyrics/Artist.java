package me.oldscarflabs.lyrics;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import me.oldscarflabs.markov.Markov;

public class Artist {
	
	@Id
	private String id;
	
	private String artistName;
	private ArrayList<Song> songs;
	private int numberOfSongs;
	private String rapGeniusArtistLink;
	Markov markovModel;
	
	public Artist(){}
	
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
	
	/**
	 * Populates an artist's markov model with their song lyrics
	 */
	public void populateArtistMarkov(int order){
		this.markovModel = new Markov(order);
		for(Song song : this.songs){
			this.markovModel.addSentence(song.getSongLyrics().getArtistsLyricsFromSong(this.artistName));
		}
	}
	
	public Markov getMarkovModel(){
		return this.markovModel;
	}
	
	@Override
	public String toString(){
		return String.format("Artist[id=%s, name='%s', songs=%d]", id, artistName, numberOfSongs);
	}
}
