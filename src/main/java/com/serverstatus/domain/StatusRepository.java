package com.serverstatus.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StatusRepository extends MongoRepository<Status, String>{
	
	public List<Status> findByGame(String game);
	public List<Status> findByCreateDateGreaterThan(Date date);
	
	@Query("{'createDate': {$gt: ?0}, 'game': ?1}")
	public List<Status> findByGameAndByCreateDateGreaterThan(Date date, String game);
}
