package fr.gnss.constellation.ouranos.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Aspect
@ConfigurationProperties("interceptor-trace-invocation")
@Component
@Slf4j(topic = "OURANOS_TRACE_INVOCATION")
public class TraceInvocation {

    @Pointcut("execution(* *.*(..)) && within(fr.gnss.constellation.ouranos.service..*)")
    public void interceptFunctionCall() {
    }

    @Before("interceptFunctionCall()")
    public void afficherDebutTrace(final JoinPoint joinpoint) throws Throwable {
        final StringBuffer sb = new StringBuffer();
        sb.append(joinpoint.getSignature().toString());

        log.info("Debut methode :  {}", sb);
    }

    @AfterReturning(pointcut = "interceptFunctionCall()", returning = "result")
    public void afficherFinTrace(final JoinPoint joinpoint, final Object result) throws Throwable {
        String nomMethode = joinpoint.getSignature().toLongString();
        log.info("Fin methode : {}", nomMethode);
    }

    @AfterThrowing(pointcut = "interceptFunctionCall()", throwing = "exception")
    public void afficherExceptionTrace(final JoinPoint joinpoint, final Exception exception) throws Throwable {
        String nomMethode = joinpoint.getSignature().toLongString();
        log.error("Exception durant la methode {}, {} ", nomMethode, exception.getMessage());
    }
}