package fr.gnss.constellation.ouranos.wrapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;
import fr.gnss.constellation.ouranos.model.Resultats;

public class WrapperResultats {

	public static fr.gnss.constellation.ouranos.bean.Resultats wrapperResultats(Resultats res) {
		fr.gnss.constellation.ouranos.bean.Resultats wrappeRes = new fr.gnss.constellation.ouranos.bean.Resultats();

		for (Entry<LocalDateTime, List<Satelite>> elem : res.getVisibleSats()) {
			wrappeRes.addElem(elem.getKey().toString(), elem.getValue().size());
		}

		return wrappeRes;
	}
}
