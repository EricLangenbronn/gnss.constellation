package fr.gnss.constellation.ouranos.api.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.api.service.ServiceLocator;
import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;

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

	private String getHeaderParamAccept(String accept) {
		if (accept != null && accept.contains(MediaType.APPLICATION_XML)) {
			accept = MediaType.APPLICATION_XML;
		} else {
			accept = MediaType.APPLICATION_JSON;
		}

		return accept;

	}

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Path("/{version}/visibleSat")
	public Response getVisibleSatelite(@HeaderParam("Accept") String accept, @PathParam("version") final String version,
			@QueryParam("requete") String p_contenu) throws BusinessException, TechnicalException {

		accept = this.getHeaderParamAccept(accept);

		List<SateliteTimeCoordinate> satelitesVisible = ServiceLocator.getServiceFactory().getFluxService()
				.sateliteVisible(version, p_contenu);

		return Response.ok(satelitesVisible, accept).build();
	}

}
