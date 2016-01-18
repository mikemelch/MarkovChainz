package me.oldscarflabs.lyrics;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String>{

	public Artist findByArtistName(String artistName);
}
