package markov;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DenseBag {
	
	private HashMap<Suffix, Integer> bag;
	private int totalItems;
	
	public DenseBag(){
		this.bag = new HashMap<Suffix, Integer>();
		this.totalItems = 0;
	}
	
	/**
	 * Adds a suffix to the DenseBag
	 * 
	 * @param s
	 */
	public void put(Suffix s){
		if(this.bag.containsKey(s)){
			int value = this.bag.get(s);
			this.bag.put(s, (value + 1));
		}
		else{
			this.bag.put(s, 1);
		}
		this.totalItems++;
	}
	
	/**
	 * Uses a random number generator to pick the next word using certain weights
	 * 
	 * @return Suffix
	 */
	public Suffix getWithProbability(){
		
		if(bag.size() == 1){
			return bag.keySet().iterator().next();
		}
		
		int indexToReturn = new Random().nextInt(this.totalItems) + 1;
		int currentIndex = 0;
		
		for(Map.Entry<Suffix, Integer> entry : this.bag.entrySet()){
			currentIndex += entry.getValue();
			if(currentIndex >= indexToReturn){
				return entry.getKey();
			}
		}
		
		try {
			throw new DenseBagException("No items found within getWithProbability() method");
		} catch (DenseBagException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	public String toString(){
		String toString = "DenseBag[";
		for(Map.Entry<Suffix, Integer> entry : this.bag.entrySet()){
			toString += entry.getKey().getWord() + "=" + entry.getValue() + ",";
		}
		toString += "]";
		return toString;
	}
}


@SuppressWarnings("serial")
class DenseBagException extends Exception{
	public DenseBagException(){}
	
	public DenseBagException(String message){
		super(message);
	}
}
