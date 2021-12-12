package fr.gnss.constellation.ouranos.api.controller;

import fr.gnss.constellation.ouranos.api.controller.dto.VisibleSatParamQueryParamDto;
import fr.gnss.constellation.ouranos.api.controller.mapper.BeanParamToVisibleSatParamDTOMapper;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.ISateliteVisibleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/visibleSat/bySatellite", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated
@RequiredArgsConstructor
public class GetResourcesVisibleSatBySatController {

    private final ISateliteVisibleService sateliteVisibleService;

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds visible satellite order by satellite id", responses = {})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Given visible satellite found")})
    public List<SatelliteTimeCoordinate<SphericalCoordinate>> getVisiblesatelliteBySatellite(
            @Parameter(description = "submission request", required = true) @Valid @NotNull VisibleSatParamQueryParamDto visibleSatParam) {

        return this.sateliteVisibleService.getSatelliteVisibleByPeriod(BeanParamToVisibleSatParamDTOMapper.beanVisibleSatParamToDTO(visibleSatParam));
    }
}
