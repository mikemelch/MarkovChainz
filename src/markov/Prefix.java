package markov;

import java.util.List;

public class Prefix {
	private String words;
	
	public Prefix(List<String> words){
		this.words = "";
		for(String s : words){
			this.words += (s + ":");
		}
		this.words = this.words.substring(0, this.words.length() - 1);
		
	}

	public String getWords(){
		return words;
	}
	
	@Override
	public int hashCode(){
		return this.words.hashCode();
	}
	
	@Override
	public boolean equals(Object o){	
		if(!(o instanceof Prefix)){
			return false;
		}
		Prefix other = (Prefix)o;
		return other.words.equals(this.words);
	}

	public int size() {
		return this.words.split(":").length;
	}
	
	public String toString(){
		String[] words = this.words.split(":");
		String toString = "[";
		for(String s : words){
			toString += s + ",";
		}
		toString = toString.substring(0, toString.length() - 1);
		toString += "]";
		return toString;
	}
	
}
