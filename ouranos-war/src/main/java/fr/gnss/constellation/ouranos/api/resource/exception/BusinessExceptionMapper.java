package fr.gnss.constellation.ouranos.api.resource.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionMapper.class);

	@Override
	public Response toResponse(BusinessException p_exception) {

		ResponseBuilder responseBuilder = Response.status(500);
		return responseBuilder.build();
	}

}
