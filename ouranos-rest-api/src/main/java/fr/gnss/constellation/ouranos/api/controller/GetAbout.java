package fr.gnss.constellation.ouranos.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/about", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GetAbout {

    @GetMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
    @ResponseBody
    @Operation(summary = "About")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "About information")})
    public ResponseEntity<String> getVisiblesatelliteByPeriodBeanParamXml() {

        String versionService = "Version Services : " + "todo";
        String versionWebapp = "Version Webapps : " + "todo";

        return new ResponseEntity<>("<root>" + versionService + "\n" + versionWebapp + "</root>", HttpStatus.OK);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    @ResponseBody
    @Operation(summary = "About")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "About information")})
    public ResponseEntity<String> getVisiblesatelliteByPeriodBeanParamJson() {

        String versionService = "Version Services : " + "todo";
        String versionWebapp = "Version Webapps : " + "todo";

        return new ResponseEntity<>("{" + versionService + "\n" + versionWebapp + "}", HttpStatus.OK);
    }
}
