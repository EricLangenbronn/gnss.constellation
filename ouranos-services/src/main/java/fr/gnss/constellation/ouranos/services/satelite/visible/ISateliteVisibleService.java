package fr.gnss.constellation.ouranos.services.satelite.visible;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.xsd.VisibleSateliteRequest;

public interface ISateliteVisibleService {

	public List<Entry<LocalDateTime, List<Satelite>>> getSateliteVisible(VisibleSateliteRequest p_requete)
			throws TechnicalException, BusinessException;
}
