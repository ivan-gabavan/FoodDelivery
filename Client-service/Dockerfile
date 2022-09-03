FROM openjdk:18-alpine
ARG JAR_FILE=target/Client-service-0.0.1-SNAPSHOT.jar
WORKDIR /opr/app
COPY $JAR_FILE app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"]