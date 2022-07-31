#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the configuration server to start on port $(getPort $CONFIG_SERVER_PORT)"
echo "********************************************************"
./wait-for-it.sh config-server:$CONFIG_SERVER_PORT -s -t 20;
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Starting gateway-server with Configuration Service via Eureka :  $EUREKA_SERVER_URI:$SERVER_PORT"
echo "Using Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIG_SERVER_URI                          \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/gateway-server/@project.build.finalName@.jar