package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.StringUtils;

public enum OrbitType {
  clk, cls, erp, sp3, sum;

  private static final int LENGTH_ORBIT_TYPE = 3;

  /**
   * Returns an OrbitType with a value represented by the specified string.
   *
   * @param orbitType - the string to be converted
   * @return the string representation of the specified OrbitType
   * @throws IllegalArgumentException - if the string is null, empty or not references
   */
  public static OrbitType stringToOrbitType(String orbitType) {
    if (StringUtils.isBlank(orbitType)) {
      throw new IllegalArgumentException("Le type de l'orbite doit être renseigné. [type=" + orbitType + "]");
    }

    if (orbitType.length() != LENGTH_ORBIT_TYPE) {
      throw new IllegalArgumentException("La taille du paramètre pour un OrbitType doit être de 3");
    }

    return switch (orbitType) {
      case "clk" -> OrbitType.clk;
      case "cls" -> OrbitType.cls;
      case "erp" -> OrbitType.erp;
      case "sp3" -> OrbitType.sp3;
      case "sum" -> OrbitType.sum;
      default -> throw new IllegalArgumentException(
          "Le paramètre ne peut pas être convertit en OrbitType,  OrbitType inexistant [type=" + orbitType + "]"
      );
    };
  }
}
