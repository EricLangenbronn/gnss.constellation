package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.StringUtils;

public enum OrbitType {
    clk, cls, erp, sp3, sum;

    /**
     * Returns an OrbitType with a value represented by the specified string.
     *
     * @param p_type - the string to be converted
     * @return the string representation of the specified OrbitType
     * @throws IllegalArgumentException - if the string is null, empty or not references
     */
    public static OrbitType stringToOrbitType(String p_type) {
        if (StringUtils.isBlank(p_type)) {
            String message = "Le type de l'orbite doit être renseigné. [type=" + p_type + "]";
            throw new IllegalArgumentException(message);
        }

        if (p_type.length() != 3) {
            String message = "La taille du paramètre pour un OrbitType doit être de 3";
            throw new IllegalArgumentException(message);
        }

        return switch (p_type) {
            case "clk" -> OrbitType.clk;
            case "cls" -> OrbitType.cls;
            case "erp" -> OrbitType.erp;
            case "sp3" -> OrbitType.sp3;
            case "sum" -> OrbitType.sum;
            default ->
                    throw new IllegalArgumentException("Le paramètre ne peut pas être convertit en OrbitType,  OrbitType inexistant [type=" + p_type + "]");
        };
    }
}
