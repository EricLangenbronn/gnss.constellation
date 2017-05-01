package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;

public interface ISp3FileParser extends Closeable {

	void close();

	public String getVersionSymbol() throws TechnicalException;

	public LocalDateTime getStartEpochRecord() throws TechnicalException;

	public int getNumberOfEpoch() throws TechnicalException;

	public String getDataUsed() throws TechnicalException;

	public String getCoordinateSystem() throws TechnicalException;

	public String getOrbitType() throws TechnicalException;

	public String getAgency() throws TechnicalException;

	public int getGPSWeek() throws TechnicalException;

	public double getSecondsOfWeek() throws TechnicalException;

	public double getEpochInterval() throws TechnicalException;

	public int getModJulDaySt() throws TechnicalException;

	public double getFractionalDay() throws TechnicalException;

	public int getNumber0fSats() throws TechnicalException;

	public String[] getSatId() throws TechnicalException, BusinessException;

	public int[] getSatAccuracy() throws TechnicalException, BusinessException;

	public String getFileType() throws TechnicalException;

	public String getTimeSystem() throws TechnicalException;

	public List<SateliteTimeCoordinate> getPositionAndClockRecord(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException;
}
