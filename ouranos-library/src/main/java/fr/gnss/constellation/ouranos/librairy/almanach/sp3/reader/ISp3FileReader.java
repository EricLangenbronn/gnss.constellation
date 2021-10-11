package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import java.io.Closeable;
import java.io.IOException;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface ISp3FileReader extends Closeable {

	/**
	 * Returns the current offset in this file.
	 *
	 * @return the offset from the beginning of the file, in bytes, at which the
	 *         next read or write occurs.
	 * @exception IOException
	 *                if an I/O error occurs.
	 */
	long getFilePointer() throws IOException;

	/**
	 * Sets the file-pointer offset, measured from the beginning of this file, at
	 * which the next read or write occurs. The offset may be set beyond the end of
	 * the file. Setting the offset beyond the end of the file does not change the
	 * file length. The file length will change only by writing after the offset has
	 * been set beyond the end of the file.
	 *
	 * @param pos
	 *            the offset position, measured in bytes from the beginning of the
	 *            file, at which to set the file pointer.
	 * @exception IOException
	 *                if {@code pos} is less than {@code 0} or if an I/O error
	 *                occurs.
	 */
	void seek(long pos) throws IOException;

	/**
	 * Reads a line of text. A line is considered to be terminated by any one of a
	 * line feed ('\n'), a carriage return ('\r'), or a carriage return followed
	 * immediately by a linefeed.
	 * 
	 * @return A String containing the contents of the line, not including any
	 *         line-termination characters, or null if the end of the stream has
	 *         been reached or line contain 'EOF'
	 * @throws TechnicalException
	 */
	String readLine() throws IOException;

	/**
	 * Check number of occurrence %i.
	 * 
	 * @return true if %i appear more than two, false otherwise
	 */
	boolean isEndOfHeader();
}
