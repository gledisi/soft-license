#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom \
      -Dserver.port=$SERVER_PORT   \
      -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI  \
      -Dspring.profiles.active=$PROFILE                                   \
      -jar /usr/local/eureka-server/@project.build.finalName@.jar