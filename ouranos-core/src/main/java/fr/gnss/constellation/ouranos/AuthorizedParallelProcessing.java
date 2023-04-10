package fr.gnss.constellation.ouranos;

public class AuthorizedParallelProcessing {

  private final boolean authorizedParallelDownload;

  public AuthorizedParallelProcessing(boolean authorizedParallelDownload) {
    this.authorizedParallelDownload = authorizedParallelDownload;
  }

  public boolean isAuthorized() {
    return this.authorizedParallelDownload;
  }
}
