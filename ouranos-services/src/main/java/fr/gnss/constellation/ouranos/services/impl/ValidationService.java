package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.dao.ISp3Dao;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FormatConst;
import fr.gnss.constellation.ouranos.services.IValidationService;

public class ValidationService implements IValidationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

	private ISp3Dao sp3Dao;
	private PropertiesService propertiesService;

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

	public void setPropertiesService(PropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}

}
