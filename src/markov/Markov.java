package markov;

import java.util.HashMap;
import java.util.Vector;

public class Markov {
	private HashMap<String, Vector<String>> chains;
	private HashMap<String, Integer> totalOccurances;
	private Vector<String> starterWords;
	private Vector<String> endWords;
	
	
	public Markov(){
		this.chains = new HashMap<String, Vector<String>>();
		this.totalOccurances = new HashMap<String, Integer>();
		this.starterWords = new Vector<String>();
		this.endWords = new Vector<String>();
	}
	
}
