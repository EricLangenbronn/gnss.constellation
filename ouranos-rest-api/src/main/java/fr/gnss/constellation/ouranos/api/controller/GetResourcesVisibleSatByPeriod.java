package fr.gnss.constellation.ouranos.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gnss.constellation.ouranos.api.controller.domain.VisibleSatParamQueryParam;
import fr.gnss.constellation.ouranos.api.controller.mapper.BeanParamToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;
import fr.gnss.constellation.ouranos.service.resource.IProcessResourceService;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/visibleSat/{version}/byPeriod")
@Api(value = "/visibleSat", description = "Operations about visible satellite", consumes = "application/json, application/xml")
public class GetResourcesVisibleSatByPeriod {

	@Autowired
	@Qualifier("processResourceService")
	private IProcessResourceService processResourceService;

	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ApiOperation(value = "Finds visible satellite order by period of time", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found") })
	public ResponseEntity<StringBuffer> getVisiblesatelliteByPeriodBeanParamXml(@PathVariable("version") final String version,
			@ApiParam(value = "submission request", required = true) @Valid VisibleSatParamQueryParam visibleSatParam) throws Exception {

		VisibleSateliteRequestDto visibleSatBean = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(visibleSatParam);

		StringBuffer response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.xml, visibleSatBean, version);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Finds visible satellite order by period of time", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Given visible satellite found") })
	public ResponseEntity<StringBuffer> getVisiblesatelliteByPeriodBeanParamJson(@PathVariable("version") final String version,
			@ApiParam(value = "submission request", required = true) @Valid VisibleSatParamQueryParam visibleSatParam) throws Exception {

		VisibleSateliteRequestDto visibleSatBean = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(visibleSatParam);

		StringBuffer response = processResourceService.processSatelliteVisibleByPeriod(ResourceType.json, visibleSatBean, version);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
