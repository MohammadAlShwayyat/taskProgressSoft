FROM openjdk:8

EXPOSE 8081

WORKDIR /

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} progressSoft.jar

ARG RESOURCES_FOLDER=src/main/resources/*

COPY ${RESOURCES_FOLDER} /resources/

ENTRYPOINT ["java" ,"-jar" ,"progressSoft.jar"]