package fr.gnss.constellation.ouranos.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Aspect
@ConfigurationProperties("interceptor-track-time")
@Component
@Slf4j(topic = "OURANOS_TRACK_TIME")
public class TrackTime {

    @Pointcut("execution(* *.*(..)) && within(fr.gnss.constellation.ouranos.service..*)")
    public void interceptFunctionExecutionTime() {
    }


    @Around("interceptFunctionExecutionTime()")
    public Object calculationTime(final ProceedingJoinPoint joinpoint) throws Throwable {

        String nomMethode = joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName();

        Object returnValue;
        long start_time = System.currentTimeMillis();
        try {
            returnValue = joinpoint.proceed();
        } finally {
            long elapsed = System.currentTimeMillis() - start_time;
            log.info("{}, s'execute en {} milliseconde", nomMethode, elapsed);
        }
        return returnValue;
    }

}