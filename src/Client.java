import markov.Markov;

public class Client {
	
	public static void main(String args[]){
		Markov markov = new Markov();
		markov.addSentence("This is a nice sentence. This is another sentence. This is another sentence.");
		System.out.println(markov.toString());
		System.out.println(markov.generate());
	}
}
