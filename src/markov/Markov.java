package markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Markov {
	private HashMap<Prefix, DenseBag> chains;
	private HashMap<String, Integer> totalOccurances;
	
	private int ORDER;
	
	public Markov(){
		this.chains = new HashMap<Prefix, DenseBag>();
		this.totalOccurances = new HashMap<String, Integer>();
		this.ORDER = 2;
	}
	
	public Markov(int order){
		this.chains = new HashMap<Prefix, DenseBag>();
		this.totalOccurances = new HashMap<String, Integer>();
		this.ORDER = order;
	}
	
	/**
	 * Updates the totalOccurances HashMap to reflect the current counts of all words
	 * 
	 * @param suffix
	 */
	private void addWord(Suffix suffix){
		String _word = suffix.getWord().toLowerCase();
		if(this.totalOccurances.containsKey(_word)){
			int value = this.totalOccurances.get(_word);
			this.totalOccurances.put(_word, (value + 1));
		}else{
			this.totalOccurances.put(_word, 1);
		}
	}
	
	/**
	 * Updates the chains HashMap with the new prefix and suffix combination
	 * 
	 * @param prefix
	 * @param suffix
	 */
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
	/**
	 * Returns the final variable ORDER
	 * 
	 * @return
	 */
	public int getOrder() {
		return ORDER;
	}
	
	/**
	 * Uses Markov theory to add prefix -> following word mappings
	 * The prefix will always be less than or equal to the defined ORDER (usually 2 - 4)
	 * 
	 * @param sentence
	 */
	public void addSentence(String sentence){
		String words[] = sentence.split("\\s+");
		
		if(words.length <= 0 || words[0].equals(".")){
			return;
		}
		
		List<String> prefix = new ArrayList<String>();
		prefix.add(""); //The empty string is always the first character
		
		for(int i = 0; i < words.length; i++){
			Suffix suffix;
			if(words[i].equals(".")){
				continue;
			}
			
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
	
	/**
	 * Method which determines the next word given a list of prefix words
	 * 
	 * @param prefixWords
	 * @return String
	 */
	private String getNextWord(List<String> prefixWords){
		Prefix prefix = new Prefix(prefixWords);
		if(this.chains.containsKey(prefix)){
			DenseBag bag = this.chains.get(prefix);
			return bag.getWithProbability().getWord();
		}else{
			return null;
		}
		
		
	}
	
	/**
	 * Takes an integer which is the maximum number of words the user would like generated
	 * 
	 * @param maxWords
	 * @return String
	 */
	public String generate(int maxWords){
		String sentence = "";
		List<String> prefix = new ArrayList<String>();
		prefix.add("");
		
		for(int i = 0; i < maxWords; i++){
			String next = this.getNextWord(prefix);
			if(next == null){
				
			}
			sentence += (next + " ");
			if(prefix.size() == ORDER){
				prefix.remove(0);
			}
			prefix.add(next);
		}
		return sentence;
	}
	
	/**
	 * If not maxWords isn't specified, run generate with a default value (15)
	 * 
	 * @return String
	 */
	public String generate(){
		return generate(15);
	}

	@Override
	public String toString(){
		String toString = "Markov[";
		
		for(Map.Entry<Prefix, DenseBag> entry : this.chains.entrySet()){
			toString += "\n\t" + entry.getKey().toString() + " -> " + entry.getValue().toString();
		}
		toString += "\n]";
		return toString;
	}
}
