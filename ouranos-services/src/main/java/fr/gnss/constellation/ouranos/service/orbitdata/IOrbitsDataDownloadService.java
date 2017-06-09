package fr.gnss.constellation.ouranos.service.orbitdata;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

public interface IOrbitsDataDownloadService {

	public void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd) throws TechnicalException, BusinessException;
}
