package fr.gnss.constellation.ouranos.librairy.almanach;

import org.apache.commons.lang3.StringUtils;

public enum EphemerideType {
    igl, igr, igs, igu;

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

        EphemerideType res;
        switch (type) {
            case "igl":
                res = EphemerideType.igl;
                break;
            case "igr":
                res = EphemerideType.igr;
                break;
            case "igs":
                res = EphemerideType.igs;
                break;
            case "igu":
                res = EphemerideType.igu;
                break;
            default:
                String message = "Le paramètre ne peut pas être convertit en EphemerideType,  EphemerideType inexistant [type=" + type + "]";
                throw new IllegalArgumentException(message);
        }

        return res;
    }
}
