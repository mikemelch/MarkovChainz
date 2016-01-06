package lyrics;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import markov.Markov;

public class RapGenius {
	
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
			for(String s : lyrics.split("\n")){
				m.addSentence(s);
			}
			System.out.println(m.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
