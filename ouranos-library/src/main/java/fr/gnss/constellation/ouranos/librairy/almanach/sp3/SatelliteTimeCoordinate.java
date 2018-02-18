package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;

public class SatelliteTimeCoordinate<T extends ICoordinate> {

	private LocalDateTime epochHeaderRecord;
	private Map<String, SatellitePosition<T>> satellites;

	public SatelliteTimeCoordinate(LocalDateTime epochHeaderRecord) {
		this.epochHeaderRecord = epochHeaderRecord;
		this.satellites = new HashMap<>();
	}

	public void addSatellite(String key, SatellitePosition<T> satelite) {
		this.satellites.put(key, satelite);
	}

	public LocalDateTime getEpochHeaderRecord() {
		return epochHeaderRecord;
	}

	public Map<String, SatellitePosition<T>> getSatellites() {
		return satellites;
	}

	@Override
	public String toString() {
		return "SatelliteTimeCoordinate [epochHeaderRecord=" + epochHeaderRecord + ", satellites=" + satellites + "]";
	}

}