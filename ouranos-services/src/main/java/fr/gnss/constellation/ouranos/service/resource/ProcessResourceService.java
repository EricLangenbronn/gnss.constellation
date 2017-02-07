package fr.gnss.constellation.ouranos.service.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.ISateliteVisibleService;
import fr.gnss.constellation.ouranos.service.resource.request.IRequestResourceService;
import fr.gnss.constellation.ouranos.service.resource.response.IResponseResourceService;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public class ProcessResourceService implements IProcessResourceService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessResourceService.class);

	private IRequestResourceService requestResourceService;
	private IResponseResourceService responseResourceService;
	private ISateliteVisibleService sateliteVisibleService;

	@Override
	public String processSateliteVisible(HttpHeaderType contentType, HttpHeaderType mediaType, String request,
			String version) throws TechnicalException, BusinessException {

		VisibleSateliteRequest visibleSateliteRequeste = this.requestResourceService
				.getRequestSateliteVisible(contentType, version, request);

		List<SateliteTimeCoordinate> sateliteVisible = this.sateliteVisibleService
				.getSateliteVisible(visibleSateliteRequeste);

		Map<String, Object> fluxInformations = new HashMap<String, Object>();
		fluxInformations.put("satelitesVisible", sateliteVisible);
		fluxInformations.put("mediaType", mediaType);

		Version v = ApiVersionUtil.getInstance().getVersion(version);
		String response = this.responseResourceService.getSateliteVisible("satellite-visible", v, fluxInformations);

		return response;
	}

	public void setSateliteVisibleService(ISateliteVisibleService sateliteVisibleService) {
		this.sateliteVisibleService = sateliteVisibleService;
	}

	public void setRequestResourceService(IRequestResourceService requestResourceService) {
		this.requestResourceService = requestResourceService;
	}

	public void setResponseResourceService(IResponseResourceService responseResourceService) {
		this.responseResourceService = responseResourceService;
	}

}
