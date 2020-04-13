package fr.gnss.constellation.ouranos.toolbox;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

/**
 * Simplification d'un client FTP pour télécharger les fichiers sp3. Une seul
 * connexion possible en anonymous pour télécharger les fichiers.
 * 
 * @author eric
 *
 */
public class ClientFtpSp3File implements IConnection {

	// FIXME est ce qu'il doit sauvegarder son état ou non ? pour le moment oui

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientFtpSp3File.class);

	// -------------------- Propriétés de la classe --------------------

	private ClientFtp clientFtp;
	private String ipServer = "";
	private String ftpServerName = "";
	private String epochDirectory = "";
	private boolean isOpenConnection = false;

	// -------------------- Constructeurs --------------------

	public ClientFtpSp3File(ClientFtpSp3FileConfiguration clientFtpSp3FileConfiguration) {
		this.ftpServerName = clientFtpSp3FileConfiguration.getFtpServerName();
		this.epochDirectory = clientFtpSp3FileConfiguration.getEpochDirectory();
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void openConnection() {

		this.isOpenConnection = false;
		if (this.clientFtp != null) {
			this.clientFtp.logoutAndCloseConnection();
		}

		try {
			InetAddress address = InetAddress.getByName(this.ftpServerName);
			this.ipServer = address.getHostAddress();

			this.clientFtp = new ClientFtp(this.ipServer);
			this.isOpenConnection = this.clientFtp.openConnection();
		} catch (UnknownHostException e) {
			// c'est pas tout à fait vrai, mais on s'en contente :)
			String message = "Impossible d'acceder à internet.";
			LOGGER.error(message);
		}
	}

	@Override
	public boolean isConnectionOpen() {
		return this.isOpenConnection;
	}

	@Override
	public void closeConnection() {
		this.clientFtp.logoutAndCloseConnection();
	}

	// -------------------- Methodes internes --------------------

	public void downloadAndStoreSp3File(Sp3FileName sp3fileName, Path destinationSp3File) throws IOException {
		String sp3UrlFileName = this.epochDirectory + "/" + sp3fileName.getGpsWeek() + "/" + sp3fileName.getFileName(true);

		boolean isOpenConnection = this.clientFtp.openConnection();
		String message = "";
		if (isOpenConnection) {

			boolean isFileDownload = this.clientFtp.downloadBinaryFile(sp3UrlFileName, destinationSp3File);
			if (isFileDownload) {
				LOGGER.debug("Téléchargement réussi : " + destinationSp3File.toString());
			} else {
				FileUtils.deleteQuietly(destinationSp3File.toFile());
				message = "Impossible de télécharger le fichier. [source=" + sp3UrlFileName + ", destination=" + destinationSp3File.toString() + "]";
				LOGGER.error(message);

				throw new IOException(message);
			}
		} else {
			message = "Connection au serveur ftp impossible. [serveur=" + this.ftpServerName + "]";
			LOGGER.error(message);

			throw new IOException(message);
		}
	}

	public String getFtpServerName() {
		return ftpServerName;
	}

}
