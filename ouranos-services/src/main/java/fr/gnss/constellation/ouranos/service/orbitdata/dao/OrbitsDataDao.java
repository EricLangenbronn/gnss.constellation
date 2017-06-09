package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class OrbitsDataDao implements IOrbitsDataDao {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataDao.class);

	private ISp3FileDao sp3Dao;

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> readDatasForPeriod(String repertoireSp3,
			Sp3FileName sp3FileName, LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {

		List<SatelliteTimeCoordinate<CartesianCoordinate3D>> allSatelitesForPeriod = new ArrayList<>();
		Sp3FileParser sp3FileParser = null;

		try {
			File sp3File = sp3Dao.getFile(repertoireSp3, sp3FileName);
			sp3FileParser = new Sp3FileParser(new Sp3File(sp3File));
			allSatelitesForPeriod.addAll(sp3FileParser.getPositionAndClockRecord(start, end));
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
}
