package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

public interface IComputationService {

	public SphericalCoordinate processSphericalCoordinate(GeodeticCoordinate gStation, CartesianCoordinate3D station,
			CartesianCoordinate3D satelite);

	public List<Entry<LocalDateTime, List<Sp3SateliteInformation>>> getSateliteVisiblePeriod(
			Sp3FileParser sp3FileParser, double elevationMask, LocalDateTime start, LocalDateTime end,
			GeodeticCoordinate gStation) throws TechnicalException, BusinessException;
}