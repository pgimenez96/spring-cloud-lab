FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
# Argumentos para el build (pueden tener valores por defecto)
ARG JAR_FILE=target/*.jar
ARG APP_NAME=app
ARG APP_VERSION=1.0.0
# Convertir ARG a ENV para que estén disponibles en runtime
ENV APP_NAME=${APP_NAME}
ENV APP_VERSION=${APP_VERSION}
# Copiar el JAR (usando las variables ARG en el COPY)
COPY ${JAR_FILE} ${APP_NAME}-${APP_VERSION}.jar
# Usar las variables ENV en el ENTRYPOINT (sh -c → Permite expandir las variables ENV en el comando.)
ENTRYPOINT ["sh", "-c", "java -jar /${APP_NAME}-${APP_VERSION}.jar"]