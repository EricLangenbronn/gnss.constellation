package fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.orbit.a;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.core.AbstractCoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.core.ISp3CoreParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;

public class Sp3aCoreParser extends AbstractCoreParser implements ISp3CoreParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sp3aCoreParser.class);

	// ------------------------ Constructeur(s) ------------------------

	public Sp3aCoreParser(FileReader p_fr) throws TechnicalException {
		super(p_fr);
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public void initParser() throws TechnicalException {
		// TODO Auto-generated method stub

	}

	@Override
	public SatelliteTimeCoordinate<CartesianCoordinate3D> getPositionAndClockRecord() throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SatelliteTimeCoordinate<CartesianCoordinate3D>> getPeriodOfPosition(LocalDateTime start, LocalDateTime end)
			throws TechnicalException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
