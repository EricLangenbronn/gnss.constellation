package fr.gnss.constellation.ouranos.api.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.api.service.ServiceFactory;
import fr.gnss.constellation.ouranos.api.service.ServiceLocator;
import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.coordinate.GeodeticCoordinate;
import fr.gnss.constellation.ouranos.model.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;

@Path("/")
public class GetResources {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(GetResources.class);

	/**
	 * Creates a new instance of RelationResource
	 */
	public GetResources() {
		LOGGER.info("charger");
	}

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) throws BusinessException, TechnicalException {
		LOGGER.info("param");
		String output = "Jersey say : " + msg;

		LocalDateTime p_startOfMeasure = LocalDateTime.parse("2013-12-22T00:00", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime p_endOfMeasure = LocalDateTime.parse("2013-12-22T23:45", DateTimeFormatter.ISO_DATE_TIME);

		OuranosExecutionService ouranosExecService = ServiceLocator.getServiceFactory().getOuranosExecutionService();
		GeodeticCoordinate stationGeo = new GeodeticCoordinate(Math.toRadians(38.889139), Math.toRadians(-77.049),
				130.049);

		ouranosExecService.setParameters(new Parameters(stationGeo, 15, p_startOfMeasure, p_endOfMeasure));
		ouranosExecService.launchExecution();
		Resultats res = ouranosExecService.getResultats();
		LOGGER.info(res.toString());
		
		
		return Response.status(200).entity(output).build();

	}

}
