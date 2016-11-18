FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD RegistrationService:0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","/app.jar"]
EXPOSE 8761 8761