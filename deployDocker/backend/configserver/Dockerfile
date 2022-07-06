FROM openjdk:8-jdk
ARG ec_profile
ENV profile=$ec_profile
ADD config-service-0.0.1-SNAPSHOT.jar  /
ENTRYPOINT exec java -jar  config-service-0.0.1-SNAPSHOT.jar 

