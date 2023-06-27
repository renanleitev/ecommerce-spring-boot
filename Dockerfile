FROM eclipse-temurin:17.0.7_7-jre-jammy
 
WORKDIR /app
 
COPY pom.xml .
RUN mvn dependency:go-offline
 
COPY src src
RUN mvn clean package
 
COPY /ecommerce-spring-boot/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]