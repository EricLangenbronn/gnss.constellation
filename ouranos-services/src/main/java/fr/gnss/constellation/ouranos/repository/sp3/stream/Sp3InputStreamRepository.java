package fr.gnss.constellation.ouranos.repository.sp3.stream;

import fr.gnss.constellation.ouranos.common.compress.UnCompress;
import fr.gnss.constellation.ouranos.common.network.ManagedConnection;
import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class Sp3InputStreamRepository implements ISp3InputStreamRepository {

    // -------------------- Propriétés de la classe --------------------

    @Qualifier("ftpServerName")
    private final String ftpServerName;
    @Qualifier("epochDirectory")
    private final String epochDirectory;
    private ClientFtp clientFtp;
    private ManagedConnection<ClientFtp> managedConnection;

    // -------------------- Initialisation --------------------

    @PostConstruct
    public void init() {
        this.clientFtp = new ClientFtp(ftpServerName);
        this.managedConnection = new ManagedConnection<>();
    }

    // -------------------- Methodes de l'interface --------------------

    public InputStream downloadSp3File(Sp3FileName sp3FileName, boolean unCompressSp3File) {

        clientFtp = managedConnection.initConnection(clientFtp);
        if (clientFtp == null) {
            throw new RuntimeException("Impossible d'établir une connexion au serveur FTP : " + ftpServerName);
        }

        InputStream compressSp3InputSteam = null;
        InputStream sp3InputSteam = null;
        try {
            compressSp3InputSteam = clientFtp.retrieveFileStream(generateFtSp3FileUrl(sp3FileName));
            if (unCompressSp3File) {
                sp3InputSteam = UnCompress.unCompressZFile(compressSp3InputSteam);
            }
            log.debug("Téléchargement réussi : " + sp3FileName);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de télécharger le fichier : " + sp3FileName, e);
        } finally {
            clientFtp.logoutAndCloseConnection();
        }

        if (unCompressSp3File) {
            return sp3InputSteam;
        } else {
            return compressSp3InputSteam;
        }
    }

    @Override
    public Map<Sp3FileName, InputStream> downloadSp3File(List<Sp3FileName> sp3FileNames, boolean unCompressSp3File) {

        clientFtp = managedConnection.initConnection(clientFtp);
        if (clientFtp == null) {
            throw new RuntimeException("Impossible d'établir une connexion au serveur FTP : " + ftpServerName);
        }

        Map<Sp3FileName, InputStream> sp3Files = new HashMap<>();
        InputStream compressSp3InputSteam = null;
        try {
            for (Sp3FileName sp3FileName : sp3FileNames) {
                compressSp3InputSteam = clientFtp.retrieveFileStream(generateFtSp3FileUrl(sp3FileName));
                if (unCompressSp3File) {
                    sp3Files.put(sp3FileName, UnCompress.unCompressZFile(compressSp3InputSteam));
                } else {
                    sp3Files.put(sp3FileName, compressSp3InputSteam);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossible de télécharger l'un des fichiers", e);
        } finally {
            clientFtp.logoutAndCloseConnection();
        }

        return sp3Files;
    }

    public String generateFtSp3FileUrl(Sp3FileName sp3fileName) {
        return epochDirectory + "/" + sp3fileName.getGpsWeek() + "/" + sp3fileName.getFileName(true);
    }

}
