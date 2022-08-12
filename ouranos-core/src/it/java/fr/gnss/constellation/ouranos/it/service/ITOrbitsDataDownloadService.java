package fr.gnss.constellation.ouranos.it.service;


import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OuranosConfiguration.class})
@TestPropertySource("classpath:ouranos.properties")
public class ITOrbitsDataDownloadService {
/*
    @Autowired
    private IOrbitsDataDownloadService orbitsDataDownloadService;

    @Value("${download.sp3.directory}")
    private String defaultDownloadSp3Directory;

    @AfterEach
    public void deleteAllDownloadFile() throws IOException {
        FileUtils.cleanDirectory(new File(defaultDownloadSp3Directory));
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDay1() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-12-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-12-22T14:00", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDay2() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDayBeforeMidnight() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
        // En java 24:00 interdit, on prend donc 00:00 du jour suivant
        LocalDateTime end = LocalDateTime.parse("2013-12-22T23:59", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDayAfterMidnight() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
        // En java 24:00 interdit, on prend donc 00:00 du jour suivant
        LocalDateTime end = LocalDateTime.parse("2013-12-23T00:00", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDayNoData() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-22T15:00", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }

    @Test
    public void testDownloadAndGetFileForPeriodOneDayNoDataMultiDay() throws Exception {

        LocalDateTime start = LocalDateTime.parse("2013-08-22T10:00", DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse("2013-08-24T15:00", DateTimeFormatter.ISO_DATE_TIME);
        List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(EphemerideType.igs, start, end, OrbitType.sp3);

        this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
            File sp3DownloadFile = Paths.get(defaultDownloadSp3Directory, sp3FileName.getFileName(false)).toFile();
            assertTrue(sp3DownloadFile.exists());
        }
    }
*/
}
