package fr.gnss.constellation.ouranos.api.resource.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER_AUDIT = LoggerFactory.getLogger("OURANOS_AUDIT");
	private final static Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		MDC.put("start_time", String.valueOf(System.currentTimeMillis()));
		MDC.put("url", requestContext.getUriInfo().getAbsolutePath().toString());
		MDC.put("method", requestContext.getMethod());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		String stTime = MDC.get("start_time");
		if (null == stTime || stTime.length() == 0) {
			return;
		}
		long startTime = Long.parseLong(stTime);
		long executionTime = System.currentTimeMillis() - startTime;
		MDC.put("execution_time", String.valueOf(executionTime));
		MDC.put("http_status", String.valueOf(responseContext.getStatus()));

		LOGGER.debug("Total request execution time : {} milliseconds", executionTime);
		LOGGER_AUDIT.info("");

		// clear the context on exit
		MDC.clear();
	}
}
