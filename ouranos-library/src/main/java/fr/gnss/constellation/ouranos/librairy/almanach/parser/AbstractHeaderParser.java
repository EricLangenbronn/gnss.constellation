package fr.gnss.constellation.ouranos.librairy.almanach.parser;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public abstract class AbstractHeaderParser {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHeaderParser.class);

	private RandomAccessFile fileToParse;

	public AbstractHeaderParser(RandomAccessFile p_fr) {
		this.fileToParse = p_fr;
	}

	public String read(int offset, int length) throws TechnicalException {
		byte[] cbuf = new byte[length];

		try {
			this.fileToParse.seek(offset);
			int nbReadChar = this.fileToParse.read(cbuf);
			// TODO : vérification si -1 fin du fichier, on est dans le header
			// donc normalement pas de souci, mais au cas ou
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}

		return new String(cbuf);
	}

	public void close() {
		IOUtils.closeQuietly(this.fileToParse);
	}
}
