package com.maerskdigital.task.test.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.maerskdigital.task.domain.Problem;
import com.maerskdigital.task.domain.ProblemRequest;
import com.maerskdigital.task.domain.Solution;
import com.maerskdigital.task.domain.SolutionResponse;
import com.maerskdigital.task.domain.Task;
import com.maerskdigital.task.domain.Timestamps;
import com.maerskdigital.task.domain.User;
import com.maerskdigital.task.repo.SolutionRepository;
import com.maerskdigital.task.repo.TaskRepository;
import com.maerskdigital.task.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KnapsackControllerTests{

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TaskRepository taskRepo;
    
    @MockBean
    private SolutionRepository solutionRepo;
    
    @MockBean
    private UserRepository userRepository;
    
    private String authenticationToken;
    
    @Test
    public void testSignup() throws Exception{
      User user = new User();
      user.setUsername("user1");
      user.setPassword("secret");
    	  given(userRepository.save(user)).willReturn(user);
    	  mockMvc.perform(post("/users/sign-up")
    			  .contentType(TestUtil.APPLICATION_JSON_UTF8)
        		  .content(TestUtil.convertObjectToJsonBytes(user)))
    	          .andExpect(status().isOk());
    }
    

    @Before
    public void setUp() throws Exception{
    	   User userOnDB = new User();
    	   userOnDB.setUsername("user1");
    	   userOnDB.setPassword("$2a$10$/5ATjVrzLXKIsi6dgES6WOS8RDEvQcqyb2ErxQg29SamkJtKgkzTi");
       given(userRepository.findByUsername(userOnDB.getUsername())).willReturn(userOnDB);
       User userOnRequest = new User();
       userOnRequest.setUsername("user1");
       userOnRequest.setPassword("secret");
       MvcResult result = mockMvc.perform(post("/login")
    			   .contentType(TestUtil.APPLICATION_JSON_UTF8)
    			   .content(TestUtil.convertObjectToJsonBytes(userOnRequest)))
    			   .andExpect(status().isOk())
    			   .andReturn();
       authenticationToken = result.getResponse().getHeader("Authorization");  
    }
    

    @Test
    public void testSubmitTask() throws Exception{
    	
    	Task task = new Task("3491376d-07c9-47f0-8cc2-ba6987ef7463", new Timestamps(new Date().getTime()));
    	
    	given(this.taskRepo.save(task)).willReturn(task);
    	
    	ProblemRequest problemRequest = new ProblemRequestBuilder().capacity(850)
                                                               .weights(new int[] {7, 0, 30, 22, 80, 94, 11, 81, 70,
                                                                       64, 59, 18, 0, 36, 3, 8, 15, 42,
                                                                       9, 0, 42, 47, 52, 32, 26, 48, 55,
                                                                       6, 29, 84, 2, 4, 18, 56, 7, 29,
                                                                       93, 44, 71, 3, 86, 66, 31, 65, 0,
                                                                       79, 20, 65, 52, 13})
    	                                                           .values(new int[] {360, 83, 59, 130, 431, 67, 230, 52, 93,
    	                                                                   125, 670, 892, 600, 38, 48, 147, 78, 256,
    	                                                                   63, 17, 120, 164, 432, 35, 92, 110, 22,
    	                                                                   42, 50, 323, 514, 28, 87, 73, 78, 15,
    	                                                                   26, 78, 210, 36, 85, 189, 274, 43, 33,
    	                                                                   10, 19, 389, 276, 312}).build();  	
      	mockMvc.perform(post("/knapsack/tasks")
      			.contentType(TestUtil.APPLICATION_JSON_UTF8)
      			.header("Authorization", authenticationToken)
      			.content(TestUtil.convertObjectToJsonBytes(problemRequest)))
      	.andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void queryTaskStatus() throws Exception{
    	System.out.println("in queryTask #####*****"+authenticationToken+"******########");
     	String taskId = "f5083116-2d96-49ca-9020-e9f22fa0c3bb";
	    Task task = new Task(taskId, new Timestamps(new Date().getTime()));
	    given(taskRepo.findOne(taskId)).willReturn(task);
      	mockMvc.perform(get("/knapsack/tasks/f5083116-2d96-49ca-9020-e9f22fa0c3bb")
      			    .header("Authorization", authenticationToken))
                    .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getSolution() throws Exception{
    	    Solution solution = new Solution();
    	    solution.setTime(17L);
    	    solution.setItems(new int[] {0,2});
    	    SolutionResponse solutionResp = new SolutionResponse();
    	    solutionResp.setProblem(new Problem(60, new int[] {10, 20, 33}, new int[] {10, 3, 30}));
    	    solutionResp.setTask("f5083116-2d96-49ca-9020-e9f22fa0c3bb");
    	    solutionResp.setSolution(solution);
    	    given(solutionRepo.findOne("f5083116-2d96-49ca-9020-e9f22fa0c3bb")).willReturn(solutionResp);
      	mockMvc.perform(get("/knapsack/solutions/f5083116-2d96-49ca-9020-e9f22fa0c3bb")
      			.header("Authorization", authenticationToken))
                .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getTasks() throws Exception{
    	    Task task = new Task("f5083116-2d96-49ca-9020-e9f22fa0c3bb", new Timestamps(new Date().getTime()));
    	    List<Task> tasks = new ArrayList<>();
    	    tasks.add(task);
    	    given(taskRepo.findByStatus("completed")).willReturn(tasks);
     	mockMvc.perform(get("/knapsack/admin/tasks")
     			.header("Authorization", authenticationToken))
                .andDo(print()).andExpect(status().isOk());
    }
    
    @Test
    public void shutdown() throws Exception{
    	/*
    	  mockMvc.perform(post("/admin/shutdown"))
          .andDo(print()).andExpect(status().isOk())
          .andExpect(jsonPath("$.content").value("Service shutting down...\n"));
    	*/
    }

}
