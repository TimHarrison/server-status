package com.serverstatus.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String>{
	
	public List<Sequence> findByPublicId(int publicId);
	public List<Sequence> findByOrganization(Game organization);
}
