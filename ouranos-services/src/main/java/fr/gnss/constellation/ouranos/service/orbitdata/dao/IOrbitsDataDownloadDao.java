package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.nio.file.Path;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.orbitdata.domain.OrbitData;

public interface IOrbitsDataDownloadDao {

	void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir) throws TechnicalException;

	void downloadAndStoreSp3File(List<OrbitData> orbitsData, Path destinationDir, String ftpServerName) throws TechnicalException;
}
