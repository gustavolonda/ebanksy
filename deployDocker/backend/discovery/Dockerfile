FROM openjdk:8-jdk
ARG ec_profile
ENV profile=$ec_profile
ADD discovery-service-0.0.1-SNAPSHOT.jar  /
ENTRYPOINT exec java -jar  discovery-service-0.0.1-SNAPSHOT.jar 
EXPOSE 8761
