module ouranos.core {
  requires arc;
  requires jakarta.cdi;
  requires jakarta.annotation;
  requires java.desktop;
  requires org.apache.commons.collections4;
  requires org.apache.commons.compress;
  requires org.apache.commons.io;
  requires org.apache.commons.lang3;
  requires org.apache.commons.net;
  requires org.mapstruct;
  requires org.slf4j;
  requires quarkus.arc;
  requires quarkus.cache;
  // requires quarkus.cache.runtime.spi;
  requires smallrye.config.core;
  requires static lombok;

  requires ouranos.domain;
  requires ouranos.sp3;

  exports fr.gnss.constellation.ouranos.common.network.ftp;
  exports fr.gnss.constellation.ouranos.common.network;
  exports fr.gnss.constellation.ouranos.common.service.cache;
  exports fr.gnss.constellation.ouranos.sp3.persitence;
  exports fr.gnss.constellation.ouranos.sp3.service;
}
