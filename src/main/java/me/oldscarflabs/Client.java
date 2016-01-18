package me.oldscarflabs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.oldscarflabs.lyrics.Artist;
import me.oldscarflabs.lyrics.RapGenius;

public class Client {
	
	static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static String[] getInput(){
		System.out.printf(">>> ");
		System.out.flush();
		String result = null;
		try {
		    result = inputReader.readLine();
		} catch (IOException ex) {
		    System.err.println(ex.getMessage());
		    System.exit(1);
		}
		return result.trim().split(" ");
	}
	
	public static void commandLine(){
		Artist artist = null;
		while(true){
			String[] command = getInput();
			if(command.length > 0){
				switch(command[0]){
					case "generate":
						if(artist == null){
							System.out.println("Search for an artist before generating text.");
							break;
						}
						System.out.println(artist.getMarkovModel().generate(Integer.parseInt(command[1])));
						break;
					case "search":
						String searchQuery = "";
						for(int i = 1; i < command.length; i++){
							searchQuery += command[i] + " ";
						}
						
						artist = RapGenius.searchArtist(searchQuery.trim());
						RapGenius.populateSongsFromArtistPage(artist);
						RapGenius.populateSongLyricsFromSongs(artist);
						
						break;
					case "markov":
						artist.populateArtistMarkov(Integer.parseInt(command[1]));
						System.out.println("Created Markov model with an order = " + Integer.parseInt(command[1]));
						break;
					default:
						System.out.println("Welcome to Markov Chainz.\n\nCommands:\n"
								+ "\n\tsearch <artist name>"
								+ "\n\tgenerate <number of words>"
								+ "\n\tmarkov <order>");
						break;
						
				}
			}
		}
		
	}
	
	public static void main(String args[]){
		commandLine();
		/*Artist testArtist = RapGenius.searchArtist("drake");
		
		RapGenius.populateSongsFromArtistPage(testArtist);
		RapGenius.populateSongLyricsFromSongs(testArtist);
		testArtist.populateArtistMarkov();
		System.out.println(testArtist.getMarkovModel().generate(200));*/
	}
}
