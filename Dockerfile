# ----------------------------------------------------------------------------------------------------------------------
# Build the application and create the JAR file
# ----------------------------------------------------------------------------------------------------------------------
FROM openjdk:8 AS builder

WORKDIR /opt/app

COPY .mvn .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml
COPY src src

RUN ./mvnw package

FROM java:8-jdk-alpine

COPY ./target/demo-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

EXPOSE 8080

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]