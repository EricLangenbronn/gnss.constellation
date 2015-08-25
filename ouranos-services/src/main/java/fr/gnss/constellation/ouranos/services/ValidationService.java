package fr.gnss.constellation.ouranos.services;

import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FormatConst;
import fr.gnss.constellation.ouranos.util.ConfigurationLoader;

public class ValidationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(ValidationService.class);

	private static FilenameFilter filter = new FilenameFilter() {
		public boolean accept(File directory, String fileName) {
			return fileName.endsWith(".sp3") || fileName.endsWith(".sp3.Z");
		}
	};

	public ValidationService() {
	}

	public File isDataForPeriod(LocalDateTime start, LocalDateTime end) throws BusinessException {

		File isDataForPeriod = null;
		File sp3Dir = new File(ConfigurationLoader.getProperty("repertoire.sp3"));

		LOGGER.info("Chargement des données à partir du répertoire : " + sp3Dir.getAbsolutePath());
		for (File sp3File : sp3Dir.listFiles(filter)) {
			Sp3FileNameFormat fileNameFormat = new Sp3FileNameFormat(sp3File.getName());

			LocalDateTime startDay = Sp3FormatConst.firstEpochRecord.plusWeeks(fileNameFormat.getGpsWeek())
					.plusDays(fileNameFormat.getDay()).atTime(0, 0);
			LocalDateTime endDay = startDay.plusHours(23).plusMinutes(59);

			if ((start.isEqual(startDay) || start.isAfter(startDay)) && (end.isEqual(endDay) || end.isBefore(endDay))) {
				LOGGER.info("Il existe des données pour la période séléctionnée.");
				isDataForPeriod = sp3File;
			}
		}

		return isDataForPeriod;
	}
}
