package com.serverstatus.domain;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String>{
	
	public List<Game> findById(Integer id);
//	public List<Organization> findByPublicId(Integer publicId);
	public Game findByTitle(String title);
	
//	@Query("{}.sort({publicId:-1}).limit(1)")
//	public Organization findLatestOrganization();
}
