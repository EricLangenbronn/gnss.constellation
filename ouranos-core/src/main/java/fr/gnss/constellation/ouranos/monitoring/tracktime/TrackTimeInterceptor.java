package fr.gnss.constellation.ouranos.monitoring.tracktime;


import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
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
