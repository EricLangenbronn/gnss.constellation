package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;

public interface IOrbitsDataDownloadService {

	public List<File> downloadAndGetFileForPeriod(LocalDateTime start, LocalDateTime end, EphemerideType ephemerideType,
			OrbitType orbitType) throws TechnicalException, BusinessException;
}
