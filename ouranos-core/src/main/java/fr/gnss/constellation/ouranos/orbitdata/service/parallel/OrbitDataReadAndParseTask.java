package fr.gnss.constellation.ouranos.orbitdata.service.parallel;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader.Sp3Reader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.sp3.Sp3FileNameUtils;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
public class OrbitDataReadAndParseTask implements Callable<List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>>> {

  private final ISp3Service sp3Service;
  private final Sp3FileName sp3FileName;
  private final LocalDateTime start;
  private final LocalDateTime end;

  public OrbitDataReadAndParseTask(ISp3Service sp3Service, Sp3FileName sp3FileName, LocalDateTime start, LocalDateTime end) {
    this.sp3Service = sp3Service;
    this.sp3FileName = sp3FileName;
    this.start = start;
    this.end = end;
  }

  @Override
  public List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> call() throws Exception {

    LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);
    LocalDateTime dateFin = Sp3FileNameUtils.getEndDateTime(sp3FileName);

    dateDebut = start.isAfter(dateDebut) ? start : dateDebut;
    dateFin = end.isBefore(dateFin) ? end : dateFin;

    Sp3Reader sp3FileParser = null;
    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
    try {
      Sp3File sp3File = sp3Service.getSp3File(sp3FileName);
      if (sp3File != null) {
        sp3FileParser = new Sp3Reader(sp3File);
        allSatelitesForPeriod.addAll(sp3FileParser.getPositionsAndClocksRecords(dateDebut, dateFin));
        log.info(String.format("Parsing reussi [newSp3File=%s]", sp3File.getFile().getAbsoluteFile()));
      } else {
        log.warn(String.format("Parsing impossible, fichier non trouvé [newSp3File=%s]", sp3FileName));
      }
    } catch (IOException e) {
      throw new RuntimeException("Impossible de charger les informations pour ce fichier : " + sp3FileName, e);
    } finally {
      IOUtils.closeQuietly(sp3FileParser);
    }

    return allSatelitesForPeriod;
  }

  public Sp3FileName getSp3FileName() {
    return sp3FileName;
  }
}
