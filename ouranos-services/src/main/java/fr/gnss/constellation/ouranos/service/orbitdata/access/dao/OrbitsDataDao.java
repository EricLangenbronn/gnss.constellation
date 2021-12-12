package fr.gnss.constellation.ouranos.service.orbitdata.access.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.reader.Sp3Reader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.logmessage.ILogMessageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrbitsDataDao implements IOrbitsDataDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDao.class);

    // -------------------- Services --------------------

    private final ILogMessageService configurationLogMessageService;

    private final ISp3FileDao sp3Dao;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(String repertoireSp3, Sp3FileName sp3FileName
            , LocalDateTime start, LocalDateTime end) {

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
        Sp3Reader sp3FileParser = null;

        try {
            // TODO renvoyer Sp3File
            File sp3File = sp3Dao.getFile(repertoireSp3, sp3FileName);
            sp3FileParser = new Sp3Reader(new Sp3File(sp3File));
            allSatelitesForPeriod.addAll(sp3FileParser.getPositionsAndClocksRecords(start, end));
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("ODD.RDFP.TE"), new Object[]{}), e);
        } finally {
            IOUtils.closeQuietly(sp3FileParser);
        }

        return allSatelitesForPeriod;
    }
}
