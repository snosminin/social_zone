FROM gradle:8.11.1-jdk17-alpine AS build
COPY --chown=gradle:gradle ./gateway /home/gradle/src
COPY --chown=gradle:gradle ./libs-versions /home/gradle/libs-versions
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-alpine
EXPOSE 8181
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/gateway.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/gateway.jar"]