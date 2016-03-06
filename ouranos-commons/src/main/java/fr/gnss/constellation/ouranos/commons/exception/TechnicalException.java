package fr.gnss.constellation.ouranos.commons.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TechnicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4294507265833396757L;
	
	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(TechnicalException.class);

	public TechnicalException() {
		super();
	}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}
}
