package fr.gnss.constellation.ouranos.librairy.almanach;

import java.util.Objects;

public enum Sp3FileType {
    a, b, c, d;

    /**
     * Returns an Sp3FileType with a value represented by the specified character.
     *
     * @param p_type - the character to be converted
     * @return the character representation of the specified Sp3FileType
     * @throws IllegalArgumentException - if the string is null, empty or not references
     */
    public static Sp3FileType stringToSp3FileType(Character p_type) {
        Objects.requireNonNull(p_type, "Le type de l'orbite doit être renseigné. [type=" + p_type + "]");

        return switch (p_type) {
            case 'a' -> Sp3FileType.a;
            case 'b' -> Sp3FileType.b;
            case 'c' -> Sp3FileType.c;
            case 'd' -> Sp3FileType.d;
            default ->
                    throw new IllegalArgumentException("Le paramètre ne peut pas être convertit en Sp3FileType,  Sp3FileType inexistant [type=" + p_type + "]");
        };
    }

}
