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

import fr.gnss.constellation.ouranos.api.resource.filter.response.ResponseHeaderFilter;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ResponseHeaderFilter.class);

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MDC.put("start-time", String.valueOf(System.currentTimeMillis()));

		LOGGER.debug("Entering in Resource : /{} ", requestContext.getUriInfo().getPath());
		LOGGER.debug("Method Name : {} ", resourceInfo.getResourceMethod().getName());
		LOGGER.debug("Class : {} ", resourceInfo.getResourceClass().getCanonicalName());

	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		String stTime = MDC.get("start-time");
		if (null == stTime || stTime.length() == 0) {
			return;
		}
		long startTime = Long.parseLong(stTime);
		long executionTime = System.currentTimeMillis() - startTime;
		LOGGER.debug("Total request execution time : {} milliseconds", executionTime);

		// clear the context on exit
		MDC.clear();
	}
}
