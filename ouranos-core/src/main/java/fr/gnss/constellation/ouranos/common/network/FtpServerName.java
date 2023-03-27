package fr.gnss.constellation.ouranos.common.network;

import org.apache.commons.lang3.StringUtils;

public class FtpServerName {

  private final String ftpServerName;

  public FtpServerName(String serverName) {

    if (StringUtils.isBlank(serverName)) {
      throw new IllegalArgumentException(String.format("Le DNS du serveur ne peut être null ou vide [serverName=%s]", serverName));
    }

    this.ftpServerName = serverName;
  }

  public String getValue() {
    return this.ftpServerName;
  }
}
