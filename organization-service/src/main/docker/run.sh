#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}
echo "********************************************************"
echo "USing Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/organization-service/@project.build.finalName@.jar
