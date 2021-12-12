package fr.gnss.constellation.ouranos.service.orbitdata.access;

import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.orbitdata.OrbitDataUtils;
import fr.gnss.constellation.ouranos.service.orbitdata.Sp3FileNameUtils;
import fr.gnss.constellation.ouranos.service.orbitdata.access.dao.IOrbitsDataDao;
import fr.gnss.constellation.ouranos.service.orbitdata.download.IOrbitsDataDownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrbitsDataService implements IOrbitsDataService {

    // -------------------- Services --------------------

    private final IOrbitsDataDao orbitsDataDao;
    private final IOrbitsDataDownloadService orbitsDataDownloadService;
    private final String defaultDownloadSp3Directory;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start, LocalDateTime end
            , EphemerideType ephemerideType, OrbitType orbitType) {

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();

        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(ephemerideType, start, end, orbitType);
        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);
            LocalDateTime dateFin = Sp3FileNameUtils.getEndDateTime(sp3FileName);

            if (start.isAfter(dateDebut)) {
                dateDebut = start;
            }

            if (end.isBefore(dateFin)) {
                dateFin = end;
            }

            allSatelitesForPeriod.addAll(this.orbitsDataDao.readDatasForPeriod(defaultDownloadSp3Directory, sp3FileName, dateDebut, dateFin));
        }

        return allSatelitesForPeriod;
    }
}
