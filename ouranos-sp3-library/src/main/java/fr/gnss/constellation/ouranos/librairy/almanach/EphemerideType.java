package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.StringUtils;

public enum EphemerideType {
    igl("IGS final solution combination (GLONASS only)"), igr("IGS rapid solution combination"), igs("IGS final solution combination"), igu("IGS ultra-rapid solution combination");

    private String description;

    EphemerideType(String description) {
        this.description = description;
    }

    /**
     * Returns an EphemerideType with a value represented by the specified string.
     *
     * @param type - the string to be converted
     * @return the string representation of the specified EphemerideType
     * @throws IllegalArgumentException - if the string is null, empty or not references
     */
    public static EphemerideType stringToEphemerideType(String type) {
        if (StringUtils.isBlank(type)) {
            String message = "Le type de l'orbite doit être renseigné. [type=" + type + "]";
            throw new IllegalArgumentException(message);
        }

        if (type.length() != 3) {
            throw new IllegalArgumentException("La taille du paramètre pour un EphemerideType doit être de 3 [type=" + type + "]");
        }

        return switch (type) {
            case "igl" -> EphemerideType.igl;
            case "igr" -> EphemerideType.igr;
            case "igs" -> EphemerideType.igs;
            case "igu" -> EphemerideType.igu;
            default -> {
                String message = "Le paramètre ne peut pas être convertit en EphemerideType,  EphemerideType inexistant [type=" + type + "]";
                throw new IllegalArgumentException(message);
            }
        };
    }
}
