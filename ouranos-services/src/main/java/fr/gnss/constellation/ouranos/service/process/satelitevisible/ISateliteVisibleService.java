package fr.gnss.constellation.ouranos.service.process.satelitevisible;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

public interface ISateliteVisibleService {

	public List<SateliteTimeCoordinate> getSateliteVisible(VisibleSateliteRequest p_request) throws TechnicalException, BusinessException;
}
