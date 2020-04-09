package fr.gnss.constellation.ouranos.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/about")
@Api(value = "/about", description = "About", consumes = "application/json, application/xml")
public class GetAbout {

	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "About", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "About information") })
	public ResponseEntity<String> getVisiblesatelliteByPeriodBeanParamXml() throws Exception {

		String versionService = "Version Services : " + "todo";
		String versionWebapp = "Version Webapps : " + "todo";

		return new ResponseEntity<>("<root>" + versionService + "\n" + versionWebapp + "</root>", HttpStatus.OK);
	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "About", httpMethod = "GET", notes = "", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "About information") })
	public ResponseEntity<String> getVisiblesatelliteByPeriodBeanParamJson() throws Exception {

		String versionService = "Version Services : " + "todo";
		String versionWebapp = "Version Webapps : " + "todo";

		return new ResponseEntity<>("{" + versionService + "\n" + versionWebapp + "}", HttpStatus.OK);
	}
}
