package fr.gnss.constellation.ouranos.librairy.almanach.sp3;

import fr.gnss.constellation.ouranos.librairy.coordinate.ICoordinate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TimeCoordinateSatellitePosition<T extends ICoordinate> {

  // -------------------- Propriétés de la classe --------------------

  private final LocalDateTime epochHeaderRecord;
  private final Map<String, SatellitePosition<T>> satellites;

  // ------------------------ Constructeur(s) ------------------------

  public TimeCoordinateSatellitePosition(LocalDateTime epochHeaderRecord) {
    this.epochHeaderRecord = epochHeaderRecord;
    this.satellites = new HashMap<>();
  }

  // ----------------------- Methodes internes -----------------------

  public void addSatellite(String key, SatellitePosition<T> satelite) {
    this.satellites.put(key, satelite);
  }

  public LocalDateTime getEpochHeaderRecord() {
    return epochHeaderRecord;
  }

  public Map<String, SatellitePosition<T>> getSatellites() {
    return satellites;
  }

  // -------------------- Methodes de l'interface --------------------

  @Override
  public String toString() {
    return "SatelliteTimeCoordinate [epochHeaderRecord=" + epochHeaderRecord + ", satellites=" + satellites + "]";
  }

}
