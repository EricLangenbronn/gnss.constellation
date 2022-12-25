package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public class SatelliteCoordinate<T extends ICoordinate> {

	// -------------------- Propriétés de la classe --------------------

	private String id;
	private Map<LocalDateTime, SatellitePosition<T>> satellites;

	// ------------------------ Constructeur(s) ------------------------

	public SatelliteCoordinate(String id) {
		this.id = id;
		this.satellites = new HashMap<>();
	}

	// ----------------------- Methodes internes -----------------------

	public void addSatellite(LocalDateTime key, SatellitePosition<T> satelite) {
		this.satellites.put(key, satelite);
	}

	public String getId() {
		return this.id;
	}

	public Map<LocalDateTime, SatellitePosition<T>> getSatellites() {
		return satellites;
	}

	// -------------------- Methodes de l'interface --------------------

	@Override
	public String toString() {
		return "SatelliteCoordinate [id=" + id + ", satellites=" + satellites + "]";
	}

}
