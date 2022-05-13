FROM lpicanco/java11-alpine
ARG JAR_FILE=./sheet-rest/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]