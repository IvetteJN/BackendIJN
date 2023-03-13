FROM amazoncorretto:11-alpine-jdk
MAINTAINER IJN
COPY target/IJN-0.0.1-SNAPSHOT.jar ijn-app.jar
ENTRYPOINT ["java","-jar","/ijn-app.jar"]
EXPOSE 8080