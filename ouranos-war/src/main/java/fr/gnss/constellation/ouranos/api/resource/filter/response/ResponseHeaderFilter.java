package fr.gnss.constellation.ouranos.api.resource.filter.response;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResponseHeaderFilter implements ContainerResponseFilter {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ResponseHeaderFilter.class);

	@Context
	UriInfo uri;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		// for angular project
		// headers.add("Access-Control-Allow-Origin",
		// uri.getBaseUri().toString());
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		headers.add("Access-Control-Allow-Headers",
				"X-Requested-With, X-Codingpedia, Cache-Control, Content-Type, Origin, Authorization, Accept, Client-Security-Token, Accept-Encoding");

	}
}
