package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.header;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public abstract class AbstractHeaderParser implements Closeable, ISp3HeaderParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHeaderParser.class);

	// -------------------- Propriétés de la classe --------------------

	private RandomAccessFile fileToParse;

	// ------------------------ Constructeur(s) ------------------------

	public AbstractHeaderParser(RandomAccessFile p_fr) {
		this.fileToParse = p_fr;
	}

	// ----------------------- Methodes internes -----------------------

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

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void close() {
		IOUtils.closeQuietly(this.fileToParse);
	}
}
