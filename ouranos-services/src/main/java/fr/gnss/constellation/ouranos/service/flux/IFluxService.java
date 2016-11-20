package fr.gnss.constellation.ouranos.service.flux;

import java.util.List;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.SateliteTimeCoordinate;

public interface IFluxService {

	public List<SateliteTimeCoordinate> sateliteVisible(String version, String requete)
			throws TechnicalException, BusinessException;

}
