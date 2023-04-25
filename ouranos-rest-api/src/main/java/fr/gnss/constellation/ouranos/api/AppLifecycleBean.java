package fr.gnss.constellation.ouranos.api;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AppLifecycleBean {

  void onStart(@Observes StartupEvent ev) {
    log.info("Ouranos is starting...");
  }

  void onStop(@Observes ShutdownEvent ev) {
    log.info("Ouranos is shutting down...");
  }

}
