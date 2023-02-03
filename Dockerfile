FROM amazoncorretto:11-alpine-jdk
MAINTAINER Kaxzark
COPY target/backend-0.0.1-SNAPSHOT.jar dockerfile-backend.jar
ENTRYPOINT ["java","-jar","/dockerfile-backend.jar"]