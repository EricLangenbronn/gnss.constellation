package fr.gnss.constellation.ouranos.service.orbitdata;

import java.io.File;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FormatConst;
import fr.gnss.constellation.ouranos.service.IPropertiesService;
import fr.gnss.constellation.ouranos.service.orbitdata.dao.ISp3Dao;

public class OrbitsDataService implements IOrbitsDataService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrbitsDataService.class);

	private ISp3Dao sp3Dao;
	private IPropertiesService propertiesService;

	@Override
	public File isDataForPeriod(LocalDateTime start, LocalDateTime end) throws TechnicalException, BusinessException {

		File isDataForPeriod = null;

		for (File sp3File : sp3Dao.getListSp3File(propertiesService.getString("repertoire.sp3"))) {
			Sp3FileNameFormat fileNameFormat = new Sp3FileNameFormat(sp3File.getName());

			LocalDateTime startDay = Sp3FormatConst.firstEpochRecord.plusWeeks(fileNameFormat.getGpsWeek())
					.plusDays(fileNameFormat.getDay()).atTime(0, 0);
			LocalDateTime endDay = startDay.plusHours(23).plusMinutes(59);

			if ((start.isEqual(startDay) || start.isAfter(startDay)) && (end.isEqual(endDay) || end.isBefore(endDay))) {
				LOGGER.info(
						"Il existe des données pour la période séléctionnée. [start=" + start + ", end=" + end + "]");
				isDataForPeriod = sp3File;
			}
		}

		return isDataForPeriod;
	}

	public void setSp3Dao(ISp3Dao sp3Dao) {
		this.sp3Dao = sp3Dao;
	}

	public void setPropertiesService(IPropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

}
