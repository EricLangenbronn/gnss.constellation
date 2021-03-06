package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.orbitdata.domain.OrbitData;
import fr.gnss.constellation.ouranos.toolbox.ClientFtpSp3File;
import fr.gnss.constellation.ouranos.toolbox.ClientFtpSp3FileConfiguration;
import fr.gnss.constellation.ouranos.toolbox.ManagedConnection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("orbitsDataDownloadDao")
@RequiredArgsConstructor
public class OrbitsDataDownloadDao implements IOrbitsDataDownloadDao {

	// -------------------- Services --------------------

	private final IConfigurationLogMessageService configurationLogMessageService;

	// -------------------- Propriétés de la classe --------------------

	private final ClientFtpSp3FileConfiguration clientFtpSp3FileConfiguration;
	private ClientFtpSp3File clientFtpSp3File;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir) throws TechnicalException {

		this.clientFtpSp3File = new ClientFtpSp3File(clientFtpSp3FileConfiguration);
		this.checkConnectionDownloadAndStoreSp3File(orbitsData, destinationDir);
		this.clientFtpSp3File.closeConnection();
	}

	@Override
	public void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir, String ftpServerName) throws TechnicalException {

		this.clientFtpSp3File = new ClientFtpSp3File(clientFtpSp3FileConfiguration);
		this.checkConnectionDownloadAndStoreSp3File(orbitsData, destinationDir);
		this.clientFtpSp3File.closeConnection();
	}

	// -------------------- Methodes internes --------------------

	private void checkConnectionDownloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir) throws TechnicalException {

		ManagedConnection<ClientFtpSp3File> managedConnection = new ManagedConnection<>();
		this.clientFtpSp3File = managedConnection.initConnection(this.clientFtpSp3File);

		if (clientFtpSp3File == null) {
			log.error(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.CSE"));
			throw new TechnicalException(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.CSE"));
		} else {
			Sp3FileName sp3FileName = null;
			Path pathToSaveSp3 = null;
			try {
				for (OrbitData orbitData : orbitsData) {
					sp3FileName = new Sp3FileName(orbitData.getEphemeride(), orbitData.getEpoch(), orbitData.getDayInWeek(), orbitData.getHour(),
							orbitData.getOrbitType());
					pathToSaveSp3 = Paths.get(destinationDir.toString(), sp3FileName.getFileName(true));
					clientFtpSp3File.downloadAndStoreSp3File(sp3FileName, pathToSaveSp3);
				}
			} catch (IOException e) {
				log.error(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.DASE"), sp3FileName.toString(), pathToSaveSp3);
				throw new TechnicalException(
						MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.DASE"), sp3FileName.toString(), pathToSaveSp3),
						e);
			} finally {
				this.clientFtpSp3File.closeConnection();
			}
		}
	}

}
