package fr.gnss.constellation.ouranos.common.network.ftp;

import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.common.network.IConnection;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientFtp implements IConnection {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClientFtp.class);

  private static final String DEFAULT_USER = "anonymous";
  private static final String DEFAULT_PASSWORD = "anonymous@domain.com";

  private final FTPClient ftp = new FTPClient();
  private final FTPClientConfig config = new FTPClientConfig();

  private String serverName = "";
  private String user = "";
  private String password = "";

  public ClientFtp(FtpServerName ftpServerName) {
    this.serverName = ftpServerName.getValue();
    this.ftp.setConnectTimeout(30000); // 30seconde
  }

  public ClientFtp(FtpServerName ftpServerName, String user, String password) {
    this(ftpServerName);
    this.user = user;
    this.password = password;
  }

  @Override
  public boolean isConnectionOpen() {
    return this.isConnected();
  }

  @Override
  public void closeConnection() {
    this.logoutAndCloseConnection();
  }

  @Override
  public void openConnection() throws IOException {

    this.ftp.connect(this.serverName);
    LOGGER.info("Connection au serveur : " + this.serverName);

    int reply = ftp.getReplyCode();
    if (!FTPReply.isPositiveCompletion(reply)) {
      this.ftp.disconnect();
    } else {
      this.ftp.login(StringUtils.isBlank(this.user) ? DEFAULT_USER : this.user
          , StringUtils.isBlank(this.password) ? DEFAULT_PASSWORD : this.password);
    }
  }

  public void setTimeOut(int timeout) {
    this.ftp.setConnectTimeout(timeout);
  }

  public boolean isConnected() {
    return this.ftp.isConnected();
  }

  /**
   * Download ftp file, with user connection otherwise use anonymous.
   *
   * @param remoteFileName - File to store information
   */
  public InputStream retrieveFileStream(String remoteFileName) throws IOException {

    this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
    // this method switches data connection mode from server-to-client
    // (default mode) to client-to-server which can pass through
    // firewall.
    this.ftp.enterLocalPassiveMode();

    LOGGER.debug("download file to url : " + remoteFileName);
    InputStream sp3InputSteam = this.ftp.retrieveFileStream(remoteFileName);
    if (sp3InputSteam == null) {
      throw new IOException(this.ftp.getReplyString());
    }
    LOGGER.debug("Téléchargement effectué avec success");

    return sp3InputSteam;
  }

  public void logoutAndCloseConnection() {
    if (this.ftp.isConnected()) {
      try {
        this.ftp.logout();
        this.ftp.disconnect();
      } catch (IOException e) {
        LOGGER.error("Impossible de clôre la connexion.", e);
      }
    }
  }

  public String getServerName() {
    return serverName;
  }

  public String getUser() {
    return user;
  }
}
