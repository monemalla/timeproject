FROM openjdk:8-jdk-alpine
ENTRYPOINT exec java -jar /app.jar
ADD target/*.jar /app.jar 