package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.librairy.almanach.Sp3FileType;

public class Sp3File {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3File.class);

	// -------------------- Propriétés de la classe --------------------

	private File sp3File;

	// ------------------------ Constructeur(s) ------------------------

	public Sp3File(File f) {
		this.sp3File = f;
	}

	public Sp3File(Path p) {
		this(p.toFile());
	}

	public Sp3File(String chemin) {
		this(new File(chemin));
	}

	// ----------------------- Methodes internes -----------------------

	public Sp3FileType getSp3FileType() throws IOException {
		Sp3FileType sp3TypeFile = null;
		if (this.sp3File.exists() && this.sp3File.isFile() && this.sp3File.canRead()) {
			RandomAccessFile sp3HeaderReader = null;
			try {
				sp3HeaderReader = new RandomAccessFile(this.sp3File, "r");
				byte[] cbuf = new byte[2];

				sp3HeaderReader.read(cbuf);
				if (cbuf[0] == '#') {
					// http://stackoverflow.com/questions/17912640/byte-and-char-conversion-in-java
					sp3TypeFile = Sp3FileType.stringToSp3FileType((char) (cbuf[1] & 0xFF));
				} else {
					String message = "Le fichier est mal formaté, premier caractère : " + cbuf[0] + ", devrait être #";
					throw new IOException(message);
				}

			} catch (IOException e) {
				String message = "Impossible de lire les deux premiers caractères du fichier.";
				throw new IOException(message);
			} finally {
				IOUtils.closeQuietly(sp3HeaderReader);
			}
		} else {
			String message = "Impossible d'acceder au fichier [sp3File=" + this.sp3File.getAbsolutePath() + "] [exists=" + this.sp3File.exists() + ", isFile="
					+ this.sp3File.isFile() + "]";
			throw new IOException(message);
		}

		return sp3TypeFile;
	}

	public File getFile() {
		return this.sp3File;
	}

}
