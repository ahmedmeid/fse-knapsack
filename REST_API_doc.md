# REST API's documentation

**Register User**
----
  Creates a new user.

* **URL**

  /users/sign-up

* **Method:**

  `POST`
  
*  **URL Params**

   None
 

* **Data Params**

  `{ "username" : [string], "password" : [string]}`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** None

* **Sample Call:**

  ```ssh
  curl -XPOST -H 'Content-type: application/json' http://localhost:8080/users/sign-up -d '{
  "username" : "user1",
  "password" : "secretP@ssw0rd"
   }' 
  ```
  
  
  **Login**
----
  Authenticates a user and creates a session.

* **URL**

  /login

* **Method:**

  `POST`
  
*  **URL Params**

   None

* **Data Params**

  `{ "username" : [string], "password" : [string] }`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `None`
 
* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{ "timestamp": 1519757555626, "status": 401, "error": "Unauthorized", "message": "Authentication Failed: Bad credentials", "path": "/login"}`

* **Sample Call:**

  ```ssh
    curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "user1",
    "password": "secretP@ssw0rd"
}' http://localhost:8080/login
```

**Submit Task**
----
  Receives a problem and submits optimization task asynchronously.

* **URL**

  /knapsack/tasks

* **Method:**

  `POST`
  
*  **URL Params**

   None

* **Data Params**

  `{ "problem": { "capacity": [non-negative integer], "weights": [array of non-negative integers], "values": [array of non-negative integers] } }`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"task": [string], "status": [string], "timestamps": {"submitted": [long integer], "started": [long integer], "completed": [long integer]}}`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ "timestamp": 1519755755604, "status": 403, "error": "Forbidden", "message": "Access Denied", "path": "/knapsack/tasks" }`

* **Sample Call:**

  ```ssh
  curl -H 'Content-type: application/json' -XPOST http://localhost:8080/knapsack/tasks -d '{"problem": {"capacity": 60, "weights": [10, 20, 33], "values": [10, 3, 30]}}'
  ```
  
**Retreive task**
----
  Retreives an optimization task's information.

* **URL**

  /knapsack/tasks/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[string]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"task": [string], "status": [string], "timestamps": {"submitted": [long integer], "started": [long integer], "completed": [long integer]}}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ "operationMessage": "Task with id='invalid_task_id' cannot be found.", "operationStatus": "ERROR"}`

  OR

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ "timestamp": 1519755755604, "status": 403, "error": "Forbidden", "message": "Access Denied", "path": "/knapsack/tasks" }`

* **Sample Call:**

  ```ssh
    curl http://localhost:8080/knapsack/tasks/aff270fa-1c29-4b2e-96fb-ef6d6adaf31f \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'
```
	  
**Retreive Solution**
----
  Retreive Solution

* **URL**

  Retreive Solution:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "task": [string] "problem": {"capacity": [non-negative integer]"weights": [array of non-negative integers],"values": [array of non-negative integers]
    }, "solution": {"items" : [array of integers],"time": [non-negative integer] 
    }
}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ "operationMessage": "Solution for task with id='task_id' cannot be found.", "operationStatus": "ERROR"}`

  OR

  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ "timestamp": 1519755755604, "status": 403, "error": "Forbidden", "message": "Access Denied", "path": "/knapsack/solutions" }`

* **Sample Call:**

  ```ssh
    curl http://localhost:8080/knapsack/solutions/aff270fa-1c29-4b2e-96fb-ef6d6adaf31f \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'
```
	  
	  
**Retreive tasks**
----
  Retreives a list of optimization task's information.

* **URL**

  /knapsack/admin/tasks

* **Method:**

  `GET`
  
*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{"task": [string], "status": [string], "timestamps": {"submitted": [long integer], "started": [long integer], "completed": [long integer]}}]`
 
* **Error Response:**


  * **Code:** 403 FORDIDDEN <br />
    **Content:** `{ "timestamp": 1519755755604, "status": 403, "error": "Forbidden", "message": "Access Denied", "path": "/knapsack/tasks" }`

* **Sample Call:**

  ```ssh
    curl http://localhost:8080/knapsack/admin/tasks \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'
```
	  
	  
**Shutdown service**
----
  Stops the knapsack optimization service gracefully.

* **URL**

  /knapsack/admin/shutdown

* **Method:**

  `POST`
  
*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `"Service shutting down..."`
 
* **Error Response:**


  * **Code:** 403 FORBIDDEN <br />
    **Content:** `{ "timestamp": 1519755755604, "status": 403, "error": "Forbidden", "message": "Access Denied", "path": "/shutdown" }`

* **Sample Call:**

  ```ssh
    curl -XPOST http://localhost:8080/knapsack/admin/shutdown \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'
```