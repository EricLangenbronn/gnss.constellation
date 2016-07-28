//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.07.27 à 08:14:59 PM CEST 
//


package fr.gnss.constellation.ouranos.xsd;

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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:documentation xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:oc10="http://www.ouranos.fr/ouranos/data/v10/coordinate" xmlns:odt10="http://www.ouranos.fr/ouranos/data/v10/types" xmlns:tns="http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest"/&gt;
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
 *         &lt;element name="groundStation" type="{http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest}coorindate"/&gt;
 *         &lt;element name="startDateOfMeasure" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="endDateOfMeasure" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
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
    "endDateOfMeasure"
})
@XmlRootElement(name = "visibleSateliteRequest")
public class VisibleSateliteRequest {

    @XmlElement(required = true)
    protected Coorindate groundStation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateOfMeasure;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateOfMeasure;

    /**
     * Obtient la valeur de la propriété groundStation.
     * 
     * @return
     *     possible object is
     *     {@link Coorindate }
     *     
     */
    public Coorindate getGroundStation() {
        return groundStation;
    }

    /**
     * Définit la valeur de la propriété groundStation.
     * 
     * @param value
     *     allowed object is
     *     {@link Coorindate }
     *     
     */
    public void setGroundStation(Coorindate value) {
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
    public void setEndDateOfMeasure(XMLGregorianCalendar value) {
        this.endDateOfMeasure = value;
    }

}
