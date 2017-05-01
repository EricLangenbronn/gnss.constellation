package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.service.IPropertiesService;
import fr.gnss.constellation.ouranos.service.orbitdata.bean.OrbitDataBean;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.IOrbitsDataDownloadDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.ISp3FileDao;

public class OrbitsDataService implements IOrbitsDataService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataService.class);

	private ISp3FileDao sp3Dao;
	private IOrbitsDataDownloadDao orbitsDataDownloadDao;
	private IPropertiesService propertiesService;

	@Override
	public List<SateliteTimeCoordinate> getDatasForPeriod(LocalDateTime start, LocalDateTime end,
			EphemerideType ephemerideType, OrbitType orbitType) throws TechnicalException, BusinessException {

		List<SateliteTimeCoordinate> allSatelitesForPeriod = new ArrayList<>();
		try {
			List<File> sp3Files = this.downloadAndGetFileForPeriod(start, end, ephemerideType, orbitType);
			allSatelitesForPeriod = this.readDatasForPeriod(sp3Files, start, end);
		} catch (TechnicalException e) {
			String message = "Impossible d'obtenir les données pour cette période. [start=" + start + ", end=" + end
					+ ", ephemerideType=" + ephemerideType + ", orbitType=" + orbitType + "]";
			LOGGER.error(message);
			throw new TechnicalException(message);
		} catch (BusinessException e) {
			String message = "Impossible de parser les données pour cette période. [start=" + start + ", end=" + end
					+ ", ephemerideType=" + ephemerideType + ", orbitType=" + orbitType + "]";
			LOGGER.error(message);
			throw new TechnicalException(message);
		}

		return allSatelitesForPeriod;
	}

	@Override
	public List<File> downloadAndGetFileForPeriod(LocalDateTime start, LocalDateTime end, EphemerideType ephemerideType,
			OrbitType orbitType) throws TechnicalException, BusinessException {

		List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(ephemerideType,
				start, end, orbitType);
		List<File> sp3Files = new ArrayList<>();
		try {
			for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
				File sp3File = sp3Dao.getFile(propertiesService.getString("repertoire.sp3"), sp3FileName);
				if (sp3File == null) {
					OrbitDataBean orbitDataBean = new OrbitDataBean(sp3FileName);
					List<OrbitDataBean> orbitDataBeans = new ArrayList<>();
					orbitDataBeans.add(orbitDataBean);
					this.orbitsDataDownloadDao.downloadAndStoreSp3File(orbitDataBeans,
							Paths.get(propertiesService.getString("repertoire.sp3")));
				}
				sp3Files.add(sp3File);
			}
		} catch (TechnicalException e) {
			String message = "Impossible de téléchargé le fichier, arret du traitement.";
			LOGGER.error(message, e);
			throw new BusinessException(message);
		}

		return sp3Files;
	}

	@Override
	public List<SateliteTimeCoordinate> readDatasForPeriod(List<File> sp3Files, LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {

		List<SateliteTimeCoordinate> allSatelitesForPeriod = new ArrayList<>();
		Sp3FileParser sp3FileParser = null;
		try {
			for (File sp3File : sp3Files) {
				sp3FileParser = new Sp3FileParser(new Sp3File(sp3File));
				allSatelitesForPeriod.addAll(sp3FileParser.getPositionAndClockRecord(start, end));
			}
		} catch (TechnicalException e) {
			String message = "Impossible de récuperer la liste de satelite";
			LOGGER.error(message, e);
			throw new TechnicalException(message, e);

		} catch (BusinessException e) {
			String message = "Impossible d'initialiser le parser de fichier";
			LOGGER.error(message, e);
			throw new BusinessException(message, e);
		} finally {
			IOUtils.closeQuietly(sp3FileParser);
		}

		return allSatelitesForPeriod;
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
