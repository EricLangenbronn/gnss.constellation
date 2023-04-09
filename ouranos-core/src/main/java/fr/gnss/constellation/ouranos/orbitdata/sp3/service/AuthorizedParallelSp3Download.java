package fr.gnss.constellation.ouranos.orbitdata.sp3.service;

public class AuthorizedParallelSp3Download {

  private final boolean authorizedParallelDownload;

  public AuthorizedParallelSp3Download(boolean authorizedParallelDownload) {
    this.authorizedParallelDownload = authorizedParallelDownload;
  }

  public boolean isAuthorized() {
    return this.authorizedParallelDownload;
  }
}
