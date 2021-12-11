package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.domain.VisibleSatParamQueryParam;
import fr.gnss.constellation.ouranos.api.controller.mapper.BeanParamToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;
import fr.gnss.constellation.ouranos.service.resource.IProcessResourceService;
import fr.gnss.constellation.ouranos.service.resource.ResourceType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/visibleSat/{version}/bySatellite", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated
@RequiredArgsConstructor
public class GetResourcesVisibleSatBySat {

    private final IProcessResourceService processResourceService;

    @GetMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @Operation(summary = "Finds visible satellite order by satellite id", responses = {})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Given visible satellite found")})
    public ResponseEntity<StringBuffer> getVisiblesatelliteBySatelliteXml(
            @Parameter(description = "version of the api", required = true) @PathVariable("version") @NotEmpty String version
            , @Parameter(description = "submission request", required = true) @Valid @NotNull VisibleSatParamQueryParam visibleSatParam) {

        VisibleSateliteRequestDto visibleSatBean = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(visibleSatParam);

        StringBuffer response = processResourceService.processSatelliteVisibleBySatellite(ResourceType.xml, visibleSatBean, version);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds visible satellite order by satellite id", responses = {})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Given visible satellite found")})
    public ResponseEntity<StringBuffer> getVisiblesatelliteBySatelliteJson(
            @Parameter(description = "version of the api", required = true) @PathVariable("version") @NotEmpty String version
            , @Parameter(description = "submission request", required = true) @Valid @NotNull VisibleSatParamQueryParam visibleSatParam) {

        VisibleSateliteRequestDto visibleSatBean = BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(visibleSatParam);

        StringBuffer response = processResourceService.processSatelliteVisibleBySatellite(ResourceType.json, visibleSatBean, version);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
