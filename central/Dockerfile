FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app


COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]