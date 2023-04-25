package fr.gnss.constellation.ouranos.orbitdata.service.sequential;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader.Sp3Reader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.orbitdata.service.AbstractOrbitDataService;
import fr.gnss.constellation.ouranos.sp3.Sp3FileNameUtils;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;


public class OrbitDataSequentialService extends AbstractOrbitDataService {

  public OrbitDataSequentialService(ISp3Service sp3Service) {
    super(sp3Service);
  }

  @Override
  protected List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> doProcessing(
      LocalDateTime start, LocalDateTime end, List<Sp3FileName> allSp3FileNameBetweenStartEnd) {

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
    for (Sp3FileName sp3FileName : allSp3FileNameBetweenStartEnd) {
      LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);
      LocalDateTime dateFin = Sp3FileNameUtils.getEndDateTime(sp3FileName);

      dateDebut = start.isAfter(dateDebut) ? start : dateDebut;
      dateFin = end.isBefore(dateFin) ? end : dateFin;

      Sp3Reader sp3FileParser = null;
      try {
        Sp3File sp3File = sp3Service.getSp3File(sp3FileName);
        if (sp3File != null) {
          sp3FileParser = new Sp3Reader(sp3File);
          allSatelitesForPeriod.addAll(sp3FileParser.getPositionsAndClocksRecords(dateDebut, dateFin));
        }
      } catch (IOException e) {
        throw new RuntimeException("Impossible de charger les informations pour ce fichier : " + sp3FileName, e);
      } finally {
        IOUtils.closeQuietly(sp3FileParser);
      }
    }

    return allSatelitesForPeriod;
  }
}
