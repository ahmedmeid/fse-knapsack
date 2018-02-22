package com.maerskdigital.task.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maerskdigital.task.domain.Task;

public interface TaskRepository extends MongoRepository<Task, String>{
	
	List<Task> findByStatus(String status);

}
