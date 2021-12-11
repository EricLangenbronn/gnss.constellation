package fr.gnss.constellation.ouranos.service.orbitdata;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.util.List;

public interface IOrbitsDataDownloadService {

    public void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd);
}
