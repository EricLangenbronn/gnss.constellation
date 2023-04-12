## Stage 1 : build with maven builder image
FROM maven:3.8-eclipse-temurin-17-alpine as builder

COPY . /usr/src/ouranos/

RUN mvn -f /usr/src/ouranos/pom.xml clean package -Douranos.parallel-processing.enabled=true


## Stage 2 : create the docker final image
FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S ouranos && adduser -S ouranosuser -G ouranos

RUN mkdir /opt/ouranos
COPY --from=builder /usr/src/ouranos/ouranos-rest-api/target/*-runner.jar /opt/ouranos

RUN mkdir /opt/ouranos/config
RUN touch /opt/ouranos/config/application.properties
RUN echo -e '\
quarkus.http.cors=true\n\
sp3.authorized-download=true\n\
default.sp3.directory=/opt/ouranos/sp3\n\
default.products.access.ftp-server-name=igs.ign.fr\n\
default.products.access.epoch-directory=/pub/igs/products\n\
default.elevation.mask.elevation-mask-degree=10'\
>> /opt/ouranos/config/application.properties

RUN mkdir /opt/ouranos/sp3
RUN chown -R ouranosuser:ouranos /opt/ouranos

RUN mkdir /var/log/ouranos
RUN chown -R ouranosuser:ouranos /var/log/ouranos


USER ouranosuser

WORKDIR "/opt/ouranos"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar ${0} ${@}"]
