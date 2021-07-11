FROM openjdk:latest

ADD platforms/target/platforms-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java","-jar","app.jar" ]

EXPOSE 8080