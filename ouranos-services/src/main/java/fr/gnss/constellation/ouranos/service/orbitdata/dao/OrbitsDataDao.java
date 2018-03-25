package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;

@Repository("orbitsDataDao")
public class OrbitsDataDao implements IOrbitsDataDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDao.class);

	// -------------------- Services --------------------

	@Autowired
	private IConfigurationLogMessageService configurationLogMessageService;

	@Autowired
	private ISp3FileDao sp3Dao;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(String repertoireSp3, Sp3FileName sp3FileName, LocalDateTime start,
			LocalDateTime end) throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
		Sp3FileParser sp3FileParser = null;

		try {
			File sp3File = sp3Dao.getFile(repertoireSp3, sp3FileName);
			sp3FileParser = new Sp3FileParser(new Sp3File(sp3File));
			allSatelitesForPeriod.addAll(sp3FileParser.getPositionAndClockRecord(start, end));
		} catch (TechnicalException e) {
			LOGGER.error(configurationLogMessageService.getDefautErrorMessage("ODD.RDFP.TE"));
			throw new TechnicalException(MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("ODD.RDFP.TE"), new Object[] {}), e);

		} catch (BusinessException e) {
			LOGGER.error(configurationLogMessageService.getDefautErrorMessage("ODD.RDFP.BE"));
			throw new BusinessException(MessageFormat.format(configurationLogMessageService.getDefautErrorMessage("ODD.RDFP.BE"), new Object[] {}), e);
		} finally {
			IOUtils.closeQuietly(sp3FileParser);
		}

		return allSatelitesForPeriod;
	}
}
