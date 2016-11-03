package fr.gnss.constellation.ouranos.api.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.GET;
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
import fr.gnss.constellation.ouranos.services.impl.BindingXMLService;
import fr.gnss.constellation.ouranos.xsd.VisibleSateliteRequest;

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
	@Produces(MediaType.TEXT_XML + ";charset=UTF-8")
	@Path("/{version}/visibleSat")
	public Response getVisibleSatelite(@PathParam("version") final String version,
			@QueryParam("requete") String p_contenu) throws BusinessException, TechnicalException {

		BindingXMLService bindingXml = BindingXMLService.getInstance();
		InputStream is = new ByteArrayInputStream(p_contenu.getBytes());
		VisibleSateliteRequest request = bindingXml.mapXml2Object(is, VisibleSateliteRequest.class);

		ServiceLocator.getServiceFactory().getSateliteVisibleService().getSateliteVisible(request);

		return null;
	}

}
