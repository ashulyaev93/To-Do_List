FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/todo-app.jar todo-app.jar
ENTRYPOINT ["java","-jar","/todo-app.jar"]
