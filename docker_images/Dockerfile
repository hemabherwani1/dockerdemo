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
# ----------------------------------------------------------------------------------------------------------------------

# ----------------------------------------------------------------------------------------------------------------------
# Create the docker image that we will run
# ----------------------------------------------------------------------------------------------------------------------
FROM openjdk:8-jre-alpine3.9

WORKDIR /opt/app

# copy the packaged jar file into our docker image
# COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar
COPY --from=builder /opt/app/target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

# set the startup command to execute the jar
# CMD ["java", "-jar", "demo.jar"]
ENTRYPOINT ["java", "-jar", "demo.jar"]
# ----------------------------------------------------------------------------------------------------------------------
