package markov;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DenseBag {
	
	private HashMap<String, Integer> bag;
	private int totalItems;
	
	public DenseBag(){
		this.bag = new HashMap<String, Integer>();
		this.totalItems = 0;
	}
	
	private void put(String s){
		if(this.bag.containsKey(s)){
			int value = this.bag.get(s);
			this.bag.put(s, (value + 1));
		}
		else{
			this.bag.put(s, 1);
		}
		this.totalItems++;
	}
	
	private String getWithProbability(){
		int indexToReturn = new Random().nextInt(this.totalItems);
		int currentIndex = 0;
		
		for(Map.Entry<String, Integer> entry : this.bag.entrySet()){
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
}


@SuppressWarnings("serial")
class DenseBagException extends Exception{
	public DenseBagException(){}
	
	public DenseBagException(String message){
		super(message);
	}
}
