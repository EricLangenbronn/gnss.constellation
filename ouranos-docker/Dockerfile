## Stage 1 : build with maven builder image
FROM maven:3.9.1-eclipse-temurin-20-alpine as builder

COPY . /usr/src/ouranos/

RUN ls /usr/src/ouranos # for debug mode
RUN ls /usr/src/ouranos/ouranos-core # for debug mode

RUN mvn -f /usr/src/ouranos/pom.xml clean package -Douranos.parallel-processing.enabled=true


## Stage 2 : create the docker final image
FROM eclipse-temurin:20-jre-alpine

RUN addgroup -S ouranos && adduser -S ouranosuser -G ouranos

RUN mkdir /opt/ouranos
COPY --from=builder /usr/src/ouranos/ouranos-rest-api/target/*-runner.jar /opt/ouranos
RUN ls /opt/ouranos # for debug mode

RUN mkdir /opt/ouranos/config
COPY --from=builder /usr/src/ouranos/ouranos-docker/conf/application.properties /opt/ouranos/config
RUN ls /opt/ouranos/config # for debug mode

RUN mkdir /opt/ouranos/sp3
RUN chown -R ouranosuser:ouranos /opt/ouranos

RUN mkdir /var/log/ouranos
RUN chown -R ouranosuser:ouranos /var/log/ouranos


USER ouranosuser

WORKDIR "/opt/ouranos"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar ${0} ${@}"]
