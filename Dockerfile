FROM amazoncorretto:11-alpine-jdk
MAINTAINER IJN
COPY target/IJN-0.0.1-SNAPSHOT ijn-app.jar
ENTRYPOINT ["java","-jar","/ijn-app.jar"]