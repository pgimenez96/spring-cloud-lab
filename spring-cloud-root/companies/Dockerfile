FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV CONFIG_SERVER_URL=""
ENV EUREKA_SERVER_URL=""
ENTRYPOINT ["java","-jar","/app.jar"]