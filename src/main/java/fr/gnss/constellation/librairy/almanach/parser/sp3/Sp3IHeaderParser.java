package fr.gnss.constellation.librairy.almanach.parser.sp3;

import java.time.LocalDateTime;

import fr.gnss.constellation.Exception.BusinessException;
import fr.gnss.constellation.Exception.TechnicalException;

public interface Sp3IHeaderParser {

	String getVersionSymbol() throws TechnicalException;

	LocalDateTime getStartEpochRecord() throws TechnicalException;

	int getNumberOfEpoch() throws TechnicalException;

	String getDataUsed() throws TechnicalException;

	String getCoordinateSystem() throws TechnicalException;

	String getOrbitType() throws TechnicalException;

	String getAgency() throws TechnicalException;

	int getGPSWeek() throws TechnicalException;

	double getSecondsOfWeek() throws TechnicalException;

	double getEpochInterval() throws TechnicalException;

	int getModJulDaySt() throws TechnicalException;

	double getFractionalDay() throws TechnicalException;

	int getNumber0fSats() throws TechnicalException;

	String[] getSatId() throws TechnicalException, BusinessException;

	int[] getSatAccuracy() throws TechnicalException, BusinessException;

	String getFileType() throws TechnicalException;

	String getTimeSystem() throws TechnicalException;
}
