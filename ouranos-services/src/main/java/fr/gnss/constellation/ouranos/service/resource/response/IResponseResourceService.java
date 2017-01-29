package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.service.resource.HttpHeaderType;

public interface IResponseResourceService {

	String getSateliteVisible(HttpHeaderType mediaType, List<SateliteTimeCoordinate> sateliteVisible)
			throws TechnicalException, BusinessException;
}
