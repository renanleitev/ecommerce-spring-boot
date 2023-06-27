# Build stage

FROM maven:3-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage

FROM openjdk:21-ea-17-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]