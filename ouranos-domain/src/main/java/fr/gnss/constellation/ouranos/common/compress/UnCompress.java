package fr.gnss.constellation.ouranos.common.compress;

import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class UnCompress {

    public static InputStream unCompressZFile(InputStream compressSteam) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZCompressorInputStream compressInputStream = null;
        InputStream unCompressIs = null;
        try {
            compressInputStream = new ZCompressorInputStream(new BufferedInputStream(compressSteam));

            final int BUFFER = 2048;
            byte[] data = new byte[BUFFER];
            while (compressInputStream.read(data, 0, BUFFER) != -1) {
                out.write(data);
            }

            unCompressIs = new ByteArrayInputStream(out.toByteArray());

            IOUtils.closeQuietly(out);
        } catch (Exception e) {
            IOUtils.closeQuietly(unCompressIs);
            throw new IOException("Impossible de décompresser le flux.", e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(compressInputStream);
        }

        return unCompressIs;
    }
}
