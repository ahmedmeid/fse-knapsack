package com.maerskdigital.task.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maerskdigital.task.domain.ProblemRequest;
import com.maerskdigital.task.domain.SolutionResponse;
import com.maerskdigital.task.domain.Task;
import com.maerskdigital.task.domain.Tasks;
import com.maerskdigital.task.domain.TasksResponse;
import com.maerskdigital.task.domain.Timestamps;
import com.maerskdigital.task.repo.SolutionRepository;
import com.maerskdigital.task.repo.TaskRepository;
import com.maerskdigital.task.service.OptimizerService;
import com.maerskdigital.task.service.ShutdownService;

@RestController
@RequestMapping("/knapsack")
public class KnapsackController {
	
	
	final String TASK_STATUS_SUBMITTED = "submitted";
	final String TASK_STATUS_STARTED = "started";
	final String TASK_STATUS_COMPLETED = "completed";

    
    @Autowired
    OptimizerService optimizerService;
    
    @Autowired
    ShutdownService shutdownService;
    
    @Autowired
    TaskRepository taskRepo;
    
    @Autowired
    SolutionRepository solutionRepo;
    
    
    Logger log = Logger.getLogger(KnapsackController.class);
    
    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public Task postProblem(@RequestBody ProblemRequest problemReq) {
    	
    	  Task task = new Task();
    	  String taskId = UUID.randomUUID().toString();
    	  task.setTask(taskId);
    	  task.setStatus("submitted");
    	  Timestamps timestamps = new Timestamps();
    	  timestamps.setSubmitted(new Date().getTime());
    	  task.setTimestamps(timestamps);
    	  
    	  taskRepo.save(task);
    	  
    	  optimizerService.startTask(problemReq.getProblem(), task);
    	  
    	  return task;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public Task getTask(@PathVariable String taskId) {
    	  return taskRepo.findOne(taskId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/solutions/{taskId}")
    public SolutionResponse getSolution(@PathVariable String taskId) {
    	 return solutionRepo.findOne(taskId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/admin/tasks")
    public TasksResponse getTasks() {
    	
    	 TasksResponse response = new TasksResponse();
    	 
    	 List<Task> submittedTasks = taskRepo.findByStatus(TASK_STATUS_SUBMITTED);
    	 List<Task> startedTasks = taskRepo.findByStatus(TASK_STATUS_STARTED);
    	 List<Task> completedTasks = taskRepo.findByStatus(TASK_STATUS_COMPLETED);
    	 
    	 Tasks tasks = new Tasks();
    	 
    	 if(submittedTasks.size() > 0) {
    	 Task[] tempArray = new Task[submittedTasks.size()];
    	 tasks.setSubmitted(submittedTasks.toArray(tempArray));
    	 }
    	 
    	 if(startedTasks.size() > 0) {
        	 Task[] tempArray = new Task[startedTasks.size()];
        	 tasks.setStarted(startedTasks.toArray(tempArray));
        	 }
    	 
    	 if(completedTasks.size() > 0) {
        	 Task[] tempArray = new Task[completedTasks.size()];
        	 tasks.setCompleted(completedTasks.toArray(tempArray));
        	 }
    	 
    	 response.setTasks(tasks);
    	 
    	 return response;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/admin/shutdown")
    public @ResponseBody String Shutdown() {
    	    shutdownService.shutdown();
		return "Service shutting down...\n";
    }
    

}
