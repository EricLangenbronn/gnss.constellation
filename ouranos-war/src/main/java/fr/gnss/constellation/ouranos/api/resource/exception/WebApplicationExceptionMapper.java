package fr.gnss.constellation.ouranos.api.resource.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

	@Override
	public Response toResponse(WebApplicationException p_exception) {

		ResponseBuilder responseBuilder = Response.status(500);
		LOGGER.error(p_exception.getMessage());
		LOGGER.error(""+p_exception.getResponse().getStatus());
		return responseBuilder.build();
	}
}
