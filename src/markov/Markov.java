package markov;

import java.util.HashMap;
import java.util.Vector;

public class Markov {
	private HashMap<Vector<String>, DenseBag> chains;
	private HashMap<String, Integer> totalOccurances;
	private Vector<String> starterWords;
	private Vector<String> endWords;
	
	private static final int ORDER = 2;
	
	public Markov(){
		this.chains = new HashMap<Vector<String>, DenseBag>();
		this.totalOccurances = new HashMap<String, Integer>();
		this.starterWords = new Vector<String>();
		this.endWords = new Vector<String>();
	}
	
	private void put(Vector<String> prefix, String suffix){
		if(prefix.get(0))
	}
}
