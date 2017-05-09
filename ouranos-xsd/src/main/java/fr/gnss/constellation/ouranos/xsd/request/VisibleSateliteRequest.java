//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.09 à 07:06:23 PM CEST 
//


package fr.gnss.constellation.ouranos.xsd.request;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 					
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:documentation xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:oc10="http://www.ouranos.fr/ouranos/data/v10/coordinate" xmlns:tns="http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/&gt;
 * </pre>
 * 
 * 				
 * 
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="groundStation" type="{http://www.ouranos.fr/ouranos/data/v10/coordinate}geodeticCoordinate"/&gt;
 *         &lt;element name="startDateOfMeasure" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="endDateOfMeasure" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="elevationMask" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "groundStation",
    "startDateOfMeasure",
    "endDateOfMeasure",
    "elevationMask"
})
@XmlRootElement(name = "visibleSateliteRequest", namespace = "http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
public class VisibleSateliteRequest {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    protected GeodeticCoordinate groundStation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    protected XMLGregorianCalendar startDateOfMeasure;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    protected XMLGregorianCalendar endDateOfMeasure;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    protected double elevationMask;

    /**
     * Obtient la valeur de la propriété groundStation.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticCoordinate }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public GeodeticCoordinate getGroundStation() {
        return groundStation;
    }

    /**
     * Définit la valeur de la propriété groundStation.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticCoordinate }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public void setGroundStation(GeodeticCoordinate value) {
        this.groundStation = value;
    }

    /**
     * Obtient la valeur de la propriété startDateOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public XMLGregorianCalendar getStartDateOfMeasure() {
        return startDateOfMeasure;
    }

    /**
     * Définit la valeur de la propriété startDateOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public void setStartDateOfMeasure(XMLGregorianCalendar value) {
        this.startDateOfMeasure = value;
    }

    /**
     * Obtient la valeur de la propriété endDateOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public XMLGregorianCalendar getEndDateOfMeasure() {
        return endDateOfMeasure;
    }

    /**
     * Définit la valeur de la propriété endDateOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public void setEndDateOfMeasure(XMLGregorianCalendar value) {
        this.endDateOfMeasure = value;
    }

    /**
     * Obtient la valeur de la propriété elevationMask.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public double getElevationMask() {
        return elevationMask;
    }

    /**
     * Définit la valeur de la propriété elevationMask.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-09T07:06:23+02:00", comments = "JAXB RI v2.2.11")
    public void setElevationMask(double value) {
        this.elevationMask = value;
    }

}
