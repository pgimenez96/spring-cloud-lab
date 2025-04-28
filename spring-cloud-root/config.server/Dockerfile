FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV EUREKA_SERVER_URL=null
ENTRYPOINT ["java", "-jar", "/app.jar"]