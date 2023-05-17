module ouranos.domain {
  requires java.desktop;
  requires org.apache.commons.lang3;
  requires org.slf4j;
  requires static lombok;

  requires ouranos.sp3;

  exports fr.gnss.constellation.ouranos.domain.satellite.visible;
  exports fr.gnss.constellation.ouranos.domain.satellite;
}
