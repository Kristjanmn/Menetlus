FROM openjdk:17-jdk
WORKDIR /workspace/app/backend

COPY gradle gradle
COPY gradlew .
COPY src src
COPY build.gradle .
COPY settings.gradle .

RUN microdnf install findutils

ENTRYPOINT ["./gradlew","bootRun"]