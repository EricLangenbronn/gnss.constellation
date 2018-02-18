package fr.gnss.constellation.ouranos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Aspect
public class Monitore implements Ordered {

	private static Logger LOGGER = LoggerFactory.getLogger("OURANOS_MONITORE");
	private int order;

	@Pointcut("within(fr.gnss.constellation.ouranos.service..*)")
	public void afficherClasseMethode() {};
	
	
	@Around("afficherClasseMethode()")
	public Object executer(final ProceedingJoinPoint joinpoint) throws Throwable {

		String nomMethode = joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName();

		Object returnValue;
		long start_time = System.currentTimeMillis();
		try {
			returnValue = joinpoint.proceed();
		} finally {
			long elapsed = System.currentTimeMillis() - start_time;
			LOGGER.info(nomMethode + " : temps d'execution (millis) : " + elapsed);
		}
		return returnValue;
	}

	@Override
	@Order(1)
	public int getOrder() {
		return this.order;
	}

	public void setOrder(final int order) {
		this.order = order;
	}
}