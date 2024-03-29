package fr.gnss.constellation.ouranos.domain.satellite;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Builder
@Getter
@Slf4j
public class Satellite {

  @NonNull
  private SatelliteId satelliteId;

  @NonNull
  private SortedMap<LocalDateTime, Position> positionsByTime;

  public SortedMap<LocalDateTime, Position> positionsVisible(double elevationMask) {
    return Optional.of(positionsByTime.entrySet()).stream().flatMap(Collection::stream)
        .filter(entry -> {
          log.debug(String.format("Satellite : %s", this.satelliteId.getValue()));
          return entry.getValue().isVisible(elevationMask);
        })
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, TreeMap::new));
  }

  public void addPositionIfAbsent(LocalDateTime localDateTime, Position position) {
    this.positionsByTime.putIfAbsent(localDateTime, position);
  }

  public static class SatelliteId {

    private static final Pattern patternSatelliteId = Pattern.compile("[GRECJ]([0-9]{2})");

    private final String satelliteId;

    public SatelliteId(String satelliteId) {

      Matcher matcherSatelliteId = patternSatelliteId.matcher(satelliteId);
      if (StringUtils.isNotBlank(satelliteId) && matcherSatelliteId.matches()) {
        this.satelliteId = satelliteId;
      } else {
        throw new IllegalArgumentException(
            String.format("Le nom du satellite ne correspond pas au pattern [pattern=%s, satelliteId=%s] "
                , patternSatelliteId.pattern(), satelliteId));
      }
    }

    public String getValue() {
      return satelliteId;
    }
  }

  public static class Position {
    private final RadialDistance radialDistance;
    private final PolarAngle polarAngle;
    private final AzimuthalAngle azimuthalAngle;

    public Position(RadialDistance radialDistance, PolarAngle polarAngle, AzimuthalAngle azimuthalAngle) {

      if (Objects.isNull(radialDistance) || Objects.isNull(polarAngle) || Objects.isNull(azimuthalAngle)) {
        throw new IllegalArgumentException("Les paramètres de la position ne peuvent être null");
      }

      this.radialDistance = radialDistance;
      this.polarAngle = polarAngle;
      this.azimuthalAngle = azimuthalAngle;
    }

    public RadialDistance getRadialDistance() {
      return radialDistance;
    }

    public PolarAngle getPolarAngle() {
      return polarAngle;
    }

    public AzimuthalAngle getAzimuthalAngle() {
      return azimuthalAngle;
    }

    private boolean isVisible(double elevationMask) {

      double correctionElevationMask = Math.max(elevationMask, 0.0);
      // sphCoord[2] = azimuth, azimuth doit être positif sinon c'est
      // que le satelite est pas du bon coté de la terre
      if (this.getAzimuthalAngle().getValue() >= 0) {
        // 3.1415 / 2 rad = 90.0°, on vérifie qu'il est entre 90° et 0°
        if ((this.getPolarAngle().getValue() >= correctionElevationMask) && (this.getPolarAngle().getValue() < (Math.PI / 2.0))) {
          log.debug(String.format("Visible [azimuth=%s,elevation=%s]", Math.toDegrees(this.getAzimuthalAngle().getValue())
              , Math.toDegrees(this.getPolarAngle().getValue())));
          return true;
        } else {
          log.debug(String.format("Non visible [azimuth=%s,elevation=%s]", Math.toDegrees(this.getAzimuthalAngle().getValue())
              , Math.toDegrees(this.getPolarAngle().getValue())));
        }
      }

      return false;
    }

    public static class RadialDistance {

      private final Double radialDistance;

      public RadialDistance(Double radialDistance) {
        if (radialDistance == null || radialDistance < 0.0) {
          throw new IllegalArgumentException(
              String.format("Attention la distance radial est inférieur à zéro [radialDistance=%s]", radialDistance));
        }

        this.radialDistance = radialDistance;
      }

      public Double getValue() {
        return radialDistance;
      }
    }

    public static class PolarAngle {

      private final Double polarAngle;

      public PolarAngle(Double polarAngle) {
        if (polarAngle == null || (polarAngle < -(Math.PI / 2.0) || polarAngle > (Math.PI / 2.0))) {
          throw new IllegalArgumentException(
              String.format("L'inclinaison (latitude) doit être compris entre [%s, %s] radian, [polarAngle=%s]", -(Math.PI / 2.0)
                  , (Math.PI / 2.0), polarAngle)
          );
        }

        this.polarAngle = polarAngle;
      }

      public Double getValue() {
        return polarAngle;
      }
    }

    public static class AzimuthalAngle {

      private final Double azimuthalAngle;

      public AzimuthalAngle(Double azimuthalAngle) {

        if (azimuthalAngle == null || (azimuthalAngle < -Math.PI || azimuthalAngle > Math.PI)) {
          throw new IllegalArgumentException(
              String.format("L'azimuth (longitude) doit être compris entre [%s,%s] radian [azimuthalAngle=%s]", -Math.PI, Math.PI
                  , azimuthalAngle)
          );
        }

        this.azimuthalAngle = azimuthalAngle;
      }

      public Double getValue() {
        return azimuthalAngle;
      }
    }
  }
}
