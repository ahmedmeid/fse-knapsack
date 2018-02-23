# Knapsack Optimizer Service


A solution for the homework challenge for full-stack software engineer position at Maersk Digital.


By Ahmed Mohamed Eid

## Usage

### Start the solution as a service on a local machine:

```sh
cd knapsack-optimizer-service
docker-compose up -d
```

### Interact with the solution using the REST API:

```sh
$ curl -XPOST -H 'Content-type: application/json' http://localhost:8080/knapsack/tasks \
   -d '{"problem": {"capacity": 60, "weights": [10, 20, 33], "values": [10, 3, 30]}}'
{"task":"9fd6d458-c024-4a10-9643-00588917ea08","status":"completed","timestamps":{"submitted":1519425117433,"started":1519425117557,"completed":1519425117560}}

$ curl http://localhost:8080/knapsack/tasks/9fd6d458-c024-4a10-9643-00588917ea08
{"task":"9fd6d458-c024-4a10-9643-00588917ea08","status":"completed","timestamps":{"submitted":1519425117433,"started":1519425117557,"completed":1519425117560}}

$ curl http://localhost:8080/knapsack/tasks/9fd6d458-c024-4a10-9643-00588917ea08
{"task": "nbd43jhb", "status": "submitted", "timestamps": {"submitted": 1505225308, "started": 1505225320, "completed": 1505225521}}

$ curl http://localhost:8080/knapsack/solutions/9fd6d458-c024-4a10-9643-00588917ea08
{"task": "nbd43jhb", "problem": {...}, "solution": {"items": [0, 2]}, "time": 201}

$ curl http://localhost:8080/knapsack/admin/tasks
{"tasks": {"submitted": [...], "started": [...], "completed": [...]}}

$ _
```
### Terminating the service:

```sh
$ curl -XPOST http://localhost:8080/knapsack/admin/shutdown
Service shutting down...
$ _
```

### Build the project
#### Prerequisites:

* Java 8
* Maven 3
* Mongodb 3.6


#### Building steps:

```sh
mvn clean
mvn package
```
