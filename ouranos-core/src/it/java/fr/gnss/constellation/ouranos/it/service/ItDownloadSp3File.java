package fr.gnss.constellation.ouranos.it.service;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;


public class ItDownloadSp3File {


  private static final String URL_FTP_SP3 = "igs.ensg.ign.fr";
  private static final String URL_FTP_SP3_DIRECTORY = "/pub/igs/products";

  @Test
  public void testConnexion() throws Exception {
    InetAddress address = InetAddress.getByName(URL_FTP_SP3);
    ClientFtp client = new ClientFtp(new FtpServerName(address.getHostAddress()));

    assertDoesNotThrow(client::openConnection);
    assertTrue(client.isConnected());

    client.logoutAndCloseConnection();
  }

  @Test
  public void testConnexionDownloadFile() throws Exception {
    InetAddress address = InetAddress.getByName(URL_FTP_SP3);
    ClientFtp client = new ClientFtp(new FtpServerName(address.getHostAddress()));

    client.openConnection();
    assertTrue(client.isConnected());

    InputStream sp3InputStream = client.retrieveFileStream(URL_FTP_SP3_DIRECTORY + "/1282/igs12821.sp3.Z");

    assertNotNull(sp3InputStream);
    assertNotEquals(-1, sp3InputStream.read());

    client.logoutAndCloseConnection();
    IOUtils.closeQuietly(sp3InputStream);
  }

  @Test
  public void testConnexionCheckTimeOut() throws Exception {
    InetAddress address = InetAddress.getByName(URL_FTP_SP3);

    ClientFtp client = new ClientFtp(new FtpServerName(address.getHostAddress()));
    client.setTimeOut(1); // 1 milliseconde

    assertThrows(IOException.class, client::openConnection);
    assertFalse(client.isConnected());
    client.logoutAndCloseConnection();
  }

}
