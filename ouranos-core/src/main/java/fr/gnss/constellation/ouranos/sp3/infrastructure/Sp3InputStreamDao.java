package fr.gnss.constellation.ouranos.sp3.infrastructure;

import fr.gnss.constellation.ouranos.common.compress.UnCompress;
import fr.gnss.constellation.ouranos.common.network.FtpServerName;
import fr.gnss.constellation.ouranos.common.network.ManagedConnection;
import fr.gnss.constellation.ouranos.common.network.ftp.ClientFtp;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.monitoring.tracktime.TrackTime;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Singleton
@RequiredArgsConstructor
@Slf4j
public class Sp3InputStreamDao implements ISp3InputStreamDao {

  // -------------------- Propriétés de la classe --------------------

  private final FtpServerName ftpServerName;
  private final EpochDirectory epochDirectory;
  private final ManagedConnection<ClientFtp> managedConnection = new ManagedConnection<>();

  // -------------------- Methodes de l'interface --------------------

  @TrackTime
  @Override
  public InputStream downloadSp3File(Sp3FileName sp3FileName, boolean unCompressSp3File) {

    ClientFtp clientFtp = managedConnection.initConnection(new ClientFtp(ftpServerName));
    if (clientFtp == null) {
      throw new RuntimeException(String.format("Impossible d'établir une connexion au serveur FTP : %s ", ftpServerName.getValue()));
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
      throw new RuntimeException("Impossible de télécharger le fichier : " + generateFtSp3FileUrl(sp3FileName), e);
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

    ClientFtp clientFtp = managedConnection.initConnection(new ClientFtp(ftpServerName));
    if (clientFtp == null) {
      throw new RuntimeException(String.format("Impossible d'établir une connexion au serveur FTP : %s", ftpServerName.getValue()));
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
    return epochDirectory.getValue() + "/" + sp3fileName.getGpsWeek() + "/" + sp3fileName.getFileName(true);
  }

}
