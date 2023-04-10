package fr.gnss.constellation.ouranos.api;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {

  private static final Logger log = Logger.getLogger("ListenerBean");

  void onStart(@Observes StartupEvent ev) {
    log.info("Ouranos is starting...");
  }

  void onStop(@Observes ShutdownEvent ev) {
    log.info("Ouranos is shutting down...");
  }

}
