package fr.gnss.constellation.ouranos.services;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.bean.Parameters;
import fr.gnss.constellation.ouranos.services.bean.Resultats;

public interface OuranosExecutionService {

	public void setParameters(Parameters parameters);

	public void launchExecution(Resultats resultat) throws TechnicalException, BusinessException;
}
