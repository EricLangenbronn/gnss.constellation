package fr.gnss.constellation.ouranos.toolbox;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
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

	private static final String FTP_SERVER_NAME = "ftp.igs.org";
	private static final String EPOCH_DIRECTORY = "/pub/product";

	private ClientFtp clientFtp;
	private String ipServer = "";
	private String ftpServerName = "";
	private boolean isOpenConnection = false;

	public ClientFtpSp3File() {
		this.ftpServerName = FTP_SERVER_NAME;
	}

	public ClientFtpSp3File(String ftpServerName) {
		this.ftpServerName = ftpServerName;
	}

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

	public void downloadAndStoreSp3File(Sp3FileName sp3fileName, Path destinationSp3File) throws TechnicalException {
		String sp3UrlFileName = EPOCH_DIRECTORY + "/" + sp3fileName.getGpsWeek() + "/" + sp3fileName.getFileName(true);

		boolean isOpenConnection = this.clientFtp.openConnection();
		String message = "";
		if (isOpenConnection) {

			boolean isFileDownload = this.clientFtp.downloadBinaryFile(sp3UrlFileName, destinationSp3File);
			if (isFileDownload) {
				LOGGER.debug("Téléchargement réussi : " + destinationSp3File.toString());
			} else {
				FileUtils.deleteQuietly(destinationSp3File.toFile());
				message = "Impossible de télécharger le fichier. [source=" + sp3UrlFileName + ", destination="
						+ destinationSp3File.toString() + "]";
				LOGGER.error(message);

				throw new TechnicalException(message);
			}
		} else {
			message = "Connection au serveur ftp impossible. [serveur=" + this.ftpServerName + "]";
			LOGGER.error(message);

			throw new TechnicalException(message);
		}
	}

	@Override
	public void closeConnection() {
		this.clientFtp.logoutAndCloseConnection();
	}
}
