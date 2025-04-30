FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV MONGO_HOST=""
ENV EUREKA_SERVER_URL=""
ENV BROKER_URL=""
ENTRYPOINT ["java","-jar","/app.jar"]