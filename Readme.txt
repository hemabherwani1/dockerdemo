Deploying image to docker:

Steps as followed for deploying simple springboot application:

1. Install docker

2. Create a sample Springboot application

3. Use maven command "mvn spring-boot:run", to run the application (for local testing)

4. Test it using curl

5. Create Dockerfile (as attached)

6. Build image using docker build command : docker build -t demo:V1.0 .

7. check images : docker images

8. image is available, run it for dev environment using environment variable:
docker run -e ENV_VALUE=dev -p 8080:8080 demo:V1.0

9 Container is created and running, run the application from terminal : curl "http://localhost:8080/"
output: Welcome to dev  environment


Accessing db:

Assuming docker is installed:

1. Run an existing MySQL image from docker hub as container:
  docker run --name=demosql -p3306:3306 -e MYSQL_DATABASE=demo_db -e MYSQL_USER=hema -e MYSQL_PASSWORD=demopwd -d mysql/mysql-server:5.7.34

2. Connect to mysql:
    -> docker exec -it demosql /bin/bash
    -> bash-4.2# mysql -u hema -p

    once connected verify database and create table

3. Update springboot application, add entity class, repository class for some crud operations

4. update application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/demo_db
    spring.datasource.username=hema
    spring.datasource.password=demopwd

    spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect

5. Build image using docker build command : docker build -t demo:V1.1 .

6. check images : docker images

7. image is available, run it for dev environment using environment variable:
docker run -e ENV_VALUE=dev -p 8080:8080 demo:V1.1

8. Container is created and running, run the application from terminal : curl "http://localhost:8080/"
   output: Welcome to dev  environment

9. curl "http://localhost:8080/tasks"
Output:
[{"taskId":1,"title":"task1","start_date":"2021-04-26","due_date":"2021-04-27","status":"incompelete"}]%


Using Docker network:

Assuming:
1. springboot application is there with repository and entity, controller classes
2. docker is installed and running

Steps:
1. Create docker network: docker network create demo-mysql

2. Run the existing Mysql image as container on above network

3. docker container run --name demodb --network demo-mysql -p3306:3306 -e MYSQL_DATABASE=demo_db -e MYSQL_USER=hema -e MYSQL_PASSWORD=demopwd -d mysql/mysql-server:5.7.34

4. Connect to mysql:
    -> docker exec -it demodb /bin/bash
    -> bash-4.2# mysql -u hema -p

    once connected verify database and create table

5. update application.properties:
    spring.datasource.url=jdbc:mysql://demodb:3306/demo_db
    spring.datasource.username=hema
    spring.datasource.password=demopwd

    spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect

5. Build image using docker build command : docker build -t demo:V1.2 .

6. Run it as container on the demo-mysql network.
docker run --network demo-mysql --name devcontainer -e ENV_VALUE=staging -p 8083:8080 demo:V1.2

7. Test the application:
curl "http://localhost:8083/"
   output: Welcome to staging  environment

8. curl "http://localhost:8083/tasks"
Output:
[{"taskId":1,"title":"task1","start_date":"2021-04-26","due_date":"2021-04-27","status":"incompelete"}]%


With compose file (without docker network)
Assuming:
1. springboot application is there with repository, entity, controller classes
2. docker is installed and running

Steps:
1. Create docker compose yml file.
2. Update application.properties file as:

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect

3. execute: docker-compose up --build from terminal

4. once application is started, test it:

hemabherwani@HemaBherwanis-MacBook-Pro demo % curl "https://localhost:8080"
curl: (35) error:1400410B:SSL routines:CONNECT_CR_SRVR_HELLO:wrong version number
hemabherwani@HemaBherwanis-MacBook-Pro demo % curl "http://localhost:8080"
Welcome to dev  environment%

hemabherwani@HemaBherwanis-MacBook-Pro demo % curl "http://localhost:8080/greet/me"
Welcomeme to local  environment.%

hemabherwani@HemaBherwanis-MacBook-Pro demo % curl "http://localhost:8080/tasks"
{"timestamp":"2021-04-27T09:24:55.477+00:00","status":500,"error":"Internal Server Error","message":"","path":"/tasks"}%

hemabherwani@HemaBherwanis-MacBook-Pro demo % curl "http://localhost:8080/tasks"
[{"taskId":1,"title":"task1","start_date":"2021-04-26","due_date":"2021-04-27","status":"incompelete"}]%


------------



Pushing image to repo:
a. docker login
 privide your docker username and password

b. once login is succeeded: docker push hemabherwani/demo_demo-app:latest

image will be pushed

c. verify it under your credentials in docker hub

d. Once should have


b. save the docker image: docker save --output demo-export.tar demo

c. load the image: docker load -input demo-export.tar  (as the image is exported, we can share it and open it)


https://hub.docker.com/repository/docker/hemabherwani/demo

