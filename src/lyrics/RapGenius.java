package lyrics;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import markov.Markov;

public class RapGenius {
	
	 public static void populateSongLyricsFromSongs(Artist artist){
		 Document doc = new Document(null);
		 String primaryArtistName;
		 String songTitle;
		 String songLyrics = "";
		 
		 for(Song song : artist.getSongs()){
			 try {
				 doc = Jsoup.connect(song.getRapGeniusLink()).userAgent("Mozilla").get();
			 } catch (IOException e1) {
				 e1.printStackTrace();
			 }
			 
			doc = Jsoup.parse(doc.html().replaceAll("(?i)<br[^>]*>", "br2n"));
			
			Elements lyricsElem = doc.getElementsByClass("lyrics");
			Elements primaryArtist = doc.getElementsByClass("song_header-primary_info-primary_artist");
			Elements songTitleElem = doc.getElementsByClass("song_header-primary_info-title");
			
			primaryArtistName = primaryArtist.get(0).text();
			songLyrics = lyricsElem.get(0).text().replaceAll("br2n", ".\n");
			songLyrics = songLyrics.replaceAll(".\n", ". ");
			songTitle = songTitleElem.get(0).text();
			
			song.setSongLyrics(new Lyrics(songLyrics, primaryArtistName));
			song.setSongTitle(songTitle);
		 }
		
		
	 }
	
	public static int populateSongsFromArtistPage(Artist artist){
		//TODO
		return artist.getNumberOfSongs();
	}
	
	public static Artist searchArtist(String artistSearch){
		//TODO
		return null;
	}
}
