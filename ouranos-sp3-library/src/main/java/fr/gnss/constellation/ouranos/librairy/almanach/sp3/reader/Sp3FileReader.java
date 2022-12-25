package fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class Sp3FileReader implements ISp3FileReader {

    // -------------------- Propriétés de la classe --------------------

    private static final String MODE = "r";

    private static final String MODULO_I = "%i";
    private static final String COMMENT = "/*";
    private static final String EOF = "EOF";

    private final RandomAccessFile fileReader;
    private int nbModuloI = 0;
    private boolean skipComment = true;

    // ------------------------ Constructeur(s) ------------------------

    public Sp3FileReader(Sp3File sp3File) throws FileNotFoundException {
        Objects.requireNonNull(sp3File, "sp3 file");

        this.fileReader = new RandomAccessFile(sp3File.getFile(), MODE);
    }

    public Sp3FileReader(Sp3File sp3File, boolean skipComment) throws FileNotFoundException {
        this(sp3File);
        this.skipComment = skipComment;
    }

    // ----------------------- Methodes internes -----------------------

    public long getFilePointer() throws IOException {
        return this.fileReader.getFilePointer();
    }

    public void seek(long pos) throws IOException {
        this.fileReader.seek(pos);
    }

    @Override
    public String readLine() throws IOException {

        String line;
        do {
            line = fileReader.readLine();

            if (nbModuloI < 2 && isModuloI(line)) {
                nbModuloI += 1;
            }

        } while (skipComment && isCommentLine(line));

        return isEOF(line) ? null : line;
    }

    @Override
    public boolean isEndOfHeader() {
        return nbModuloI >= 2;
    }

    private boolean isCommentLine(String line) {
        if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
            return false;
        } else {
            return COMMENT.equals(line.substring(0, 2));
        }
    }

    public static boolean isModuloI(String line) {
        if (StringUtils.isBlank(line) || (StringUtils.isNotBlank(line) && line.length() < 2)) {
            return false;
        } else {
            return MODULO_I.equals(line.substring(0, 2));
        }
    }

    public static boolean isEOF(String line) {
        return line == null || EOF.equals(line);
    }

    // -------------------- Methodes de l'interface --------------------

    /**
     * Closes the stream and releases any system resources associated with it. Once
     * the stream has been closed, further read(), ready(), mark(), reset(), or
     * skip() invocations will throw an IOException. Closing a previously closed
     * stream has no effect.
     */
    @Override
    public void close() {
        IOUtils.closeQuietly(fileReader);
    }
}
