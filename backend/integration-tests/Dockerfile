FROM maven:3.9-eclipse-temurin-21-alpine
WORKDIR /app

COPY pom.xml ./
COPY common/pom.xml ./common/
COPY notify-ingest/pom.xml ./notify-ingest/
COPY notify-inbox/pom.xml ./notify-inbox/
COPY integration-tests/pom.xml ./integration-tests/

RUN mvn -f pom.xml dependency:go-offline -pl integration-tests -am

COPY integration-tests ./integration-tests

CMD ["mvn", "-f", "pom.xml", "test", "-pl", "integration-tests", "-Dbase.url=http://notify-ingest:8080"]
