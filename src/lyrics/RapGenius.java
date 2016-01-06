package lyrics;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import markov.Markov;

public class RapGenius {
	
	 public static void populateSongLyricsFromSongs(Artist artist){
		//TODO
	 }
	
	public static int populateSongsFromArtistPage(Artist artist){
		//TODO
		return artist.getNumberOfSongs();
	}
	
	public static Artist searchArtist(String artistSearch){
		//TODO
		return null;
	}
	
	public static void main(String args[]){
		try {
			Document doc = Jsoup.connect("http://genius.com/Travis-scott-wonderful-lyrics").userAgent("Mozilla").get();
			doc = Jsoup.parse(doc.html().replaceAll("(?i)<br[^>]*>", "br2n"));
			Elements elems = doc.getElementsByClass("lyrics");
			String lyrics = "";
			for(Element e : elems){
				lyrics = e.text().replaceAll("br2n", ".\n");
			}
			Markov m = new Markov();
			lyrics = lyrics.replaceAll(".\n", ". ");
			m.addSentence(lyrics);
			System.out.println(m.toString());
			System.out.println(m.generate(50));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
