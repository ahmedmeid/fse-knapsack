package com.maerskdigital.task.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.maerskdigital.task.domain.SolutionResponse;

public interface SolutionRepository extends MongoRepository<SolutionResponse, String>{

}
