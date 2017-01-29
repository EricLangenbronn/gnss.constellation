package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface IProcessResourceService {

	String processSateliteVisible(HttpHeaderType contentType, HttpHeaderType mediaType, String request, String version)
			throws TechnicalException, BusinessException;

}
