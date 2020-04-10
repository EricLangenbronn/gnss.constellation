package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;

public interface IProcessResourceService {

	StringBuffer processSatelliteVisibleByPeriod(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version)
			throws TechnicalException, BusinessException;

	StringBuffer processSatelliteVisibleBySatellite(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version)
			throws TechnicalException, BusinessException;

}
