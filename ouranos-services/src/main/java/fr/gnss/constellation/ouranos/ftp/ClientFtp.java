package fr.gnss.constellation.ouranos.ftp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientFtp {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientFtp.class);

	private static final String DEFAULT_USER = "anonymous";
	private static final String DEFAULT_PASSWORD = "anonymous@domain.com";

	private FTPClient ftp = new FTPClient();
	private FTPClientConfig config = new FTPClientConfig();

	private String serverName = "";
	private String user = "";
	private String password = "";

	public ClientFtp(String serverName) {
		this.serverName = serverName;
		this.ftp.setConnectTimeout(300000); // 300seconde
	}

	public ClientFtp(String serverName, String user, String password) {
		this(serverName);
		this.user = user;
		this.password = password;
	}

	/**
	 * Set timeout on the open connection
	 * 
	 * @param timeout
	 *            - in milliseconde
	 */
	public void setTimeOut(int timeout) {
		this.ftp.setConnectTimeout(timeout);
	}

	public boolean openConnection() {
		boolean isConnected = true;

		try {
			this.ftp.connect(this.serverName);
			LOGGER.info("Connection au serveur : " + this.serverName);

			int reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				this.ftp.disconnect();
				isConnected = false;
			}

			if (isConnected) {
				isConnected = this.ftp.login(StringUtils.isBlank(this.user) ? DEFAULT_USER : this.user,
						StringUtils.isBlank(this.password) ? DEFAULT_PASSWORD : this.password);
			}

		} catch (SocketException e) {
			String message = "Connection au serveur timeout : " + this.serverName;
			LOGGER.error(message, e);
			isConnected = false;
		} catch (IOException e) {
			String message = "Impossible d'établire la connection au serveur : " + this.serverName;
			LOGGER.error(message, e);
			isConnected = false;
		}

		return isConnected;
	}

	public boolean isConnected() {
		return this.ftp.isConnected();
	}

	/**
	 * Download ftp file, with user connection otherwise use anonymous.
	 * 
	 * @param remoteFileName
	 *            - File to store information
	 */
	public boolean downloadBinaryFile(String remoteFileName, Path outputFile) {

		boolean isSuccessFullDownload = false;
		OutputStream outputStream1 = null;
		try {
			this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// this method switches data connection mode from server-to-client
			// (default mode) to client-to-server which can pass through
			// firewall.
			this.ftp.enterLocalPassiveMode();

			outputStream1 = new BufferedOutputStream(new FileOutputStream(outputFile.toFile()));
			isSuccessFullDownload = this.ftp.retrieveFile(remoteFileName, outputStream1);

			if (isSuccessFullDownload) {
				LOGGER.debug("Téléchargement effectué avec success.");
			}

		} catch (IOException e) {
			String message = "Impossible de télécharger le fichier. [remoteFileName=" + remoteFileName + ", outputFile="
					+ outputFile.getFileName() + "]";
			LOGGER.error(message, e);
		} finally {
			IOUtils.closeQuietly(outputStream1);
		}

		return isSuccessFullDownload;
	}

	public void logoutAndCloseConnection() {
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (IOException e) {
				String message = "Impossible de clôre la connexion.";
				LOGGER.error(message, e);
			}
		}
	}
}
