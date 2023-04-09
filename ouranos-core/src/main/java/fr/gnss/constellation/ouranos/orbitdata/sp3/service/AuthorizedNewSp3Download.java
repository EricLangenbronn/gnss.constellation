package fr.gnss.constellation.ouranos.orbitdata.sp3.service;

public class AuthorizedNewSp3Download {

  private final boolean authorizedNewDownload;

  public AuthorizedNewSp3Download(boolean authorizedNewDownload) {
    this.authorizedNewDownload = authorizedNewDownload;
  }

  public boolean isAuthorized() {
    return this.authorizedNewDownload;
  }
}
