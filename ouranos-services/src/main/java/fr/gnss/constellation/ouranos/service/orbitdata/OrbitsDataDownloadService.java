package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.IConfigurationService;
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
@Service("orbitsDataDownloadService")
public class OrbitsDataDownloadService implements IOrbitsDataDownloadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDownloadService.class);

	// -------------------- Services --------------------

	@Autowired
	private IOrbitsDataDownloadDao orbitsDataDownloadDao;

	@Autowired
	private ISp3FileDao sp3Dao;

	@Autowired
	private IConfigurationService configurationService;

	@Autowired
	private IConfigurationLogMessageService configurationErrorMessageService;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void downloadAndGetFileForPeriod(List<Sp3FileName> allSp3FileBetweenStartEnd) throws TechnicalException, BusinessException {

		try {
			String repertoireSp3 = configurationService.getDirectorySp3();
			LOGGER.debug("Repertoire SP3 : " + repertoireSp3);
			for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {

				File sp3File = sp3Dao.getFile(repertoireSp3, sp3FileName);
				if (sp3File == null) {
					OrbitDataBean orbitDataBean = new OrbitDataBean(sp3FileName);
					List<OrbitDataBean> orbitDataBeans = new ArrayList<>();
					orbitDataBeans.add(orbitDataBean);
					this.orbitsDataDownloadDao.downloadAndStoreSp3File(orbitDataBeans, Paths.get(repertoireSp3));
					// uncompress
					UnCompress.unCompressZFile(Paths.get(repertoireSp3), sp3FileName.getFileName(true), sp3FileName.getFileName(false));
				}
			}
		} catch (TechnicalException e) {
			LOGGER.error(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.TE"));
			throw new BusinessException(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.TE"), e);
		}
	}

}
