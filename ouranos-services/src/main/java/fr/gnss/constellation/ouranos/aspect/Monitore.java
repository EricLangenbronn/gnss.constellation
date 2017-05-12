package fr.gnss.constellation.ouranos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

public class Monitore implements Ordered {

	private static Logger LOGGER = LoggerFactory.getLogger("OURANOS_MONITORE");
	private int order;

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
	public int getOrder() {
		return this.order;
	}

	public void setOrder(final int order) {
		this.order = order;
	}
}