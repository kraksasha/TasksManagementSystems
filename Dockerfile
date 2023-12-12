FROM openjdk:17

WORKDIR /app

ARG JAR_FILE=target/TasksManagementSystems-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} TasksManagementSystems-0.0.1-SNAPSHOT.jar
COPY Manifest.mf /app/Manifest.mf

CMD ["java", "-jar", "TasksManagementSystems-0.0.1-SNAPSHOT.jar", "-m","Manifest.mf"]