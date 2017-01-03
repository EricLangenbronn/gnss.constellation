package fr.gnss.constellation.ouranos.service.flux;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
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
	public List<SateliteTimeCoordinate> sateliteVisible(String version, String requete)
			throws TechnicalException, BusinessException {

		this.bindingXmlService = BindingXMLService.getInstance();
		InputStream is = new ByteArrayInputStream(requete.getBytes(Charset.forName("UTF-8")));
		VisibleSateliteRequest request = this.bindingXmlService.mapXml2Object(is, VisibleSateliteRequest.class);

		LocalDateTime dateDebut = request.getStartDateOfMeasure().toGregorianCalendar().toZonedDateTime()
				.toLocalDateTime();
		LocalDateTime dateFin = request.getEndDateOfMeasure().toGregorianCalendar().toZonedDateTime().toLocalDateTime();

		GeodeticCoordinate geodeticCoordinate = XsdWrapper.wrapGeodeticCoordindate(request.getGroundStation());

		double elevationMask = request.getElevationMask();

		List<SateliteTimeCoordinate> satelitesVisible = sateliteVisibleService.getSateliteVisible(geodeticCoordinate,
				elevationMask, dateDebut, dateFin);

		return satelitesVisible;

	}

	public void setSateliteVisibleService(ISateliteVisibleService sateliteVisibleService) {
		this.sateliteVisibleService = sateliteVisibleService;
	}

}
