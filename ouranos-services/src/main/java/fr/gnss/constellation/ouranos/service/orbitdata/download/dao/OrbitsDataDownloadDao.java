package fr.gnss.constellation.ouranos.service.orbitdata.download.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.logmessage.ILogMessageService;
import fr.gnss.constellation.ouranos.service.orbitdata.domain.OrbitData;
import fr.gnss.constellation.ouranos.ftp.ClientFtpSp3File;
import fr.gnss.constellation.ouranos.ftp.ClientFtpSp3FileConfiguration;
import fr.gnss.constellation.ouranos.ftp.ManagedConnection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

@Repository("orbitsDataDownloadDao")
@RequiredArgsConstructor
@Slf4j
public class OrbitsDataDownloadDao implements IOrbitsDataDownloadDao {

    // -------------------- Services --------------------

    private final ILogMessageService configurationLogMessageService;

    // -------------------- Propriétés de la classe --------------------

    private final ClientFtpSp3FileConfiguration clientFtpSp3FileConfiguration;
    private ClientFtpSp3File clientFtpSp3File;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir) {

        this.clientFtpSp3File = new ClientFtpSp3File(clientFtpSp3FileConfiguration);
        this.checkConnectionDownloadAndStoreSp3File(orbitsData, destinationDir);
        this.clientFtpSp3File.closeConnection();
    }

    @Override
    public void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir, String ftpServerName) {

        this.clientFtpSp3File = new ClientFtpSp3File(clientFtpSp3FileConfiguration);
        this.checkConnectionDownloadAndStoreSp3File(orbitsData, destinationDir);
        this.clientFtpSp3File.closeConnection();
    }

    // -------------------- Methodes internes --------------------

    private void checkConnectionDownloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir) {

        ManagedConnection<ClientFtpSp3File> managedConnection = new ManagedConnection<>();
        this.clientFtpSp3File = managedConnection.initConnection(this.clientFtpSp3File);

        if (clientFtpSp3File == null) {
            throw new RuntimeException(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.CSE"));
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
                throw new RuntimeException(
                        MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("ODDD.DASSF.TE.DASE"), sp3FileName.toString(), pathToSaveSp3)
                        , e
                );
            } finally {
                this.clientFtpSp3File.closeConnection();
            }
        }
    }

}
