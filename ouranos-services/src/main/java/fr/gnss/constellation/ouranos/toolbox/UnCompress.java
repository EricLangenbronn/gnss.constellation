package fr.gnss.constellation.ouranos.toolbox;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnCompress {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UnCompress.class);

	public static void unCompressZFile(Path pathDir, String fileNameIn, String fileNameOut) {

		String zFileName = Paths.get(pathDir.toString(), fileNameIn).toString();
		String tarFileName = Paths.get(pathDir.toString(), fileNameOut).toString();
		try {
			int buffersize = 1024;
			FileInputStream fin = new FileInputStream(zFileName);
			BufferedInputStream in = new BufferedInputStream(fin);
			FileOutputStream out = new FileOutputStream(tarFileName);
			ZCompressorInputStream zIn = new ZCompressorInputStream(in);
			final byte[] buffer = new byte[buffersize];
			int n = 0;
			while (-1 != (n = zIn.read(buffer))) {
				out.write(buffer, 0, n);
			}
			out.close();
		} catch (Exception e) {
			String message = "Impossible de décompresser le fichier. [pathDir=" + pathDir.toString() + ", fileNameIn="
					+ fileNameIn + ", fileNameOut=" + fileNameOut + "]";
			LOGGER.error(message);
		}
	}
}
