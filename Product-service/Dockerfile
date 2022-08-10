FROM openjdk:18-alpine
ARG JAR_FILE=target/Product-service-0.0.1-SNAPSHOT.jar
WORKDIR /opr/app
COPY $JAR_FILE app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]