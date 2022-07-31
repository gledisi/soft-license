FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add curl && apk add bash
RUN mkdir -p /usr/local/gateway-server
ADD @project.build.finalName@.jar /usr/local/gateway-server/
ADD run.sh run.sh
RUN chmod +x run.sh
ADD wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
CMD ./run.sh
