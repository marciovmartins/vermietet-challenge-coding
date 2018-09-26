FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./build/libs/app.jar /app/app.jar

CMD java -jar /app/app.jar