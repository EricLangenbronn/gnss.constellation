package fr.gnss.constellation.ouranos.service.resource;

import fr.gnss.constellation.ouranos.service.process.satelitevisible.dto.VisibleSateliteRequestDto;

public interface IProcessResourceService {

    StringBuffer processSatelliteVisibleByPeriod(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version);

    StringBuffer processSatelliteVisibleBySatellite(ResourceType contentType, VisibleSateliteRequestDto visibleSatBean, String version);

}
