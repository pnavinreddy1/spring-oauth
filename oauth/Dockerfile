# base linux image containing JRE.
FROM openjdk:8-jdk-alpine
# configure Docker to run java -jar ... when container is started.
CMD java ${JAVA_OPTS} -jar oauthserver-0.0.1-SNAPSHOT.jar
# Copies the JAR from target location into the image
COPY target/oauthserver-0.0.1-SNAPSHOT.jar .