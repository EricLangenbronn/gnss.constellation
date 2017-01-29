package fr.gnss.constellation.ouranos.service.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.ISateliteVisibleService;
import fr.gnss.constellation.ouranos.service.resource.request.IRequestResourceService;
import fr.gnss.constellation.ouranos.service.resource.response.IResponseResourceService;
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
	public String processSateliteVisible(HttpHeaderType contentType, HttpHeaderType mediaType, String request, String version)
			throws TechnicalException, BusinessException {

		VisibleSateliteRequest visibleSateliteRequeste = this.requestResourceService
				.getRequestSateliteVisible(contentType, version, request);

		List<SateliteTimeCoordinate> sateliteVisible = this.sateliteVisibleService
				.getSateliteVisible(visibleSateliteRequeste);

		String response = this.responseResourceService.getSateliteVisible(mediaType, sateliteVisible);

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
