package fr.gnss.constellation.ouranos.monitoring.tracktime;


import io.quarkus.arc.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;

@TrackTime
@Priority(20)
@Interceptor
@Slf4j
public class TrackTimeInterceptor {

  @AroundInvoke
  Object logInvocation(InvocationContext context) throws Exception {

    Object returnValue;
    long startTime = System.currentTimeMillis();
    try {
      returnValue = context.proceed();
    } finally {
      long elapsed = System.currentTimeMillis() - startTime;
      log.info("{}.{}, s'execute en {}ms", context.getClass(), context.getMethod(), elapsed);
    }

    return returnValue;
  }

}
