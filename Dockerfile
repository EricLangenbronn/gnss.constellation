## Stage 1 : build with maven builder image
FROM maven:3.9.1-eclipse-temurin-20-alpine as builder

COPY . /usr/src/ouranos/

RUN mvn -f /usr/src/ouranos/pom.xml clean package -Douranos.parallel-processing.enabled=true


## Stage 2 : create the docker final image
FROM eclipse-temurin:20-jre-alpine

RUN addgroup -S ouranos && adduser -S ouranosuser -G ouranos

RUN mkdir /opt/ouranos
COPY --from=builder /usr/src/ouranos/ouranos-rest-api/target/*-runner.jar /opt/ouranos

RUN mkdir /opt/ouranos/config
RUN touch /opt/ouranos/config/application.properties
RUN echo -e '\
sp3.authorized-download=true\n\
default.sp3.directory=/opt/ouranos/sp3\n\
default.products.access.ftp-server-name=igs.ign.fr\n\
default.products.access.epoch-directory=/pub/igs/products\n\
default.elevation.mask.elevation-mask-degree=10\n\
\n\
quarkus.micrometer.binder-enabled-default=true\n\
quarkus.micrometer.binder.http-server.ignore-patterns='/q.*'\n\
quarkus.http.cors=true\n\
quarkus.log.file.enable=true\n\
quarkus.log.file.format=%d{HH:mm:ss.SSS} %-5p [%c{2.}]] (%t) %s%e%n\n\
quarkus.log.file.level=INFO\n\
quarkus.banner.path=banner.txt\n\
\n\
quarkus.jolokia.path=/jolokia\n\
quarkus.http.root-path=/'\
>> /opt/ouranos/config/application.properties

RUN mkdir /opt/ouranos/sp3
RUN chown -R ouranosuser:ouranos /opt/ouranos

RUN mkdir /var/log/ouranos
RUN chown -R ouranosuser:ouranos /var/log/ouranos


USER ouranosuser

WORKDIR "/opt/ouranos"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar ${0} ${@}"]
