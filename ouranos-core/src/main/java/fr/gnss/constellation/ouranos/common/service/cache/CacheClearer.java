package fr.gnss.constellation.ouranos.common.service.cache;

import io.quarkus.cache.CacheManager;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class CacheClearer {

  private final CacheManager cacheManager;

  public void clearAllCaches() {
    for (String cacheName : cacheManager.getCacheNames()) {
      cacheManager.getCache(cacheName).ifPresent(cache -> cache.invalidateAll().await().indefinitely());
    }
  }
}
