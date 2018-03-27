package fr.gnss.constellation.ouranos.api.resource;

import javax.annotation.PostConstruct;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.gnss.constellation.ouranos.api.resource.bean.VisibleSatParam;
import fr.gnss.constellation.ouranos.api.resource.mapper.BeanParamToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.api.resource.mapper.RequestXmlToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.api.service.ServiceLocator;
import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.service.resource.IProcessResourceService;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/visibleSat/{version}/byPeriod")
@Api(value = "/visibleSat", description = "Operations about visible satellite", consumes = "application/json, application/xml")
public class GetResourcesVisibleSatByPeriod {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetResourcesVisibleSatByPeriod.class);

	@Autowired
	@Qualifier("processResourceService")
	private IProcessResourceService processResourceService;

	@PostConstruct
	public void init() {
		processResourceService = ServiceLocator.getContexte().getBean(IProcessResourceService.class);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
	public Response getVisiblesatelliteByPeriodBeanParam(@PathParam("version") final String version,
			@BeanParam VisibleSatParam visibleSatParam) throws BusinessException, TechnicalException {

		VisibleSateliteRequestBean visibleSatBean = BeanParamToVisibleSatParamDTOMapper
				.beanVisibleSatParamToDTO(visibleSatParam);

		String response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.xml, visibleSatBean,
				version);

		return Response.ok(response).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
	@ApiOperation(value = "Finds visible satellite order by period", httpMethod = "POST", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found") })
	public Response postVisiblesatelliteByPeriodXml(
			@ApiParam(value = "version of the api", required = true) @PathParam("version") final String version,
			@ApiParam(value = "submission request on xml", required = true) VisibleSateliteRequest p_contenu)
			throws BusinessException, TechnicalException {

		VisibleSateliteRequestBean visibleSatBean = RequestXmlToVisibleSatParamDTOMapper
				.beanVisibleSateliteRequestToDTO(p_contenu);

		String response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.xml, visibleSatBean,
				version);

		return Response.ok(response).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
	@ApiOperation(value = "Finds visible satellite order by period", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found") })
	public Response getVisiblesatelliteByPeriodXml(
			@ApiParam(value = "version of the api", required = true) @PathParam("version") final String version,
			@ApiParam(value = "submission request on xml", required = true) VisibleSateliteRequest p_contenu)
			throws BusinessException, TechnicalException {

		VisibleSateliteRequestBean visibleSatBean = RequestXmlToVisibleSatParamDTOMapper
				.beanVisibleSateliteRequestToDTO(p_contenu);
		String response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.xml, visibleSatBean,
				version);

		return Response.ok(response).build();
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@ApiOperation(value = "Finds visible satellite order by period", httpMethod = "POST", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found") })
	public Response postVisiblesatelliteByPeriodJson(
			@ApiParam(value = "version of the api", required = true) @PathParam("version") final String version,
			@ApiParam(value = "submission request on json", required = true) String p_contenu)
			throws BusinessException, TechnicalException {

		String response = "";
		if (StringUtils.isNotBlank(p_contenu)) {
			response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.json, p_contenu, version);
		} else {
			String message = "La requête doit être présente.";
			LOGGER.error(message);
			throw new BusinessException(message);
		}

		return Response.ok(response).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@ApiOperation(value = "Finds visible satellite order by period", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found on json") })
	public Response getVisiblesatelliteByPeriodJson(
			@ApiParam(value = "version of the api", required = true) @PathParam("version") final String version,
			@ApiParam(value = "submission request on json", required = true) @QueryParam("requete") String p_contenu)
			throws BusinessException, TechnicalException {

		String response = "";
		if (StringUtils.isNotBlank(p_contenu)) {
			response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.json, p_contenu, version);
		} else {
			String message = "La requête doit être présente.";
			LOGGER.error(message);
			throw new BusinessException(message);
		}

		return Response.ok(response).build();
	}

}
