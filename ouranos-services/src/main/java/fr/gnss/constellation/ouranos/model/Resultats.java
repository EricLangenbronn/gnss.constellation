package fr.gnss.constellation.ouranos.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Satelite;

public class Resultats {

	private List<Entry<LocalDateTime, List<Satelite>>> visibleSats;

	public Resultats() {
		super();
	}

	public Resultats(List<Entry<LocalDateTime, List<Satelite>>> visibleSats) {
		super();
		this.visibleSats = visibleSats;
	}

	public List<Entry<LocalDateTime, List<Satelite>>> getVisibleSats() {
		return visibleSats;
	}

	public void setVisibleSats(List<Entry<LocalDateTime, List<Satelite>>> visibleSats) {
		this.visibleSats = visibleSats;
	}
}
