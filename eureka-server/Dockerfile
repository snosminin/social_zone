FROM gradle:8.11.1-jdk17-alpine AS build
COPY --chown=gradle:gradle ./eureka-server /home/gradle/src
COPY --chown=gradle:gradle ./libs-versions /home/gradle/libs-versions
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-alpine
EXPOSE 8070
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/eureka-service.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/eureka-service.jar"]