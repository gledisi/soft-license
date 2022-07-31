#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the gateway server to start on port $(getPort $GATEWAY_SERVER_PORT)"
echo "********************************************************"
./wait-for-it.sh gateway-server:$GATEWAY_SERVER_PORT -s -t 25;
echo "*******  Gateway Server has started"


echo "********************************************************"
echo "Starting Authentication Service                           "
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIG_SERVER_URI                          \
     -Dspring.profiles.active=$PROFILE                                   \
     -jar /usr/local/authentication-service/@project.build.finalName@.jar