FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/pgadmiss-0.0.1-SNAPSHOT.jar pgadmiss.jar
EXPOSE 9092
ENTRYPOINT exec java $JAVA_OPTS -jar pgadmiss.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar pgadmiss.jar
