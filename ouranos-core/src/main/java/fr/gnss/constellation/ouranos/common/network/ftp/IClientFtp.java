package fr.gnss.constellation.ouranos.common.network.ftp;

import fr.gnss.constellation.ouranos.common.network.IConnection;
import java.io.IOException;
import java.io.InputStream;

public interface IClientFtp extends IConnection {

  void setTimeOut(int timeout);

  boolean isConnected();

  InputStream retrieveFileStream(String remoteFileName) throws IOException;

  void logoutAndCloseConnection();

  String getServerName();

  String getUser();
}
