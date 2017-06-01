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
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
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
	private IOrbitsDataDownloadService orbitsDataDownloadService;

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start, LocalDateTime end,
			EphemerideType ephemerideType, OrbitType orbitType) throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
		try {
			List<File> sp3Files = this.orbitsDataDownloadService.downloadAndGetFileForPeriod(start, end, ephemerideType,
					orbitType);
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
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(List<File> sp3Files,
			LocalDateTime start, LocalDateTime end) throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
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

	public void setOrbitsDataDownloadService(IOrbitsDataDownloadService orbitsDataDownloadService) {
		this.orbitsDataDownloadService = orbitsDataDownloadService;
	}

}
