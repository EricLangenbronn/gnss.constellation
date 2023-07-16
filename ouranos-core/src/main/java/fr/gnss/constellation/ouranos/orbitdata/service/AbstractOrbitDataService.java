package fr.gnss.constellation.ouranos.orbitdata.service;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.sp3.Sp3FileNameUtils;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractOrbitDataService implements IOrbitDataService {

  // -------------------- Services --------------------

  protected final ISp3Service sp3Service;

  // ------------------------ Constructeur(s) ------------------------

  public AbstractOrbitDataService(ISp3Service sp3Service) {
    this.sp3Service = sp3Service;
  }

  // -------------------- Methodes de l'interface --------------------

  @Override
  @CacheResult(cacheName = "cartesian-position")
  public List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> getCartesianPositionsForPeriod(
      @CacheKey LocalDateTime start, @CacheKey LocalDateTime end, @CacheKey EphemerideType ephemerideType, @CacheKey OrbitType orbitType) {

    List<Sp3FileName> allSp3FileNameBetweenStartEnd = Sp3FileNameUtils.getAllSp3FileNameBetween2Date(ephemerideType, start, end, orbitType);

    sp3Service.downloadsAndStoresIfNotExist(allSp3FileNameBetweenStartEnd);

    return doProcessing(start, end, allSp3FileNameBetweenStartEnd);
  }

  protected abstract List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> doProcessing(
      LocalDateTime start, LocalDateTime end, List<Sp3FileName> allSp3FileNameBetweenStartEnd);
}
