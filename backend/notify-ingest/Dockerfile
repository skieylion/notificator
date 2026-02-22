## Stage 1: build with Quarkus Mandrel builder image (from https://quarkus.io/guides/building-native-image)
FROM quay.io/quarkus/ubi9-quarkus-mandrel-builder-image:jdk-21 AS build
USER root
RUN microdnf install -y findutils && microdnf clean all
WORKDIR /build

COPY --chown=quarkus:quarkus mvnw ./
COPY --chown=quarkus:quarkus .mvn ./.mvn
RUN chmod +x mvnw
COPY --chown=quarkus:quarkus pom.xml ./
COPY --chown=quarkus:quarkus common/pom.xml ./common/
COPY --chown=quarkus:quarkus notify-ingest/pom.xml ./notify-ingest/
COPY --chown=quarkus:quarkus notify-inbox/pom.xml ./notify-inbox/
COPY --chown=quarkus:quarkus integration-tests/pom.xml ./integration-tests/

USER quarkus
RUN ./mvnw -B dependency:go-offline -pl notify-ingest -am

COPY --chown=quarkus:quarkus common ./common
COPY --chown=quarkus:quarkus notify-ingest ./notify-ingest

RUN ./mvnw -B clean package -pl notify-ingest -am -DskipTests -Dnative

## Stage 2: create the docker final image (ubi-minimal has curl-minimal for healthcheck)
FROM registry.access.redhat.com/ubi9/ubi-minimal:9.6
WORKDIR /work/
COPY --from=build /build/notify-ingest/target/*-runner /work/application
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

EXPOSE 8080
USER 1001

CMD ["./application"]
