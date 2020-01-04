FROM openjdk:8-jdk-alpine

WORKDIR /

ADD back_end/target/back_end-1.0-SNAPSHOT.jar back_end-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD java -jar back_end-1.0-SNAPSHOT.jar
