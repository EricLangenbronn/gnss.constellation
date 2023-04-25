package fr.gnss.constellation.ouranos.orbitdata.service.parallel;

import fr.gnss.constellation.ouranos.common.service.parallel.ParallelCallableService;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.TimeCoordinateSatellitePosition;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.orbitdata.service.AbstractOrbitDataService;
import fr.gnss.constellation.ouranos.sp3.service.ISp3Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


@Slf4j
public class OrbitDataParallelService extends AbstractOrbitDataService {

  // -------------------- Services --------------------

  private final OrbitDataReadAndParseFactory orbitDataReadAndParseFactory;

  private final ParallelCallableService<List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>>, OrbitDataReadAndParseTask>
      parallelCallableService =
      new ParallelCallableService<>();

  // ------------------------ Constructeur(s) ------------------------

  public OrbitDataParallelService(ISp3Service sp3Service, OrbitDataReadAndParseFactory orbitDataReadAndParseFactory) {
    super(sp3Service);
    this.orbitDataReadAndParseFactory = orbitDataReadAndParseFactory;
  }

  @Override
  protected List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> doProcessing(
      LocalDateTime start, LocalDateTime end, List<Sp3FileName> allSp3FileNameBetweenStartEnd) {

    if (CollectionUtils.isEmpty(allSp3FileNameBetweenStartEnd)) {
      return new ArrayList<>();
    }

    log.info("Parallel read and parse Sp3 files");
    Collection<Future<List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>>>> callablesComplete =
        parallelCallableService.executeTasks(
            Optional.of(allSp3FileNameBetweenStartEnd).stream().flatMap(Collection::stream)
                .map(sp3FileName -> orbitDataReadAndParseFactory.getOrbitDataReadAndParseTask(sp3FileName, start, end))
                .collect(Collectors.toList())
        );

    if (allSp3FileNameBetweenStartEnd.size() != callablesComplete.size()) {
      log.error(String.format("Erreur lors de la lecture ou du parsing de(s) fichier(s) sp3 [lancées=%s,finalisées=%s]"
          , allSp3FileNameBetweenStartEnd.size(), callablesComplete.size()));
    }

    List<TimeCoordinateSatellitePosition<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
    Optional.of(callablesComplete).stream().flatMap(Collection::stream)
        .forEach(satelitesForPeriod -> {
          try {
            allSatelitesForPeriod.addAll(satelitesForPeriod.get());
          } catch (InterruptedException | ExecutionException e) {
            log.error("Erreur lors de la récupération des informations d'une tâche OrbitDataReadAndParseTask", e);
            Thread.currentThread().interrupt();
          }
        });

    return allSatelitesForPeriod;
  }
}
