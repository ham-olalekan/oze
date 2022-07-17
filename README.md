## Introduction

This project is a backend application of built for oze java developer assessment.

It helps hospitals to manage patients information.

## Assumptions and Decisions

- A new staff will be created everytime the application is restarted.


## Setup

Make sure to follow all these steps exactly as explained below. Do not miss any steps or you won't be able to run this application.

### Start the Server with Docker
Run the following Docker commands in the project root directory to run the application
- `docker build -t oze-service . `
- 
then run

- `docker run -p 8080:8080 oze-service` 
NB: you can bind to any other free port incase 8080 is already in use on your machine

OR 

### Install Maven

### Install the Dependencies
install project dependencies by running this command in the project 
- `mvn clean compile`
- 
then run

- `mvn spring-boot:run`




    

This will launch the Node server on port 8080. If that port is busy, you can set a different point in application.properties.

- import the postman collection with this link
  https://www.getpostman.com/collections/a2389f0b19892e919271

### NB
All API endpoints requires an header named *staffID* failure to provide this header will result in to 403 HTTP response
