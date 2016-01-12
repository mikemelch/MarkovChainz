package lyrics;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import markov.Markov;

public class RapGenius {
	
	/**
	 * Populates all songs using Rap Genius link within an Artist with its lyrics and song information
	 * 
	 * @param artist
	 */
	public static void populateSongLyricsFromSongs(Artist artist){
		Document doc = null;
		String primaryArtistName;
		String songTitle;
		String songLyrics = "";
		 
		for(Song song : artist.getSongs()){
			
			try {
				doc = Jsoup.connect(song.getRapGeniusLink()).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A").get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 
			doc = Jsoup.parse(doc.html().replaceAll("(?i)<br[^>]*>", "br2n"));
			
			Elements lyricsElem = doc.getElementsByClass("lyrics");
			Elements itemprops = doc.getElementsByAttributeValueContaining("itemprop", "title");
	
			primaryArtistName = itemprops.get(2).text();
			songLyrics = lyricsElem.get(0).text().replaceAll("br2n", ".");
			songTitle = itemprops.get(3).text().replaceAll(" Lyrics", "");
			
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
