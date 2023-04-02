FROM eclipse-temurin:17-alpine

RUN addgroup -S ouranos && adduser -S ouranosuser -G ouranos

RUN mkdir /opt/ouranos
COPY ouranos-rest-api/target/ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar /opt/ouranos

RUN mkdir /opt/ouranos/config
RUN touch /opt/ouranos/config/application.properties
RUN echo -e '\
quarkus.http.cors=true\n\
orbit.data.authorized-download=true\n\
default.sp3.directory=/opt/ouranos/sp3\n\
default.products.access.ftp-server-name=gssc.esa.int\n\
default.products.access.epoch-directory=/gnss/products\n\
default.elevation.mask.elevation-mask-degree=10'\
>> /opt/ouranos/config/application.properties

RUN mkdir /opt/ouranos/sp3

RUN chown -R ouranosuser:ouranos /opt/ouranos

USER ouranosuser

WORKDIR "/opt/ouranos"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ouranos-rest-api-0.0.0-SNAPSHOT-runner.jar ${0} ${@}"]
