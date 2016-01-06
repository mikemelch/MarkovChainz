package markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Markov {
	private HashMap<Prefix, DenseBag> chains;
	private HashMap<String, Integer> totalOccurances;
	
	private static final int ORDER = 2;
	
	public Markov(){
		this.chains = new HashMap<Prefix, DenseBag>();
		this.totalOccurances = new HashMap<String, Integer>();
	}
	
	private void addWord(Suffix suffix){
		String _word = suffix.getWord().toLowerCase();
		if(this.totalOccurances.containsKey(_word)){
			int value = this.totalOccurances.get(_word);
			this.totalOccurances.put(_word, (value + 1));
		}else{
			this.totalOccurances.put(_word, 1);
		}
	}
	
	private void put(Prefix prefix, Suffix suffix){
		this.addWord(suffix);

		if(this.chains.containsKey(prefix)){
			DenseBag bag = this.chains.get(prefix);
			bag.put(suffix);
			this.chains.put(prefix, bag);
		}else{
			DenseBag bag = new DenseBag();
			bag.put(suffix);
			this.chains.put(prefix, bag);
		}
	}

	public static int getOrder() {
		return ORDER;
	}
	
	public void addSentence(String sentence){
		String words[] = sentence.split("\\s+");
		
		if(words.length <= 0){
			return;
		}
		
		List<String> prefix = new ArrayList<String>();
		prefix.add("");
		for(int i = 0; i < words.length; i++){
			Suffix suffix;
			
			if(i == words.length - 1){
				suffix = new Suffix(words[i], true);
			}else{
				suffix = new Suffix(words[i], false);
			}
			
			this.put(new Prefix(prefix), suffix);
			if(prefix.size() == ORDER){
				prefix.remove(0);
			}
			prefix.add(suffix.getWord());
		}
	}
	
	private String getNextWord(List<String> prefixWords){
		Prefix prefix = new Prefix(prefixWords);
		DenseBag bag = this.chains.get(prefix);
		return bag.getWithProbability().getWord();
	}
	
	public String generate(){
		String sentence = "";
		List<String> prefix = new ArrayList<String>();
		prefix.add("");
		
		for(int i = 0; i < 5; i++){
			String next = this.getNextWord(prefix);
			sentence += next;
			if(prefix.size() == ORDER){
				prefix.remove(0);
			}
			prefix.add(next);
		}
		return sentence;
	}
	
	public String toString(){
		String toString = "Markov[";
		
		for(Map.Entry<Prefix, DenseBag> entry : this.chains.entrySet()){
			toString += "\n\t" + entry.getKey().toString() + " -> " + entry.getValue().toString();
		}
		toString += "\n]";
		return toString;
	}
}
