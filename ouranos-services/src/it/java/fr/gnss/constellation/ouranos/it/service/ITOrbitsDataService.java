package fr.gnss.constellation.ouranos.it.service;

import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.orbitdata.access.OrbitsDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OuranosConfiguration.class})
public class ITOrbitsDataService {

    @Autowired
    private OrbitsDataService orbitsDataService;

    @Test
    public void testReadDatasForPeriodStartAfterEndBefore() {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);

        List<File> files = new ArrayList<>();
        files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
                EphemerideType.igs, OrbitType.sp3);

        assertNotNull(datas);
        assertEquals(20, datas.size());

    }

    @Test
    public void testReadDatasForPeriodStartEqualsEndEquals() {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-22T23:59", DateTimeFormatter.ISO_DATE_TIME);

        List<File> files = new ArrayList<>();
        files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
                EphemerideType.igs, OrbitType.sp3);

        assertNotNull(datas);
        assertEquals(96, datas.size());

    }

    @Test
    public void testReadDatasForPeriodStartEqualsEndBefore() {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-22T15:45", DateTimeFormatter.ISO_DATE_TIME);

        List<File> files = new ArrayList<>();
        files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
                EphemerideType.igs, OrbitType.sp3);

        assertNotNull(datas);
        assertEquals(63, datas.size());

    }

    @Test
    public void testReadDatasForPeriodStartAfterEndEquals() {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

        List<File> files = new ArrayList<>();
        files.add(new File("./src/it/resources/Sp3File/igs17720.sp3"));

        List<SatelliteTimeCoordinate<CartesianCoordinate3D>> datas = orbitsDataService.getDatasForPeriod(start, end,
                EphemerideType.igs, OrbitType.sp3);

        assertNotNull(datas);
        assertEquals(55, datas.size());

    }

}
