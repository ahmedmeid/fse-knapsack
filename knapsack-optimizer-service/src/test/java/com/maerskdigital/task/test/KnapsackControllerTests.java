package com.maerskdigital.task.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class KnapsackControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitTask() throws Exception{
    	/*
      	this.mockMvc.perform(post("/knapsack/admin/tasks"))
                    .andDo(print()).andExpect(status().isOk());
    	 */  
    }
    
    @Test
    public void queryTaskStatus() throws Exception{
    	
      	this.mockMvc.perform(get("/knapsack/tasks/f5083116-2d96-49ca-9020-e9f22fa0c3bb"))
                    .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getSolution() throws Exception{
    	
      	this.mockMvc.perform(get("/knapsack/solutions/f5083116-2d96-49ca-9020-e9f22fa0c3bb"))
            .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void getTasks() throws Exception{
    	
     	this.mockMvc.perform(get("/knapsack/admin/tasks"))
            .andDo(print()).andExpect(status().isOk());
    	
    }
    
    @Test
    public void shutdown() throws Exception{
    	/*
    	  this.mockMvc.perform(post("/admin/shutdown"))
          .andDo(print()).andExpect(status().isOk())
          .andExpect(jsonPath("$.content").value("Service shutting down...\n"));
    	*/
    }

}
