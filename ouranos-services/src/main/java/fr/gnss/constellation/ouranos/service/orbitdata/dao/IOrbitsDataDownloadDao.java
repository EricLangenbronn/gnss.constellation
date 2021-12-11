package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import fr.gnss.constellation.ouranos.service.orbitdata.domain.OrbitData;

import java.nio.file.Path;
import java.util.List;

public interface IOrbitsDataDownloadDao {

    void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir);

    void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir, String ftpServerName);
}
