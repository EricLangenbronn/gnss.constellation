package fr.gnss.constellation.ouranos.service.orbitdata.download;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.logmessage.ILogMessageService;
import fr.gnss.constellation.ouranos.service.orbitdata.access.dao.ISp3FileDao;
import fr.gnss.constellation.ouranos.service.orbitdata.domain.OrbitData;
import fr.gnss.constellation.ouranos.service.orbitdata.download.dao.IOrbitsDataDownloadDao;
import fr.gnss.constellation.ouranos.toolbox.UnCompress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrbitsDataDownloadService implements IOrbitsDataDownloadService {

    // -------------------- Services --------------------

    private final IOrbitsDataDownloadDao orbitsDataDownloadDao;
    private final ISp3FileDao sp3Dao;
    private final ILogMessageService configurationErrorMessageService;
    private final String defaultDownloadSp3Directory;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd) {


        log.debug("Repertoire SP3 : " + defaultDownloadSp3Directory);
        for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {

            File sp3File = sp3Dao.getFile(defaultDownloadSp3Directory, sp3FileName);
            if (sp3File == null) {
                OrbitData orbitDataBean = new OrbitData(sp3FileName);
                List<OrbitData> orbitDataBeans = new ArrayList<>();
                orbitDataBeans.add(orbitDataBean);
                this.orbitsDataDownloadDao.downloadAndStoreSp3File(orbitDataBeans, Paths.get(defaultDownloadSp3Directory));
                // uncompress
                UnCompress.unCompressZFile(Paths.get(defaultDownloadSp3Directory), sp3FileName.getFileName(true), sp3FileName.getFileName(false));
            }
        }
    }

}
