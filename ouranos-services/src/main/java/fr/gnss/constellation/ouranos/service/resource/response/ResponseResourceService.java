package fr.gnss.constellation.ouranos.service.resource.response;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;
import fr.gnss.constellation.ouranos.service.resource.HttpHeaderType;

public class ResponseResourceService implements IResponseResourceService {

	@Override
	public String getSateliteVisible(HttpHeaderType mediaType, List<SateliteTimeCoordinate> sateliteVisible)
			throws TechnicalException, BusinessException {

		String response = "fluxResponse";

		switch (mediaType) {
		case xml:
			response += "xml";
			break;
		case json:
			response += "json";
			break;
		default:
			throw new BusinessException("mediaType non reconnu");
		}
		return response;
	}

}
