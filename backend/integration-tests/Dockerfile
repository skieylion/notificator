FROM maven:3.9-eclipse-temurin-21-alpine
WORKDIR /app

COPY pom.xml ./pom.xml
COPY common ./common
COPY notification-router ./notification-router
COPY integration-tests ./integration-tests

RUN mvn -f pom.xml dependency:go-offline -pl integration-tests -am

CMD ["mvn", "-f", "pom.xml", "test", "-pl", "integration-tests", "-Dbase.url=http://notification-router:8080"]
