package com.maerskdigital.task.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.Solution;
import com.maerskdigital.task.domain.SolutionResponse;
import com.maerskdigital.task.domain.Task;
import com.maerskdigital.task.optimizer.KnapsackOptimizer;
import com.maerskdigital.task.repo.SolutionRepository;
import com.maerskdigital.task.repo.TaskRepository;
 
@Service
public class OptimizerService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
    TaskRepository taskRepo;
	
	@Autowired
	SolutionRepository solutionRepo;
	
	@Autowired
	KnapsackOptimizer optimizer;
	
	@Async
    public void startTask(Problem problem, Task task) {
		log.info("###Start Processing task: " + task.getTask());
		
		task.setStatus("started");
		
		Long startedTime = new Date().getTime() / 1000;
		
		task.getTimestamps().setStarted(startedTime);
		
		taskRepo.save(task);
		
		Solution sol = optimizer.solve(problem);
		
        task.setStatus("completed");
        
        Long completedTime = new Date().getTime() / 1000;
        
        task.getTimestamps().setCompleted(completedTime);
		
		taskRepo.save(task);
		
		sol.setTime(completedTime - startedTime); 
		
		SolutionResponse resp = new SolutionResponse();
		
		resp.setProblem(problem);
		
		resp.setTask(task.getTask());
		
		resp.setSolution(sol);
		
		solutionRepo.save(resp);
         
		log.info("Processing is Done for task:"+task.getTask());

    }
}