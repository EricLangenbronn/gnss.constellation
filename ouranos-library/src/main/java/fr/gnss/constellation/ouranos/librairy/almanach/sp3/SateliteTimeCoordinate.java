package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SateliteTimeCoordinate {

	private LocalDateTime epochHeaderRecord;
	private Map<String, Sp3SateliteInformation> satelites;

	public SateliteTimeCoordinate(LocalDateTime epochHeaderRecord) {
		this.epochHeaderRecord = epochHeaderRecord;
		this.satelites = new HashMap<>();
	}

	public void addSatellite(Sp3SateliteInformation satelite) {
		this.satelites.put(satelite.getVehicleId(), satelite);
	}

	public LocalDateTime getEpochHeaderRecord() {
		return epochHeaderRecord;
	}

	public Map<String, Sp3SateliteInformation> getSatelites() {
		return satelites;
	}

	@Override
	public String toString() {
		return "SatelliteTimeCoordinate [epochHeaderRecord=" + epochHeaderRecord + ", satelites=" + satelites + "]";
	}

}
