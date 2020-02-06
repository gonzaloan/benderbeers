FROM openjdk:8-jdk-alpine
VOLUME /tmp

ADD target/bender-0.0.1-SNAPSHOT.jar /opt/bender-api/
EXPOSE 8080
WORKDIR /opt/bender-api/

CMD ["java", "-jar", "bender-0.0.1-SNAPSHOT.jar"]