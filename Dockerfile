FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY gradlew gradlew
COPY gradle/wrapper gradle/wrapper
RUN ./gradlew build

COPY build.gradle build.gradle
COPY src src
RUN ./gradlew build -x test -x integrationTest -x acceptanceTest

EXPOSE 8080

CMD ["./gradlew", "bootRun", "--no-daemon"]