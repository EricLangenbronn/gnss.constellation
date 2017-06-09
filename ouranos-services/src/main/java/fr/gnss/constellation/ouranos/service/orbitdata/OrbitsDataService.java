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
import fr.gnss.constellation.ouranos.service.orbitdata.dao.IOrbitsDataDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.IOrbitsDataDownloadDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.ISp3FileDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.Sp3FileNameUtils;

public class OrbitsDataService implements IOrbitsDataService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataService.class);

	private IOrbitsDataDao orbitsDataDao;
	private IOrbitsDataDownloadService orbitsDataDownloadService;
	private IPropertiesService propertiesService;

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start,
			LocalDateTime end, EphemerideType ephemerideType, OrbitType orbitType)
			throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
		try {
			List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(ephemerideType,
					start, end, orbitType);
			this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

			for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
				LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);
				LocalDateTime dateFin = Sp3FileNameUtils.getEndDateTime(sp3FileName);
				
				if(start.isAfter(dateDebut)) {
					dateDebut = start;
				}
				
				if(end.isBefore(dateFin)) {
					dateFin = end;
				}

				allSatelitesForPeriod.addAll(this.orbitsDataDao.readDatasForPeriod(
						this.propertiesService.getString("repertoire.sp3"), sp3FileName, dateDebut, dateFin));
			}

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

	public void setOrbitsDataDownloadService(IOrbitsDataDownloadService orbitsDataDownloadService) {
		this.orbitsDataDownloadService = orbitsDataDownloadService;
	}

	public void setOrbitsDataDao(IOrbitsDataDao orbitsDataDao) {
		this.orbitsDataDao = orbitsDataDao;
	}

	public void setPropertiesService(IPropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

}
