#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the gateway server to start on port $(getPort $GATEWAY_SERVER_PORT)"
echo "********************************************************"
./wait-for-it.sh gateway-server:$GATEWAY_SERVER_PORT -s -t 30;
echo "*******  Gateway Server has started"

echo "********************************************************"
echo "Waiting for the authentication service to start on port $(getPort $AUTHENTICATION_SERVICE_PORT)"
echo "********************************************************"
./wait-for-it.sh authentication-service:$AUTHENTICATION_SERVICE_PORT -s -t 30;
echo "*******  Authentication service has started"

echo "********************************************************"
echo "Starting License Server with Configuration Service via Eureka :  $EUREKA_SERVER_URI:$SERVER_PORT"
echo "USing Profile: $PROFILE"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIG_SERVER_URI                          \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/licensing-service/@project.build.finalName@.jar