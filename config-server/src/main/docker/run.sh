#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the eureka server to start on port $(getPort $EUREKA_SERVER_PORT)"
echo "********************************************************"
./wait-for-it.sh eureka-server:$EUREKA_SERVER_PORT -s -t 15;
echo "******* Eureka Server has started"
echo "********************************************************"
echo "Starting Configuration Service with Eureka Endpoint:  $EUREKA_SERVER_URI";
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI -jar /usr/local/config-server/@project.build.finalName@.jar