# Knapsack Optimizer Service

### Technology Stack

Component         | Technology
---               | ---
REST              | Spring Framework (Java 8)
Persistance	      | Spring Data for Mongo
Database          | mongo DB
Build Tool        | Maven(Java)

## Architecture

![Application_Architecture](knapsack-optimizer-service/webresources/img/AppArch.jpg)

### Main components

* Spring MVC REST controller
* Dynamic Programming Knapsack Optimizer
* Spring data mongo repository
* mongo DB NoSQL database

## Code Structure

```bash
knapsack-optimizer-service
└──[src]
│    └──[main]
│    │    └──[java] # Java classes
│    │    └──[webpapp] # Web resources for UI
│    │    └──[resources] # Configuration files
│    │       │application.yml # Main configution file
│    └──[test]
│          └──[java] # Test cases
│          └──[resources] # Test resources
│              │application-test.yml # Test configuration file
│ [target] # build destination directory
│  docker-compose.yml # Docker compose file
│  Dockerfile # Docker file for the application
│  pom.xml # Build file
```

## Submit Task

![Sequence_Diagram](knapsack-optimizer-service/webresources/img/SequenceDiagram.jpg)

## API documentation

[REST API documentation](https://bitbucket.org/ahmedmeid/fse-knapsack/src/90b614813408a45954a0002caed728634b849971/REST_API_doc.md?fileviewer=file-view-default)

