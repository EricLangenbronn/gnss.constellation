package fr.gnss.constellation.ouranos.service.resource.request;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.HttpHeaderType;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public interface IRequestResourceService {

	public VisibleSateliteRequest getRequestSateliteVisible(HttpHeaderType contentType, String version, String requete)
			throws TechnicalException, BusinessException;

}
