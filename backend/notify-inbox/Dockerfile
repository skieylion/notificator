# Build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /build

COPY mvnw ./
COPY .mvn ./.mvn
COPY pom.xml ./
COPY common ./common
COPY notify-inbox ./notify-inbox
COPY notification-router/pom.xml ./notification-router/
COPY integration-tests/pom.xml ./integration-tests/

RUN ./mvnw -B dependency:go-offline -pl notify-inbox -am
RUN ./mvnw -B package -pl notify-inbox -am -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN apk add --no-cache curl

COPY --from=build /build/notify-inbox/target/notify-inbox-*.jar ./app.jar

EXPOSE 8082

HEALTHCHECK --interval=10s --timeout=3s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "./app.jar"]
