package fr.gnss.constellation.ouranos.services.impl;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.dao.Sp3Dao;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FormatConst;
import fr.gnss.constellation.ouranos.services.OuranosValidationService;

public class OuranosValidationServiceImpl implements OuranosValidationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(OuranosValidationServiceImpl.class);

	private Sp3Dao sp3Dao;
	private OuranosPropertiesServiceImpl propertiesService;
	
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

	public void setSp3Dao(Sp3Dao sp3Dao) {
		this.sp3Dao = sp3Dao;
	}
	
	public void setPropertiesService(OuranosPropertiesServiceImpl propertiesService) {
		this.propertiesService = propertiesService;
	}

}
