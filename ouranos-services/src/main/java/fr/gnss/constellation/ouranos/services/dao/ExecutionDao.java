package fr.gnss.constellation.ouranos.services.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileReader;
import fr.gnss.constellation.ouranos.librairy.coordinate.CartesianCoordinate3D;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;

public interface ExecutionDao {

	public SphericalCoordinate processSphericalCoordinate(GeodeticCoordinate gStation, CartesianCoordinate3D station,
			CartesianCoordinate3D satelite);

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisiblePeriod(LocalDateTime start, LocalDateTime end,
			GeodeticCoordinate gStation) throws TechnicalException, BusinessException;
}
