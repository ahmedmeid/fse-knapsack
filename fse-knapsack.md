# Knapsack Optimizer Service

Last modified on 2017.09.12 by [Waclaw Kusnierczyk](waclaw.kusnierczyk@gmail.com).

## Introduction

This document provides a homework challenge for candidates for full-stack software engineering positions within Maersk Digital.  
The goal is to see how you approach a problem, how you design, structure, implement, and deliver a solution, and how you discuss its details in front of a technical audience.

### General guidelines

* Your code and other deliverables must be provided as a link to a private git repository (GitHub ro Bitbucket).
* The delivery must include a markdown readme file with concise and complete instructions on how to use (unpack, build, install, execute, access, etc.) your service.
* You must provide additional documentation (architecture, structure of the code base, discussion of the technology stack choices you made, etc); the format is up to you, but ideally it will be some form of source code (e.g., markdown). 
* Your solution must only be accessible to you and us; please make sure it is not available for a wider audience, especially not publicly.
We would like to reuse the challenge, please help us keeping it fair!

## The problem

Maersk Digital works with a variety of real-life optimisation problems.  
The motivation behind this challenge is to let you experience the process of providing an optimiser service, a task you might expect at work if you join us, but in a simplistic optimisation context.

In the Knapsack problem, one specifies the weights and values of a collection of items and the capacity of a knapsack, and is looking for a way to pack some of the items, up to the capacity constraint, to maximise the total value of the knapsack's content.    
If you're not familiar with the Knapsack problem, consider starting with the relevant [Wikipedia article](https://en.wikipedia.org/wiki/Knapsack_problem).  

## The task

You are asked to develop a service that will allow a user to:

* specify the parameters of a knapsack problem (weights and values of items, capacity);
* execute the optimiser;
* retrieve the solution.

You are free to reuse existing code implementing a knapsack problem solver (e.g., [Google Optimization Tools](https://developers.google.com/optimization/bin/knapsack))  

## Specifications

You are expected to meet all the minimal requirements.
Extra credit will be given for meeting any of the additional requirements.

### Minimal requirements

Your delivery should:

* include a concise description of the design (architecture) of your solution;
* contain a discussion of the architecture, alternative designs, and justifications for choosing the one you chose;
* include complete code needed to execute the solution;
* include a suite of tests covering all key components of your solution; 
* use a build system with targets for building, testing, deploying, and executing the solution;
* either contain all additional dependencies (e.g., external libraries, if you use any) or handle the download and installation thereof as needed (ideally, as part of the build process);
* include a concise instruction on how to start and use the solution;
* provide a REST API with endpoints to submit optimization tasks asynchnronously (especially important for larger problems that may take substantial time to compute), checking the status of a specific task (submitted, running, completed), and retrieving the solution of a complete task; 
* provide a REST API for monitoring and diagnostics of the service (checking the status, downloading logs, etc.);
* explain the details of the algorithm used to solve the optimization task;
* implement an algorithmically correct solution to the knapsack problem.

For the REST APIs, please provide complete documentation of all endpoints, with details of the required arguments, input and output formats, possible http status codes, and their semantics. 

Minimally, it should be possible to:

* start your solution as a service on a local machine;
* interact with your solution through the REST API (e.g., using `curl`)
* terminate the running solution in an elegant way (i.e., by submitting a `shutdown` request rather than by killing the process).

For deployment, your solution must be containerised: 

* for each component of the solution that runs as separate service (solver, REST API, UI, etc.), use an image to start a container for the service (for docker images, provide Dockerfiles);
* if your application includes more than one service, provide a way to orchestrate the composite app (e.g., a docker compose script);

There should be no requirement to install anything on the host other than what is needed to provide an environment for executing containers (e.g., docker machine).   
All other dependencies should be handled within the images by a package manager corresponding to your code (maven, gradle, npm, etc.). 

For implementation, use python, java, scala, or another mainstream language if you can convincingly justify its use instead of one of aforementioned. 
 
Please provide performance measurements on a collection of problems of varying sizes.


### REST API
 
The following is a concise list of REST APIs for you to implement.
See the examples section below for their usage with `curl`.

* POST `/knapsack/tasks`  
content: `application/json` with knapsack problem specification, see below  
output: `json` with task status

* GET `/knapsack/tasks/<id>`  
output: `json` with task status (id, status, timestamp of last status update)

* GET `/knapsack/solutions/<id>`  
output: `json` with solution details

* GET `/knapsack/admin/tasks`  
output: `json` with a list of submitted, running, and completed tasks

* POST `/knapsack/admin/shutdown`  

You are free to extend the API (e.g., enable stopping or deleting tasks).

The `json` requests and responses should have the following formats:

```json
# problem
{
    "problem": {
        "capacity": # non-negative integer
        "weights": # array of non-negative integers
        "values": # array of non-negative integers, as many as weights
    }
}

# task
{
    "task": # ASCII string
    "status": # one of "submitted", "started", "completed"
    "timestamps": {
        "submitted": # unix/epoch time
        "started": # unix/epoch time or null if not started
        "completed": # unix/epoch time or null if not completed
    }
}


# solution
{
    "task": # ASCII string
    "problem": # problem as above
    "solution": {
        "items" : # array of integers (indices to weights and values)
        "time": # non-negative integer, time elapsed between the task was started and completed
    }
}

# tasks
{
    "tasks": {
        "submitted": # array of task as above
        "started": # array of task as above
        "completed": # array of task as above
    }
}
```

Please make sure your APIs follow these specifications, as we will be testing your solution with an automated suite of problems and solutions.


### Extended requirements

Ideally, your delivery would meet one or more of the following requirements:

* provide an elegant, intuitive graphical UI;
* enable one to upload a custom optimiser implementing an API you specify (e.g., a python script with a class or function with appropriate signature, an OSGi bundle, an executable binary or compilable code, etc.);
* enable authentication and authorisation, so that users can safely store their optimisation details in the system without exposing them to the public.
* make your service deployable to the cloud: either deploy it and provide urls to the running service, or have your build system provide a target for deploying the solution to a cloud provider of your choice.


### Example session

The following is a hypothetical session with a knapsack optimiser service, using docker compose to start a composite service, listening on port 6543:

```bash
$ cd <root directory of the service>
$ docker-compose up
...
$ curl -XPOST -H 'Content-type: application/json' http://localhost:6543/knapsack/tasks \
   -d '{"problem": {"capacity": 60, "weights": [10, 20, 33], "values": [10, 3, 30]}}'
{"task": "nbd43jhb", "status": "submitted", "timestamps": {"submitted": 1505225308, "started": null, "completed": null}}

$ curl -XGET -H http://localhost:6543/knapsack/tasks/nbd43jhb
{"task": "nbd43jhb", "status": "submitted", "timestamps": {"submitted": 1505225308, "started": 1505225320, "completed": null}}

$ curl -XGET -H http://localhost:6543/knapsack/solutions/nbd43jhb
# http status code 404 Not Found

$ curl -XGET -H http://localhost:6543/knapsack/tasks/nbd43jhb
{"task": "nbd43jhb", "status": "submitted", "timestamps": {"submitted": 1505225308, "started": 1505225320, "completed": 1505225521}}

$ curl -XGET -H http://localhost:6543/knapsack/solutions/nbd43jhb
{"task": "nbd43jhb", "problem": {...}, "solution": {"items": [0, 2]}, "time": 201}

$ curl -XGET http://localhost:6543/knapsack/admin/tasks
{"tasks": {"submitted": [...], "started": [...], "completed": [...]}}

$ curl -XPOST http://localhost:6543/knapsack/admin/shutdown
Service shutting down...

$ _
```

