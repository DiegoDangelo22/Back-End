FROM amazoncorretto:11-alpine-jdk
MAINTAINER DangeloDiego
COPY target/backend-0.0.1-SNAPSHOT.jar portfolio.jar
ENTRYPOINT ["java","-jar","/portfolio.jar"]