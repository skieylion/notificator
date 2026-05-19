## Stage 1: build with Maven
FROM eclipse-temurin:21-jdk AS build
WORKDIR /build

COPY mvnw ./
COPY .mvn ./.mvn
RUN chmod +x mvnw
COPY pom.xml ./
COPY common/pom.xml ./common/
COPY notify-ingest/pom.xml ./notify-ingest/
COPY notify-inbox/pom.xml ./notify-inbox/
COPY integration-tests/pom.xml ./integration-tests/

RUN ./mvnw -B dependency:go-offline -pl notify-ingest -am

COPY common ./common
COPY notify-ingest ./notify-ingest

RUN ./mvnw -B clean package -pl notify-ingest -am -DskipTests

## Stage 2: create the docker final image
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /build/notify-ingest/target/*.jar /app/application.jar

RUN chmod 775 /app \
  && chown -R 1001 /app \
  && chmod -R "g+rwX" /app \
  && chown -R 1001:root /app

EXPOSE 8080
USER 1001

ENTRYPOINT ["java", "-jar", "/app/application.jar"]
