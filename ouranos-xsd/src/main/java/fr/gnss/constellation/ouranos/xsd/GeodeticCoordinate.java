//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.11.05 à 11:02:54 PM CET 
//


package fr.gnss.constellation.ouranos.xsd;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:documentation xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:oc10="http://www.ouranos.fr/ouranos/data/v10/coordinate" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/&gt;
 * </pre>
 * 
 * 			
 * 
 * <p>Classe Java pour geodeticCoordinate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="geodeticCoordinate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="altitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "geodeticCoordinate", propOrder = {
    "latitude",
    "longitude",
    "altitude"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
public class GeodeticCoordinate {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    protected double latitude;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    protected double longitude;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    protected double altitude;

    /**
     * Obtient la valeur de la propriété latitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public double getLatitude() {
        return latitude;
    }

    /**
     * Définit la valeur de la propriété latitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Obtient la valeur de la propriété longitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public double getLongitude() {
        return longitude;
    }

    /**
     * Définit la valeur de la propriété longitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Obtient la valeur de la propriété altitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public double getAltitude() {
        return altitude;
    }

    /**
     * Définit la valeur de la propriété altitude.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2016-11-05T11:02:54+01:00", comments = "JAXB RI v2.2.11")
    public void setAltitude(double value) {
        this.altitude = value;
    }

}