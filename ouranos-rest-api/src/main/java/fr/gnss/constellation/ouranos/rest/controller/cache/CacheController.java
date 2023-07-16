package fr.gnss.constellation.ouranos.rest.controller.cache;

import fr.gnss.constellation.ouranos.common.service.cache.CacheClearer;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/api/cache")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@RequiredArgsConstructor
public class CacheController {

  private final CacheClearer cacheClearer;

  @POST
  @Path("/clear-all")
  @Timed(value = "clear-all-caches", description = "A measure how long it takes to perform deletion all caches.")
  @Counted(value = "clear-all-caches", description = "A measure how many times is perform deletion all caches.")
  public void clearAllCaches() {
    cacheClearer.clearAllCaches();
  }
}
