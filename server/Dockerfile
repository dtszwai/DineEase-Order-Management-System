FROM amazoncorretto:17-alpine-jdk AS build

WORKDIR /app

COPY . /app

RUN ./mvnw package

FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY --from=build /app/target/server.jar /app/target/server.jar

ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG POSTGRES_DB
ARG SERVER_PORT

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "target/server.jar", \
  "--spring.profiles.active=docker", \
  "--server.port=${SERVER_PORT}", \
  "--spring.datasource.url=jdbc:postgresql://db:${POSTGRES_PORT}/${POSTGRES_DB}", \
  "--spring.datasource.username=${POSTGRES_USER}", \
  "--spring.datasource.password=${POSTGRES_PASSWORD}"]
