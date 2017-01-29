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
	public void downloadFile(List<OrbitDataBean> orbitsData, Path destinationDir) throws TechnicalException {

		this.clientFtpSp3File = new ClientFtpSp3File();

		ManagedConnection<ClientFtpSp3File> managedConnection = new ManagedConnection<>();
		this.clientFtpSp3File = managedConnection.initConnection(this.clientFtpSp3File);

		if (this.clientFtpSp3File == null) {
			String message = "Impossible d'établir une connexion au serveur FTP.";
			LOGGER.error(message);
			throw new TechnicalException(message);
		} else {
			Sp3FileName sp3FileName;
			Path pathToSaveSp3;
			for (OrbitDataBean orbitData : orbitsData) {
				sp3FileName = new Sp3FileName(orbitData.getEphemeride(), orbitData.getEpoch(), orbitData.getHour(),
						orbitData.getDayInWeek(), orbitData.getOrbitType());
				pathToSaveSp3 = Paths.get(destinationDir.toString(), sp3FileName.toString());
				this.clientFtpSp3File.downloadSp3File(sp3FileName, pathToSaveSp3);
			}

			this.clientFtpSp3File.closeConnection();
		}

	}

}
