# To run oauth server
Type "mvn exec:java" to run application. To package and run the application, type "mvn package"

# Deploying service using Docker
First step is to create a Dockerfile which contains instructions to build the image.
Dockerfile should be in root directory of the project.

To create the image using below command:
$ docker build -t oauth-service .

To list image: 
$ docker image ls

To run a docker container:
$ docker run -p 8081:80 oauth-service

To list docker containers: 
$ docker container ls

Note: you can use docker maven plugin to build and run docker images too. 




