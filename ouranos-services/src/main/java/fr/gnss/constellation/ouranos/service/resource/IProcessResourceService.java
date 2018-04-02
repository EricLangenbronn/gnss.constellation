package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.bean.VisibleSateliteRequestBean;

public interface IProcessResourceService {

	String processSatelliteVisibleByPeriod(ResourceType contentType, VisibleSateliteRequestBean visibleSatBean, String version)
			throws TechnicalException, BusinessException;

	String processSatelliteVisibleBySatellite(ResourceType contentType, VisibleSateliteRequestBean visibleSatBean, String version)
			throws TechnicalException, BusinessException;

}
