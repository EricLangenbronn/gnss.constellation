package fr.gnss.constellation.ouranos.common.network;

import java.io.IOException;

public interface IConnection {

  void openConnection() throws IOException;

  boolean isConnectionOpen();

  void closeConnection();

}
