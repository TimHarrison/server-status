package com.serverstatus.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchivedStatusRepository extends MongoRepository<ArchivedStatus, String>{
	
	public List<ArchivedStatus> findByGame(String game);
	public List<ArchivedStatus> findByCreateDateGreaterThan(Date date);
	public ArchivedStatus findLastByOrderByCreateDateDesc();
}
