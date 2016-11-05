//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.11.05 à 11:02:54 PM CET 
//


package fr.gnss.constellation.ouranos.xsd;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour coordinateType.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="coordinateType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Cartesian"/&gt;
 *     &lt;enumeration value="GeodeticCoordinate"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "coordinateType", namespace = "http://www.ouranos.fr/ouranos/data/v10/types")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
public enum CoordinateType {

    @XmlEnumValue("Cartesian")
    CARTESIAN("Cartesian"),
    @XmlEnumValue("GeodeticCoordinate")
    GEODETIC_COORDINATE("GeodeticCoordinate");
    private final String value;

    CoordinateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CoordinateType fromValue(String v) {
        for (CoordinateType c: CoordinateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
