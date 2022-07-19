#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/configserver/@project.build.finalName@.jar
