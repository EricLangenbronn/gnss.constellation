package fr.gnss.constellation.ouranos.test.api.controller;

import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.SateliteVisibleService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.domain.VisibleSateliteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@WebMvcTest(value = GetResourcesVisibleSatBySatController.class)
@ContextConfiguration(classes = {OuranosConfiguration.class})
public class TestGetResourcesVisibleSatBySatController {

    private PodamFactory factory = new PodamFactoryImpl();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SateliteVisibleService sateliteVisibleService;

    @Test
    public void test() throws Exception {
        when(sateliteVisibleService.getSatelliteVisibleBySatellite(any(VisibleSateliteRequest.class)))
                .thenReturn(Arrays.asList(factory.manufacturePojo(SatelliteCoordinate.class, SphericalCoordinate.class)));

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>();
        requestParams.add("latitude", "38.889139");
        requestParams.add("longitude", "-77.049");
        requestParams.add("altitude", "130.049");
        requestParams.add("startDateOfMeasure", "1387666800");
        requestParams.add("endDateOfMeasure", "1387753199");
        requestParams.add("elevationMask", "15.0");

        mockMvc.perform(get("/api/visibleSat/bySatellite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
 */
