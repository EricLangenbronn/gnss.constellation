package fr.gnss.constellation.ouranos.api.resource;

import javax.annotation.PostConstruct;
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
import fr.gnss.constellation.ouranos.service.resource.HttpHeaderType;
import fr.gnss.constellation.ouranos.service.resource.IProcessResourceService;

@Path("/")
public class GetResources {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(GetResources.class);

	private IProcessResourceService processResourceService;

	@PostConstruct
	public void init() {
		processResourceService = ServiceLocator.getContexte().getBean(IProcessResourceService.class);
	}

	private HttpHeaderType getHeaderParamType(String accept) {
		HttpHeaderType mediaType = null;

		if (accept == null || accept.contains(MediaType.APPLICATION_XML)) {
			mediaType = HttpHeaderType.xml;
		} else {
			mediaType = HttpHeaderType.json;
		}

		return mediaType;
	}

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	@Path("/{version}/visibleSat")
	public Response getVisibleSatelite(@HeaderParam("Content-Type") String contentType,
			@HeaderParam("Accept") String accept, @PathParam("version") final String version,
			@QueryParam("requete") String p_contenu) throws BusinessException, TechnicalException {
		
		HttpHeaderType acceptContentType = this.getHeaderParamType(contentType);
		HttpHeaderType acceptMediaType = this.getHeaderParamType(accept);

		String response = processResourceService.processSateliteVisible(acceptContentType, acceptMediaType, p_contenu,
				version);

		return Response.ok(response, acceptMediaType.getType()).build();
	}
}
