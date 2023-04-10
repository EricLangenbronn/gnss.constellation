package fr.gnss.constellation.ouranos.orbitdata.service;

import fr.gnss.constellation.ouranos.AuthorizedParallelProcessing;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
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
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;


@Singleton
@RequiredArgsConstructor
public class OrbitDataService implements IOrbitDataService {

  // -------------------- Services --------------------

  private final ISp3Service sp3Service;
  private final AuthorizedParallelProcessing authorizedParallelProcessing;

  // -------------------- Methodes de l'interface --------------------

  @Override
  public List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> getCartesionPositionsForPeriod(LocalDateTime start, LocalDateTime end
      , EphemerideType ephemerideType, OrbitType orbitType) {

    List<Sp3FileName> allSp3FileNameBetweenStartEnd = Sp3FileNameUtils.getAllSp3FileNameBetween2Date(ephemerideType, start, end, orbitType);

    sp3Service.downloadsAndStoresIfNotExist(allSp3FileNameBetweenStartEnd);

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
