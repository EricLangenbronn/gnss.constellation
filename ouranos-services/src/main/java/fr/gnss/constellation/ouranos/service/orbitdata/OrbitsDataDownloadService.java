package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.IPropertiesService;
import fr.gnss.constellation.ouranos.service.orbitdata.bean.OrbitDataBean;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.IOrbitsDataDownloadDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.ISp3FileDao;
import fr.gnss.constellation.ouranos.toolbox.UnCompress;

/**
 * Classe de téléchargement des fichiers sp3 via FTP
 * 
 * @author eric
 *
 */
public class OrbitsDataDownloadService implements IOrbitsDataDownloadService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDownloadService.class);

	private IOrbitsDataDownloadDao orbitsDataDownloadDao;
	private ISp3FileDao sp3Dao;
	private IPropertiesService propertiesService;

	@Override
	public void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd)
			throws TechnicalException, BusinessException {

		try {
			for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
				File sp3File = sp3Dao.getFile(propertiesService.getString("repertoire.sp3"), sp3FileName);
				if (sp3File == null) {
					OrbitDataBean orbitDataBean = new OrbitDataBean(sp3FileName);
					List<OrbitDataBean> orbitDataBeans = new ArrayList<>();
					orbitDataBeans.add(orbitDataBean);
					this.orbitsDataDownloadDao.downloadAndStoreSp3File(orbitDataBeans,
							Paths.get(propertiesService.getString("repertoire.sp3")));
					// uncompress
					UnCompress.unCompressZFile(Paths.get(propertiesService.getString("repertoire.sp3")),
							sp3FileName.getFileName(true), sp3FileName.getFileName(false));
				}
			}
		} catch (TechnicalException e) {
			String message = "Impossible de téléchargé le fichier, arret du traitement.";
			LOGGER.error(message, e);
			throw new BusinessException(message);
		}
	}

	public void setSp3Dao(ISp3FileDao sp3Dao) {
		this.sp3Dao = sp3Dao;
	}

	public void setPropertiesService(IPropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

	public void setOrbitsDataDownloadDao(IOrbitsDataDownloadDao orbitsDataDownloadDao) {
		this.orbitsDataDownloadDao = orbitsDataDownloadDao;
	}

}
