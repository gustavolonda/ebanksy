FROM openjdk:8-jdk
EXPOSE 8010
ARG ec_profile
ENV profile=$EC_profile
ADD user-service-0.0.1-SNAPSHOT.jar /
ENTRYPOINT exec java -jar  user-service-0.0.1-SNAPSHOT.jar

