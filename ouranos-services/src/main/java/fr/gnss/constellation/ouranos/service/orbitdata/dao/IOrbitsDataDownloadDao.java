package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.nio.file.Path;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.orbitdata.bean.OrbitDataBean;

public interface IOrbitsDataDownloadDao {

	void downloadFile(List<OrbitDataBean> orbitsData, Path destinationDir) throws TechnicalException;
}
