FROM lpicanco/java11-alpine
VOLUME /tmp
EXPOSE 8200
ARG JAR_FILE=sheet-rest/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=local"]