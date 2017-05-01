package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.orbitdata.bean.OrbitDataBean;
import fr.gnss.constellation.ouranos.toolbox.ClientFtpSp3File;
import fr.gnss.constellation.ouranos.toolbox.ManagedConnection;

public class OrbitsDataDownloadDao implements IOrbitsDataDownloadDao {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDownloadDao.class);

	private ClientFtpSp3File clientFtpSp3File;

	@Override
	public void downloadAndStoreSp3File(List<OrbitDataBean> orbitsData, Path destinationDir) throws TechnicalException {

		this.clientFtpSp3File = new ClientFtpSp3File();
		this.downloadAndStoreSp3File(this.clientFtpSp3File, orbitsData, destinationDir);
	}

	@Override
	public void downloadAndStoreSp3File(List<OrbitDataBean> orbitsData, Path destinationDir, String ftpServerName)
			throws TechnicalException {

		this.clientFtpSp3File = new ClientFtpSp3File(ftpServerName);
		this.downloadAndStoreSp3File(this.clientFtpSp3File, orbitsData, destinationDir);
	}

	private void downloadAndStoreSp3File(ClientFtpSp3File clientFtpSp3File, List<OrbitDataBean> orbitsData,
			Path destinationDir) throws TechnicalException {

		ManagedConnection<ClientFtpSp3File> managedConnection = new ManagedConnection<>();
		this.clientFtpSp3File = managedConnection.initConnection(this.clientFtpSp3File);

		if (clientFtpSp3File == null) {
			String message = "Impossible d'établir une connexion au serveur FTP.";
			LOGGER.error(message);
			throw new TechnicalException(message);
		} else {
			Sp3FileName sp3FileName = null;
			Path pathToSaveSp3 = null;
			try {
				for (OrbitDataBean orbitData : orbitsData) {
					sp3FileName = new Sp3FileName(orbitData.getEphemeride(), orbitData.getEpoch(),
							orbitData.getDayInWeek(), orbitData.getHour(), orbitData.getOrbitType());
					pathToSaveSp3 = Paths.get(destinationDir.toString(), sp3FileName.getFileName(true));
					clientFtpSp3File.downloadAndStoreSp3File(sp3FileName, pathToSaveSp3);
				}
			} catch (TechnicalException e) {
				String message = "Impossible de télécharger ou de stocker le fichier SP3. [sp3FileName="
						+ sp3FileName.toString() + ",pathToSaveSp3=" + pathToSaveSp3 + "]";
				LOGGER.error(message);
				throw new TechnicalException(message);
			} finally {
				this.clientFtpSp3File.closeConnection();
			}
		}
	}

}
