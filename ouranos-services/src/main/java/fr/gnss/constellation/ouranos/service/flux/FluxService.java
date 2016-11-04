package fr.gnss.constellation.ouranos.service.flux;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.service.satelitevisible.ISateliteVisibleService;
import fr.gnss.constellation.ouranos.wrapper.XsdWrapper;
import fr.gnss.constellation.ouranos.xsd.VisibleSateliteRequest;

public class FluxService implements IFluxService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FluxService.class);

	private BindingXMLService bindingXmlService;

	private ISateliteVisibleService sateliteVisibleService;

	@Override
	public void sateliteVisible(String version, String requete) throws TechnicalException, BusinessException {

		this.bindingXmlService = BindingXMLService.getInstance();
		InputStream is = new ByteArrayInputStream(requete.getBytes());
		VisibleSateliteRequest request = this.bindingXmlService.mapXml2Object(is, VisibleSateliteRequest.class);

		LocalDateTime dateDebut = request.getStartDateOfMeasure().toGregorianCalendar().toZonedDateTime()
				.toLocalDateTime();
		LocalDateTime dateFin = request.getEndDateOfMeasure().toGregorianCalendar().toZonedDateTime().toLocalDateTime();

		GeodeticCoordinate geodeticCoordinate = XsdWrapper.wrapGeodeticCoordindate(request.getGroundStation());

		double elevationMask = request.getElevationMask();

		List<Entry<LocalDateTime, List<Satelite>>> satelitesVisible = sateliteVisibleService
				.getSateliteVisible(geodeticCoordinate, elevationMask, dateDebut, dateFin);

	}

	public void setSateliteVisibleService(ISateliteVisibleService sateliteVisibleService) {
		this.sateliteVisibleService = sateliteVisibleService;
	}

}
