package fr.gnss.constellation.ouranos.librairy.almanach.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public abstract class AbstractHeaderParser {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory
			.getLog(AbstractHeaderParser.class);

	private RandomAccessFile fileToParse;

	public AbstractHeaderParser(RandomAccessFile p_fr) {
		fileToParse = p_fr;
	}

	public String read(int offset, int length) throws TechnicalException {
		byte[] cbuf = new byte[length];

		try {
			fileToParse.seek(offset);
			int nbReadChar = fileToParse.read(cbuf);
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return new String(cbuf);
	}

	public void close() throws TechnicalException {
		try {
			fileToParse.close();
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}
	}
}
