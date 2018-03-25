package fr.gnss.constellation.ouranos.service.orbitdata;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.EphemerideType;
import fr.gnss.constellation.ouranos.librairy.almanach.OrbitType;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;
import fr.gnss.constellation.ouranos.service.IConfigurationService;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.IOrbitsDataDao;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.Sp3FileNameUtils;

@Service("orbitsDataService")
public class OrbitsDataService implements IOrbitsDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataService.class);

	// -------------------- Services --------------------

	@Autowired
	private IOrbitsDataDao orbitsDataDao;

	@Autowired
	private IOrbitsDataDownloadService orbitsDataDownloadService;

	@Autowired
	private IConfigurationService configurationService;

	@Autowired
	private IConfigurationLogMessageService configurationErrorMessageService;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getDatasForPeriod(LocalDateTime start, LocalDateTime end, EphemerideType ephemerideType,
			OrbitType orbitType) throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
		try {
			List<Sp3FileName> allSp3FileBetweenStartEnd = OrbitDataUtils.getAllSp3FileNameBetween2Date(ephemerideType, start, end, orbitType);
			this.orbitsDataDownloadService.downloadAndGetFileForPeriod(allSp3FileBetweenStartEnd);

			for (Sp3FileName sp3FileName : allSp3FileBetweenStartEnd) {
				LocalDateTime dateDebut = Sp3FileNameUtils.getStartDateTime(sp3FileName);
				LocalDateTime dateFin = Sp3FileNameUtils.getEndDateTime(sp3FileName);

				if (start.isAfter(dateDebut)) {
					dateDebut = start;
				}

				if (end.isBefore(dateFin)) {
					dateFin = end;
				}

				allSatelitesForPeriod.addAll(this.orbitsDataDao.readDatasForPeriod(configurationService.getDirectorySp3(), sp3FileName, dateDebut, dateFin));
			}

		} catch (TechnicalException e) {
			LOGGER.error(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.TE"), start, end, ephemerideType, orbitType);
			throw new TechnicalException(
					MessageFormat.format(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.TE"), start, end, ephemerideType, orbitType), e);
		} catch (BusinessException e) {
			// start, end, ephemerideType, orbitType
			LOGGER.error(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.BE"), start, end, ephemerideType, orbitType);
			throw new BusinessException(
					MessageFormat.format(configurationErrorMessageService.getDefautErrorMessage("ODS.GDFP.BE"), start, end, ephemerideType, orbitType), e);
		}

		return allSatelitesForPeriod;
	}
}
