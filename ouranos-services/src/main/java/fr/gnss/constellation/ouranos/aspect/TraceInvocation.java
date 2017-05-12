package fr.gnss.constellation.ouranos.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

public class TraceInvocation implements Ordered {

	private static Logger LOGGER = LoggerFactory.getLogger("OURANOS_TRACE_INVOCATION");
	private int order;

	public Object afficherTrace(final ProceedingJoinPoint joinpoint) throws Throwable {
		String nomMethode = joinpoint.getTarget().getClass().getSimpleName() + "." + joinpoint.getSignature().getName();

		final Object[] args = joinpoint.getArgs();
		final StringBuffer sb = new StringBuffer();
		sb.append(joinpoint.getSignature().toString());
		sb.append(" avec les parametres : (");

		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		LOGGER.info("Debut methode : " + sb);

		Object obj = null;
		try {
			obj = joinpoint.proceed();
		} finally {
			LOGGER.info("Fin methode :  " + nomMethode + " retour=" + obj);
		}

		return obj;
	}

	public void afficherDebutTrace(final JoinPoint joinpoint) throws Throwable {
		final Object[] args = joinpoint.getArgs();
		final StringBuffer sb = new StringBuffer();
		sb.append(joinpoint.getSignature().toString());
		sb.append(" avec les parametres : (");

		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		LOGGER.info("Debut methode : " + sb);
	}

	public void afficherFinTrace(final StaticPart staticPart, final Object result) throws Throwable {
		String nomMethode = staticPart.getSignature().toLongString();
		LOGGER.info("Fin methode :  " + nomMethode + " retour=" + result);
	}

	@Override
	public int getOrder() {
		return this.order;
	}

	public void setOrder(final int order) {
		this.order = order;
	}
}