package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.ISateliteVisibleService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;
import fr.gnss.constellation.ouranos.service.resource.response.IFormatResponseResourceService;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("processResourceService")
@RequiredArgsConstructor
public class ProcessResourceService implements IProcessResourceService {

    // -------------------- Services --------------------

    private final IFormatResponseResourceService responseResourceService;
    private final ISateliteVisibleService sateliteVisibleService;

    // -------------------- Methodes de l'interface --------------------

    @Override
    public StringBuffer processSatelliteVisibleByPeriod(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version) {

        List<SatelliteTimeCoordinate<SphericalCoordinate>> sateliteVisible = this.sateliteVisibleService.getSatelliteVisibleByPeriod(visibleSatBean);

        Map<String, Object> fluxInformations = new HashMap<String, Object>();
        fluxInformations.put("satellitesVisible", sateliteVisible);

        Version v = ApiVersionUtil.getInstance().getVersion(version);
        return this.responseResourceService.getFluxSateliteVisible("satellite-visible-byperiod", contentType, v, fluxInformations);
    }

    @Override
    public StringBuffer processSatelliteVisibleBySatellite(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version) {

        List<SatelliteCoordinate<SphericalCoordinate>> sateliteVisible = this.sateliteVisibleService.getSatelliteVisibleBySatellite(visibleSatBean);

        Map<String, Object> fluxInformations = new HashMap<String, Object>();
        fluxInformations.put("satellitesVisible", sateliteVisible);

        Version v = ApiVersionUtil.getInstance().getVersion(version);
        return this.responseResourceService.getFluxSateliteVisible("satellite-visible-bysatellite", contentType, v, fluxInformations);
    }

}
