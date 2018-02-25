![logo](knapsack-optimizer-service/webresources/img/maersk_logo.png)

---

# Knapsack Optimizer Service


A solution for the homework challenge for full-stack software engineer position at Maersk Digital.


By [Ahmed Mohamed Eid](ahmed.eid@gmx.com).

## Usage

### Starting the solution as a service on a local machine

Starting the solution on a local machine requires [Docker](https://www.docker.com/) to be installed


```sh
cd knapsack-optimizer-service
docker-compose up -d
```

### Interacting with the solution using the REST API

The solution exposes REST API's accessible on port 8080:

```sh
$ curl -XPOST -H 'Content-type: application/json' http://localhost:8080/knapsack/tasks \
   -d '{"problem": {"capacity": 60, "weights": [10, 20, 33], "values": [10, 3, 30]}}'
{"task":"9fd6d458-c024-4a10-9643-00588917ea08","status":"completed","timestamps":{"submitted":1519425117433,"started":1519425117557,"completed":1519425117560}}

$ curl http://localhost:8080/knapsack/tasks/9fd6d458-c024-4a10-9643-00588917ea08
{"task":"9fd6d458-c024-4a10-9643-00588917ea08","status":"completed","timestamps":{"submitted":1519425117433,"started":1519425117557,"completed":1519425117560}}

$ curl http://localhost:8080/knapsack/tasks/9fd6d458-c024-4a10-9643-00588917ea08
{"task": "9fd6d458-c024-4a10-9643-00588917ea08", "status": "submitted", "timestamps": {"submitted": 1505225308, "started": 1505225320, "completed": 1505225521}}

$ curl http://localhost:8080/knapsack/solutions/9fd6d458-c024-4a10-9643-00588917ea08
{"task": "9fd6d458-c024-4a10-9643-00588917ea08", "problem": {...}, "solution": {"items": [0, 2]}, "time": 201}

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

### Building the project

Run the following command to pull maven image and use it to build spring project:

```sh
docker run -it --rm --name knapsack-optimizer-service \
               -v "$HOME/.m2":/root/.m2 -v "$PWD":/usr/src/maven-project \
               -w /usr/src/maven-project maven:3.5.2-jdk-8 mvn clean package
```
