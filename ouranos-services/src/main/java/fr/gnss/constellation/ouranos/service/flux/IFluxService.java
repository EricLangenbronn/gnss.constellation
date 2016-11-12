package fr.gnss.constellation.ouranos.service.flux;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface IFluxService {

	public void sateliteVisible(String version, String requete)
			throws TechnicalException, BusinessException;

}
