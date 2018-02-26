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

#### Register 
```sh
$ curl -H 'Content-type: application/json' -XPOST http://localhost:8080/users/sign-up -d '{
	"username" : "user1",
	"password" : "secretP@ssw0rd"
}'
```

#### Login
```sh
$ curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "user1",
    "password": "secretP@ssw0rd"
}' http://localhost:8080/login

HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg
Content-Length: 0
Date: Mon, 26 Feb 2018 17:28:36 GMT
```

Authentication token should be sent in all requests:

```sh
$ curl -XPOST -H 'Content-type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg' \
http://localhost:8080/knapsack/tasks \
-d '{"problem": {"capacity": 60, "weights": [10, 20, 33], "values": [10, 3, 30]}}'

{"task":"aff270fa-1c29-4b2e-96fb-ef6d6adaf31f","status":"started","timestamps":{"submitted":1519666287,"started":1519666288,"completed":null}}

$ curl http://localhost:8080/knapsack/tasks/aff270fa-1c29-4b2e-96fb-ef6d6adaf31f \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'

{"task":"aff270fa-1c29-4b2e-96fb-ef6d6adaf31f","status":"completed","timestamps":{"submitted":1519666287,"started":1519666288,"completed":1519666288}}

$ curl http://localhost:8080/knapsack/solutions/aff270fa-1c29-4b2e-96fb-ef6d6adaf31f \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'

{"task":"aff270fa-1c29-4b2e-96fb-ef6d6adaf31f","problem":{"values":[10,3,30],"weights":[10,20,33],"capacity":60},"solution":{"time":1,"items":[0,2]}}

$ _
```
### Terminating the service

```sh
$ curl -XPOST http://localhost:8080/knapsack/admin/shutdown \
-H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTUyMDUzMDExNn0.02oNZR0HHDhAiNu8-ayXjUbBX-K6uhUAcjgkPbIG1uNrZYBgEXpAA-fGa7rCvba4WyfvGd5nN-ueA0P4xXr_yg'
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
