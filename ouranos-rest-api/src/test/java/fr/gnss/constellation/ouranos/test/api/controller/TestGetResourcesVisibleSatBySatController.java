package fr.gnss.constellation.ouranos.test.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
