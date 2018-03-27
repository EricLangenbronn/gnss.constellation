package fr.gnss.constellation.ouranos.service.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteCoordinate;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SatelliteTimeCoordinate;
import fr.gnss.constellation.ouranos.librairy.coordinate.SphericalCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.ISateliteVisibleService;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;
import fr.gnss.constellation.ouranos.service.resource.request.IRequestResourceService;
import fr.gnss.constellation.ouranos.service.resource.response.IResponseResourceService;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

@Service("processResourceService")
public class ProcessResourceService implements IProcessResourceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessResourceService.class);

	// -------------------- Services --------------------

	@Autowired
	private IRequestResourceService requestResourceService;

	@Autowired
	private IResponseResourceService responseResourceService;

	@Autowired
	private ISateliteVisibleService sateliteVisibleService;

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String processSatelliteVisibleByPeriod(ResourceType resourceType, String request, String version) throws TechnicalException, BusinessException {

		VisibleSateliteRequest visibleSateliteRequeste = this.requestResourceService.getRequestSateliteVisible(resourceType, version, request);

		List<SatelliteTimeCoordinate<SphericalCoordinate>> sateliteVisible = this.sateliteVisibleService.getSatelliteVisibleByPeriod(visibleSateliteRequeste);

		Map<String, Object> fluxInformations = new HashMap<String, Object>();
		fluxInformations.put("satellitesVisible", sateliteVisible);

		Version v = ApiVersionUtil.getInstance().getVersion(version);
		String response = this.responseResourceService.getFluxSateliteVisible("satellite-visible-byperiod", resourceType, v, fluxInformations);

		return response;
	}

	@Override
	public String processSatelliteVisibleBySatellite(ResourceType contentType, String request, String version) throws TechnicalException, BusinessException {

		VisibleSateliteRequest visibleSateliteRequeste = this.requestResourceService.getRequestSateliteVisible(contentType, version, request);

		List<SatelliteCoordinate<SphericalCoordinate>> sateliteVisible = this.sateliteVisibleService.getSatelliteVisibleBySatellite(visibleSateliteRequeste);

		Map<String, Object> fluxInformations = new HashMap<String, Object>();
		fluxInformations.put("satellitesVisible", sateliteVisible);

		Version v = ApiVersionUtil.getInstance().getVersion(version);
		String response = this.responseResourceService.getFluxSateliteVisible("satellite-visible-bysatellite", contentType, v, fluxInformations);

		return response;
	}

	@Override
	public String processSatelliteVisibleByPeriod(ResourceType contentType, VisibleSateliteRequestBean visibleSatBean, String version) throws TechnicalException, BusinessException {
		
		List<SatelliteCoordinate<SphericalCoordinate>> sateliteVisible = this.sateliteVisibleService.getSatelliteVisibleBySatellite(visibleSatBean);

		Map<String, Object> fluxInformations = new HashMap<String, Object>();
		fluxInformations.put("satellitesVisible", sateliteVisible);

		Version v = ApiVersionUtil.getInstance().getVersion(version);
		String response = this.responseResourceService.getFluxSateliteVisible("satellite-visible-bysatellite", contentType, v, fluxInformations);

		return response;
	}

}
