//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.07.27 à 08:14:59 PM CEST 
//


package fr.gnss.constellation.ouranos.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour coorindate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="coorindate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="geodetic" type="{http://www.ouranos.fr/ouranos/data/v10/coordinate}geodeticCoordinate"/&gt;
 *           &lt;element name="cartesian" type="{http://www.ouranos.fr/ouranos/data/v10/coordinate}cartesianCoordinate"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coorindate", propOrder = {
    "geodetic",
    "cartesian"
})
public class Coorindate {

    protected GeodeticCoordinate geodetic;
    protected CartesianCoordinate cartesian;

    /**
     * Obtient la valeur de la propriété geodetic.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticCoordinate }
     *     
     */
    public GeodeticCoordinate getGeodetic() {
        return geodetic;
    }

    /**
     * Définit la valeur de la propriété geodetic.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticCoordinate }
     *     
     */
    public void setGeodetic(GeodeticCoordinate value) {
        this.geodetic = value;
    }

    /**
     * Obtient la valeur de la propriété cartesian.
     * 
     * @return
     *     possible object is
     *     {@link CartesianCoordinate }
     *     
     */
    public CartesianCoordinate getCartesian() {
        return cartesian;
    }

    /**
     * Définit la valeur de la propriété cartesian.
     * 
     * @param value
     *     allowed object is
     *     {@link CartesianCoordinate }
     *     
     */
    public void setCartesian(CartesianCoordinate value) {
        this.cartesian = value;
    }

}
