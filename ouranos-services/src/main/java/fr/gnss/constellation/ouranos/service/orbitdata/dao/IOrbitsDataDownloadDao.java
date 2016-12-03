package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.nio.file.Path;
import java.util.List;

import fr.gnss.constellation.ouranos.bean.orbitdata.OrbitDataBean;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface IOrbitsDataDownloadDao {

	void downloadFile(List<OrbitDataBean> orbitsData, Path destinationDir) throws TechnicalException;
}
