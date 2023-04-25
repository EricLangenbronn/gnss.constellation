package fr.gnss.constellation.ouranos.common.service.parallel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelRunnableService {


  // -------------------- Attributs --------------------

  private static final int NB_THREADS = 10;
  private static final long NB_MILLIS_TIMEOUT = 30000; // 30secondes
  private final int nbThreads;
  private final long nbMillisTimeout;

  // -------------------- Services --------------------

  private ExecutorService executorService;

  // ------------------------ Constructeur(s) ------------------------

  public ParallelRunnableService() {
    this(NB_THREADS, NB_MILLIS_TIMEOUT);
  }


  public ParallelRunnableService(int nbThreads, long nbMillisTimeout) {

    this.nbThreads = nbThreads;
    this.nbMillisTimeout = nbMillisTimeout;

    executorService = Executors.newFixedThreadPool(nbThreads);
  }


  public Collection<Runnable> executeTasks(Collection<Runnable> runnables) {

    if (executorService.isTerminated()) {
      executorService = Executors.newFixedThreadPool(nbThreads);
    }

    Optional.ofNullable(runnables).stream().flatMap(Collection::stream)
        .forEach(executorService::execute);
    executorService.shutdown();

    Collection<Runnable> tasksNotExecuted = new ArrayList<>();
    try {
      if (!executorService.awaitTermination(nbMillisTimeout, TimeUnit.MILLISECONDS)) {
        tasksNotExecuted = executorService.shutdownNow();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
          log.error("Wait a while for tasks to respond to being cancelled");
        }
      }
    } catch (InterruptedException e) {
      tasksNotExecuted = executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }

    return tasksNotExecuted;
  }

}
