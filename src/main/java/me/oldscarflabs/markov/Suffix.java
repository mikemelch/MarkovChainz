package me.oldscarflabs.markov;

public class Suffix {
	private String word;
	private boolean end;
	
	public Suffix(String word, boolean end){
		this.setWord(word);
		this.setEnd(end);
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public String toString(){
		return "Suffix: {" + this.word + ", " + this.end + "}";
	}
	
	@Override
	public int hashCode(){
		return this.word.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		Suffix other = (Suffix)o;
	    return o instanceof Suffix && this.word.equals(other.word);
	}
}
