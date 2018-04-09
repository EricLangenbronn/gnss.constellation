package fr.gnss.constellation.ouranos.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -4082624330729087266L;

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Log et lève une exception BusinessException avec un logger slf4j en entrée,
	 * un code d'erreur et un message.
	 * 
	 * @param p_logger
	 *            logger SLF4J
	 * @param p_businessExceptionCode
	 *            Code d'erreur (voir les constantes publiques dans
	 *            {@link BusinessException})
	 * @param p_messageTechnique
	 *            message
	 * @throws BusinessException
	 *             erreur métier
	 */
	public static void logAndThrow(final Logger p_logger, final String p_messageTechnique) throws BusinessException {
		LOGGER.error("Erreur métier : " + p_messageTechnique);
		throw new BusinessException(p_messageTechnique);
	}
}
