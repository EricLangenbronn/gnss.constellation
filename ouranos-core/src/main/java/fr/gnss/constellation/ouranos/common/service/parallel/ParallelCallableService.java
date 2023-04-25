package fr.gnss.constellation.ouranos.common.service.parallel;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelCallableService<D, T extends Callable<D>> {

  private static final int NB_THREADS = 10;
  private static final long NB_MILLIS_TIMEOUT = 30000; // 30secondes
  private final int nbThreads;
  private final long nbMillisTimeout;

  // -------------------- Services --------------------

  private ExecutorService executorService;

  // ------------------------ Constructeur(s) ------------------------

  public ParallelCallableService() {
    this(NB_THREADS, NB_MILLIS_TIMEOUT);
  }


  public ParallelCallableService(int nbThreads, long nbMillisTimeout) {

    this.nbThreads = nbThreads;
    this.nbMillisTimeout = nbMillisTimeout;

    executorService = Executors.newFixedThreadPool(nbThreads);
  }


  public Collection<Future<D>> executeTasks(Collection<T> callables) {

    if (executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(nbThreads);
    }

    Collection<Future<D>> runningTasks = Optional.ofNullable(callables).stream().flatMap(Collection::stream)
        .map(executorService::submit)
        .toList();
    executorService.shutdown();

    try {
      if (!executorService.awaitTermination(nbMillisTimeout, TimeUnit.MILLISECONDS)) {
        executorService.shutdownNow();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
          log.error("Wait a while for tasks to respond to being cancelled");
        }
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }

    return Optional.of(runningTasks).stream().flatMap(Collection::stream)
        .filter(Future::isDone)
        .toList();
  }
}
