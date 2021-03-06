package fr.gnss.constellation.ouranos.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 4294507265833396757L;

	private static final Logger LOGGER = LoggerFactory.getLogger(TechnicalException.class);

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
