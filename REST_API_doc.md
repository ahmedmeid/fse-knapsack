# REST API's documentation

**Submit Task**
----

Receives a problem and submits optimization task asynchronously

* **URL**

    /knapsack/tasks

* **Method**

    `POST`

* **URL Params**

    None

* **Data Params**

    **Required:**
    ```json
	{
      "problem": {
                  "capacity": # non-negative integer
                  "weights": # array of non-negative integers
                  "values": # array of non-negative integers, as many as weights
     }
    }
    ```

* **Success Response:**

    * **Code: 200 OK**
        **Content:** 
        ```json
                {
                  "task": # ASCII string
                  "status": # one of "submitted", "started", "completed"
                  "timestamps": {
                                  "submitted": # unix/epoch time
                                  "started": # unix/epoch time or null if not started
                                  "completed": # unix/epoch time or null if not completed
                                }
                 }
                 ```

* **Error Response:**

  * **Code: 401 UNAUTHORIZED**
     * **Content:** `{ error : "You are unauthorized to use this service." }`

    * ** Sample Call:**
```javascript
      $.ajax({
        url: "/knapsack/tasks",
        dataType: "json",
        type : "POST",
        success : function(r) {
          console.log(r);
        }
      });
      
      ```
	  
	  
Retreive task

Retreives an optimization task's information

    URL

    /knapsack/tasks/{task_id}

    Method:

    GET

    URL Params

	Required
	
    task_id : String

    Data Params

    none

    Success Response:
        Code: 200
        Content: {
                  "task": # ASCII string
                  "status": # one of "submitted", "started", "completed"
                  "timestamps": {
                                  "submitted": # unix/epoch time
                                  "started": # unix/epoch time or null if not started
                                  "completed": # unix/epoch time or null if not completed
                                }
                 }

    Error Response:

        Code: 401 UNAUTHORIZED
        Content: { error : "You are unauthorized to use this service." }

    Sample Call:

      $.ajax({
        url: "/knapsack/tasks/",
        dataType: "json",
        type : "POST",
        success : function(r) {
          console.log(r);
        }
      });
	  
Retreive Solution

Retreives solution for an optimization task.

    URL

    /knapsack/solutions/{task_id}

    Method:

    GET

    URL Params

	Required
	
    task_id : String

    Data Params

    none

    Success Response:
        Code: 200
        Content: {
    "task": # ASCII string
    "problem": {
        "capacity": # non-negative integer
        "weights": # array of non-negative integers
        "values": # array of non-negative integers, as many as weights
    }
    "solution": {
        "items" : # array of integers (indices to weights and values)
        "time": # non-negative integer, time elapsed between the task was started and completed
    }
}

    Error Response:
	
	    Code: 404 NOT_FOUND
        Content: { error : "No sultion found for this task." }

        Code: 401 UNAUTHORIZED
        Content: { error : "You are unauthorized to use this service." }

    Sample Call:

      $.ajax({
        url: "/knapsack/solutions/",
        dataType: "json",
        type : "GET",
        success : function(r) {
          console.log(r);
        }
      });
	  
	  
Retreive tasks

Retreives a list of optimization task's information

    URL

    /knapsack/admin/tasks

    Method:

    GET

    URL Params

	Required
	
    none

    Data Params

    none

    Success Response:
        Code: 200
        Content: [{
                  "task": # ASCII string
                  "status": # one of "submitted", "started", "completed"
                  "timestamps": {
                                  "submitted": # unix/epoch time
                                  "started": # unix/epoch time or null if not started
                                  "completed": # unix/epoch time or null if not completed
                                }
                 }]

    Error Response:

        Code: 401 UNAUTHORIZED
        Content: { error : "You are unauthorized to use this service." }

    Sample Call:

      $.ajax({
        url: "/knapsack/tasks/",
        dataType: "json",
        type : "GET",
        success : function(r) {
          console.log(r);
        }
      });
	  
	  
Shutdown service

Stops the knapsack optimization service gracefully

    URL

    /knapsack/admin/shutdown

    Method:

    POST

    URL Params

	none

    Data Params

    none

    Success Response:
        Code: 200
        Content: "Service shutting down..."

    Error Response:

        Code: 401 UNAUTHORIZED
        Content: { error : "You are unauthorized to stop this service." }

    Sample Call:

      $.ajax({
        url: "/knapsack/tasks/",
        dataType: "json",
        type : "POST",
        success : function(r) {
          console.log(r);
        }
      });
	  


