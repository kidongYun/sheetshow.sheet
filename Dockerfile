FROM lpicanco/java11-alpine
VOLUME /tmp
EXPOSE 8200

ARG SPRING_PROFILES_ACTIVE
RUN echo "spring.profiles.active = ${SPRING_PROFILES_ACTIVE}"
ARG JAR_FILE=sheet-rest/build/libs/*.jar

ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]