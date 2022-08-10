FROM openjdk:18-alpine
ARG JAR_FILE=target/Cartservice-0.0.1-SNAPSHOT.jar
WORKDIR /opr/app
COPY $JAR_FILE app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]