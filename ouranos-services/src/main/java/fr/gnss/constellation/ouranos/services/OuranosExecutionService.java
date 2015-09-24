package fr.gnss.constellation.ouranos.services;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.model.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;

public interface OuranosExecutionService {

	public void setParameters(Parameters parameters);

	public Resultats getResultats();
	
	public void setProcessComplet(boolean processComplet);
	
	public boolean isProcessComplet();

	public void launchExecution() throws TechnicalException, BusinessException;
}
