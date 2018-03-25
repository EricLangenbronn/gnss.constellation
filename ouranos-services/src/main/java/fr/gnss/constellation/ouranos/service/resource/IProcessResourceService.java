package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.service.resource.request.bean.VisibleSatParamDTO;

public interface IProcessResourceService {

	String processSatelliteVisibleByPeriod(ResourceType contentType, String request, String version) throws TechnicalException, BusinessException;

	String processSatelliteVisibleBySatellite(ResourceType contentType, String request, String version) throws TechnicalException, BusinessException;

	String processSatelliteVisibleByPeriod(String version, VisibleSatParamDTO visibleSatParamDTO) throws TechnicalException, BusinessException;

}
