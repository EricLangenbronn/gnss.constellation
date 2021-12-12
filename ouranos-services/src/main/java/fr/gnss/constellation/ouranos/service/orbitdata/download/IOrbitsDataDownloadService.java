package fr.gnss.constellation.ouranos.service.orbitdata.download;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.util.List;

public interface IOrbitsDataDownloadService {

    void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd);
}
