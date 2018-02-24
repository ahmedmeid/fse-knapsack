package com.maerskdigital.task.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.maerskdigital.task.domain.ProblemRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class KnapsackControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitTask() throws Exception{
    	
    	ProblemRequest problemRequest = new ProblemRequestBuilder().capacity(850)
                                                               .weights(new Integer[] {7, 0, 30, 22, 80, 94, 11, 81, 70,
                                                                       64, 59, 18, 0, 36, 3, 8, 15, 42,
                                                                       9, 0, 42, 47, 52, 32, 26, 48, 55,
                                                                       6, 29, 84, 2, 4, 18, 56, 7, 29,
                                                                       93, 44, 71, 3, 86, 66, 31, 65, 0,
                                                                       79, 20, 65, 52, 13})
    	                                                           .values(new Integer[] {360, 83, 59, 130, 431, 67, 230, 52, 93,
    	                                                                   125, 670, 892, 600, 38, 48, 147, 78, 256,
    	                                                                   63, 17, 120, 164, 432, 35, 92, 110, 22,
    	                                                                   42, 50, 323, 514, 28, 87, 73, 78, 15,
    	                                                                   26, 78, 210, 36, 85, 189, 274, 43, 33,
    	                                                                   10, 19, 389, 276, 312}).build();  	
    	
      	mockMvc.perform(post("/knapsack/tasks")
      			.contentType(TestUtil.APPLICATION_JSON_UTF8)
      			.content(TestUtil.convertObjectToJsonBytes(problemRequest)))
      	.andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
    }
    
    @Test
    public void queryTaskStatus() throws Exception{
    	
      	mockMvc.perform(get("/knapsack/tasks/f5083116-2d96-49ca-9020-e9f22fa0c3bb"))
                    .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getSolution() throws Exception{
    	
      	mockMvc.perform(get("/knapsack/solutions/f5083116-2d96-49ca-9020-e9f22fa0c3bb"))
            .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getTasks() throws Exception{
    	
     	mockMvc.perform(get("/knapsack/admin/tasks"))
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
