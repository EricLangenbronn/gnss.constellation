package fr.gnss.constellation.ouranos.common.compress;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.io.IOUtils;

public class UnCompress {

  public static InputStream unCompressZFile(InputStream compressSteam) throws IOException {

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ZCompressorInputStream compressInputStream = null;
    InputStream unCompressIs = null;
    try {
      compressInputStream = new ZCompressorInputStream(new BufferedInputStream(compressSteam));

      final int buffer = 2048;
      byte[] data = new byte[buffer];
      while (compressInputStream.read(data, 0, buffer) != -1) {
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
