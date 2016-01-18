package me.oldscarflabs.lyrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private ArtistRepository repository;
	
	static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static Artist checkDatabaseForArtist(String artistName, ArtistRepository repository){
		return repository.findByArtistName(artistName);
	}
	
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
	
	public static void commandLine(ArtistRepository repository){
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
						Artist databaseQuery = checkDatabaseForArtist(artist.getArtistName(), repository);
						if(databaseQuery != null){
							System.out.println("Artist already stored in database.");
							artist = databaseQuery;
						}
						else{
							RapGenius.populateSongsFromArtistPage(artist);
							RapGenius.populateSongLyricsFromSongs(artist);
							repository.save(artist);
						}
						
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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		commandLine(repository);

		//repository.deleteAll();

		//repository.save(new Artist("Kanye West"));

		/*for (Artist artist : repository.findAll()) {
			System.out.println(artist);
		}
		System.out.println(repository.findByArtistName("Kanye est"));*/

	}
}
