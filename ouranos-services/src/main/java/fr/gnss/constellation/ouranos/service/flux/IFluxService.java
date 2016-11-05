package fr.gnss.constellation.ouranos.service.flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3SateliteInformation;

public interface IFluxService {

	public void sateliteVisible(String version, String requete)
			throws TechnicalException, BusinessException;

}
