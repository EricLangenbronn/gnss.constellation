package fr.gnss.constellation.ouranos.librairy.almanach.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public abstract class AbstractCoreParser {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory
			.getLog(AbstractCoreParser.class);

	private FileReader fileToParse;
	protected BufferedReader sp3Buffer;

	public AbstractCoreParser(FileReader p_fr) throws TechnicalException {
		fileToParse = p_fr;
		sp3Buffer = new BufferedReader(fileToParse);
		initParser();
	}

	/**
	 * Permet l'initialisation des classes enfants
	 * 
	 * @throws TechnicalException
	 * @throws NoSuchElementException
	 */
	public abstract void initParser() throws TechnicalException,
			NoSuchElementException;

	/**
	 * Reads a line of text. A line is considered to be terminated by any one of
	 * a line feed ('\n'), a carriage return ('\r'), or a carriage return
	 * followed immediately by a linefeed.
	 * 
	 * @return A String containing the contents of the line, not including any
	 *         line-termination characters, or null if the end of the stream has
	 *         been reached
	 * @throws TechnicalException
	 */
	public String readLine() throws TechnicalException {
		try {
			String line = sp3Buffer.readLine();
			if (Objects.isNull(line))
				throw new NoSuchElementException();

			return line;
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}
	}

	/**
	 * Closes the stream and releases any system resources associated with it.
	 * Once the stream has been closed, further read(), ready(), mark(),
	 * reset(), or skip() invocations will throw an IOException. Closing a
	 * previously closed stream has no effect.
	 * 
	 * @throws TechnicalException
	 */
	public void close() throws TechnicalException {
		try {
			fileToParse.close();
		} catch (IOException e) {
			String message = "";
			throw new TechnicalException(message, e);
		}
	}
}
