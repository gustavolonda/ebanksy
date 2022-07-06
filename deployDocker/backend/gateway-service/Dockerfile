FROM openjdk:8-jdk
ARG ec_profile
ENV profile=$ec_profile
ADD gateway-service-0.0.1-SNAPSHOT.jar  /
ENTRYPOINT exec java -jar  gateway-service-0.0.1-SNAPSHOT.jar 
