package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3Parser;

public class Sp3FileReader {

	private File fl;
	private Sp3Parser parser;

	/**
	 * Creates a new Sp3FileReader instance.
	 * 
	 * @param p_file
	 *            - a file.
	 * @throws TechnicalException
	 * @throws BusinessException
	 */
	public Sp3FileReader(File p_file) throws TechnicalException, BusinessException {
		parser = new Sp3Parser(p_file);
	}

	/**
	 * Creates a new Sp3FileReader instance.
	 * 
	 * @param p_fileName
	 *            - a pathname string.
	 * @throws TechnicalException
	 * @throws BusinessException
	 */
	public Sp3FileReader(String p_fileName) throws TechnicalException, BusinessException {
		fl = new File(p_fileName);
		parser = new Sp3Parser(fl);
	}

	/**
	 * Get all positions by hour of the sp3 file.
	 * 
	 * @return
	 * @throws TechnicalException
	 * @throws BusinessException
	 */
	public List<Entry<LocalDateTime, List<Satelite>>> getPositionAndClockRecordAll()
			throws TechnicalException, BusinessException {
		return parser.getPositionAndClockRecordAll();
	}

	/**
	 * Get positions between the two date of the sp3 file.
	 * 
	 * @param start - 
	 * @param end
	 * @return
	 * @throws TechnicalException
	 * @throws BusinessException
	 */
	public List<Entry<LocalDateTime, List<Satelite>>> getPositionAndClockRecord(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {
		return parser.getPositionAndClockRecord(start, end);
	}

}
