package com.maerskdigital.task.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maerskdigital.task.domain.User;

public interface UserRepository extends MongoRepository<User, Long>{
	
	public User findByUsername(String username);

}
