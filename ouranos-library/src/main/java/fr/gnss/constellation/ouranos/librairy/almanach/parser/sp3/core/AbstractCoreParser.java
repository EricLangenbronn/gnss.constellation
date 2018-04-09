package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.core;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public abstract class AbstractCoreParser implements Closeable, ISp3CoreParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCoreParser.class);

	// -------------------- Propriétés de la classe --------------------

	private FileReader fileToParse;
	protected BufferedReader sp3Buffer;

	// ------------------------ Constructeur(s) ------------------------

	public AbstractCoreParser(FileReader p_fr) throws TechnicalException {
		fileToParse = p_fr;
		sp3Buffer = new BufferedReader(fileToParse);
		initParser();
	}

	// ----------------------- Methodes internes -----------------------

	/**
	 * Permet l'initialisation des classes enfants
	 * 
	 * @throws TechnicalException
	 * @throws NoSuchElementException
	 */
	public abstract void initParser() throws TechnicalException, NoSuchElementException;

	/**
	 * Reads a line of text. A line is considered to be terminated by any one of a
	 * line feed ('\n'), a carriage return ('\r'), or a carriage return followed
	 * immediately by a linefeed.
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

	// -------------------- Methodes de l'interface --------------------

	/**
	 * Closes the stream and releases any system resources associated with it. Once
	 * the stream has been closed, further read(), ready(), mark(), reset(), or
	 * skip() invocations will throw an IOException. Closing a previously closed
	 * stream has no effect.
	 * 
	 * @throws TechnicalException
	 */
	@Override
	public void close() {
		IOUtils.closeQuietly(fileToParse);
	}
}
