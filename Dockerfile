FROM openjdk:8-jdk-alpine
ENTRYPOINT exec java -jar /app.jar
ADD target/original-timesheet-spring-boot-core-data-jpa-mvc-rest-1-0.0.1-SNAPSHOT.jar /app.jar 