package me.oldscarflabs.lyrics;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RapGenius {
	
	static String baseLink = "http://genius.com";
	
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
			
			System.out.println("Processing song: " + song.getSongTitle());
			
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
	
	/**
	 * Populates all of an artist's songs on Rap Genius into the passed Artist object
	 * 
	 * @param artist
	 * @param maxSongs
	 * @return
	 */
	public static int populateSongsFromArtistPage(Artist artist, int maxSongs){
		
		System.out.print("Populating artist's songs..");
		
		Document doc = null;
		String url = artist.getRapGeniusArtistLink();
		int songCount = 0;
		
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9").get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Elements paginationElements = doc.getElementsByClass("pagination");
		
		//if an artist only has one page of songs
		if(paginationElements.size() == 0){
			Elements songLinks = doc.getElementsByClass("song_link");
			
			for(Element songLink : songLinks){
				if(songCount >= maxSongs){
					return artist.getNumberOfSongs();
				}
				songCount++;
				String songTitle = songLink.getElementsByClass("song_title").text();
				artist.addSong(new Song(songTitle, songLink.attr("href")));
			}
			System.out.println();
			return artist.getNumberOfSongs();
		}
		String paginationLink = paginationElements.get(0).getElementsByTag("a").get(0).attr("href");
		paginationLink = paginationLink.split("&page=")[0];
		paginationLink += "&page=";
		
		int pageCount = 1;
		
		while(songCount <= maxSongs){
			
			if(pageCount % 5 == 0) System.out.print(".");
			
			try {
				doc = Jsoup.connect(baseLink + paginationLink + pageCount).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9").get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Elements songLinks = doc.getElementsByClass("song_link");
			
			if(songLinks.size() == 0){
				System.out.println();
				return artist.getNumberOfSongs();
			}
			
			for(Element songLink : songLinks){
				String songTitle = songLink.getElementsByClass("song_title").text();
				artist.addSong(new Song(songTitle, songLink.attr("href")));
				songCount++;
			}
			
			pageCount++;
		}
		return artist.getNumberOfSongs();
	}
	
	
	/**
	 * Populates all of an artist's songs on Rap Genius into the passed Artist object
	 * Default value for maxSongs is 200
	 * 
	 * @param artist
	 * @return
	 */
	public static int populateSongsFromArtistPage(Artist artist){
		return populateSongsFromArtistPage(artist, 200);
	}
	
	/**
	 * Searches for an artist on Rap Genius and returns the newly created Artist object
	 * 
	 * @param artistSearch
	 * @return
	 */
	public static Artist searchArtist(String artistSearch){
		Document doc = null;
		String url = "http://genius.com/search/artists?q=";
		
		try {
			doc = Jsoup.connect(url + URLEncoder.encode(artistSearch.toLowerCase(), "UTF-8")).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A").get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Elements artistLinks = doc.getElementsByClass("artist_link");
		
		for(Element artistLink : artistLinks){
			if(artistLink.text().toLowerCase().equals(artistSearch.toLowerCase())){
				String rapGeniusArtistName = artistLink.text();
				Artist newArtist = new Artist(rapGeniusArtistName);
				newArtist.setRapGeniusArtistLink(artistLink.attr("href"));
				System.out.println("Found artist: " + rapGeniusArtistName);
				return newArtist;
			}
		}
		return null;
	}
}
