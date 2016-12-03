package fr.gnss.constellation.ouranos.toolbox;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

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

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientFtpSp3File.class);

	private static final String FTP_SERVER = "igs.ensg.ign.fr";
	private static final String EPOCH_DIRECTORY = "/pub/igs/products";

	private ClientFtp clientFtp;
	private String ipServer = "";
	private boolean isOpenConnection = false;

	public ClientFtpSp3File() {
	}

	@Override
	public void openConnection() {

		this.isOpenConnection = false;
		if (this.clientFtp != null) {
			this.clientFtp.logoutAndCloseConnection();
		}

		try {
			InetAddress address = InetAddress.getByName(FTP_SERVER);
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

	public void downloadSp3File(Sp3FileName sp3fileName, Path destinationSp3File) throws TechnicalException {
		String sp3UrlFileName = EPOCH_DIRECTORY + "/" + sp3fileName.getGpsWeek() + "/" + sp3fileName.getFileName(true);

		boolean isOpenConnection = this.clientFtp.openConnection();
		String message = "";
		if (isOpenConnection) {

			boolean isFileDownload = this.clientFtp.downloadBinaryFile(sp3UrlFileName, destinationSp3File);
			if (isFileDownload) {
				LOGGER.debug("Téléchargement réussi : " + destinationSp3File.toString());
			} else {
				message = "Impossible de télécharger le fichier. [source=" + sp3UrlFileName + ", destination="
						+ destinationSp3File.toString() + "]";
				LOGGER.error(message);

				throw new TechnicalException(message);
			}
		} else {
			message = "Connection au serveur ftp impossible. [serveur=" + FTP_SERVER + "]";
			LOGGER.error(message);

			throw new TechnicalException(message);
		}
	}

	@Override
	public void closeConnection() {
		this.clientFtp.logoutAndCloseConnection();
	}
}
