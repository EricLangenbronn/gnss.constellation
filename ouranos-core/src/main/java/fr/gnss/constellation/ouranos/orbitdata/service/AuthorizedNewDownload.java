package fr.gnss.constellation.ouranos.orbitdata.service;

public class AuthorizedNewDownload {

    private final boolean authorizedNewDownload;

    public AuthorizedNewDownload(boolean authorizedNewDownload) {
        this.authorizedNewDownload = authorizedNewDownload;
    }

    public boolean isAuthorized() {
        return this.authorizedNewDownload;
    }
}
