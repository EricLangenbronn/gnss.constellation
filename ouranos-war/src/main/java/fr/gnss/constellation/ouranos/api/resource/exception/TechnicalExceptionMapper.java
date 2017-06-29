package fr.gnss.constellation.ouranos.api.resource.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.xsd.response.error.Error;

@Provider
public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException> {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(TechnicalExceptionMapper.class);

	@Override
	public Response toResponse(TechnicalException p_exception) {

		Error l_erreur = new Error();
		l_erreur.setCode(520);
		l_erreur.setMessage(p_exception.getMessage());
		l_erreur.setTechnicalMessage(null);
		l_erreur.setStackTrace(ExceptionUtils.getFullStackTrace(p_exception));

		ResponseBuilder responseBuilder = Response.status(520).entity(l_erreur);
		return responseBuilder.build();
	}
}
