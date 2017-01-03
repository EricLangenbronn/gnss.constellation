package fr.gnss.constellation.ouranos.service.computation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.parser.sp3.Sp3FileParser;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticTransformation;

public class ComputationService implements IComputationService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputationService.class);

	@Override
	public List<SateliteTimeCoordinate> getSateliteVisiblePeriod(Sp3FileParser sp3FileParser, double elevationMask,
			LocalDateTime start, LocalDateTime end, GeodeticCoordinate gStation)
			throws TechnicalException, BusinessException {

		List<SateliteTimeCoordinate> fileSatelite = sp3FileParser.getPositionAndClockRecord(start, end);
		List<SateliteTimeCoordinate> sateliteVisible = new ArrayList<>();
		for (SateliteTimeCoordinate e : fileSatelite) {

			SateliteTimeCoordinate sateliteTimeVisible = new SateliteTimeCoordinate(e.getEpochHeaderRecord());
			for (Sp3SateliteInformation p : e.getSatelites().values()) {
				double[] sphCoord = GeodeticTransformation.processElevationAzimuth(gStation, p.getPosition());

				// sphCoord[2] = azimuth, azimuth doit être positif sinon c'est que le satelite est pas
				// du bon coté de la terre
				if (sphCoord[2] >= 0) {
					// 3.1415 / 2 rad = 90.0°
					if ((sphCoord[1] >= elevationMask) && (sphCoord[1] < (3.141592 / 2))) {
						sateliteTimeVisible.addSatellite(p);
					}
				}
			}
			sateliteVisible.add(sateliteTimeVisible);
		}
		afficheSateliteVisibleCount(sateliteVisible);

		return sateliteVisible;
	}

	public void afficheSateliteVisible(List<SateliteTimeCoordinate> satelitesVisible) {

		for (SateliteTimeCoordinate e : satelitesVisible) {
			Map<String, Sp3SateliteInformation> lpos = e.getSatelites();
			System.out.println("Satelite visible heure : " + e.getEpochHeaderRecord());
			for (Sp3SateliteInformation pos : lpos.values()) {
				System.out.println(pos);
			}
		}
		System.out.println("----------------------------------------------");

	}

	public void afficheSateliteVisibleCount(List<SateliteTimeCoordinate> satelitesVisible) {

		for (SateliteTimeCoordinate e : satelitesVisible) {
			System.out.println("Satelite visible heure : " + e.getEpochHeaderRecord());

			int nbSat = e.getSatelites().values().size();
			System.out.print(" : " + nbSat + "\n");
		}
		System.out.println("----------------------------------------------");

	}

}