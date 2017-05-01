//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.01 à 04:19:02 PM CEST 
//


package fr.gnss.constellation.ouranos.xsd.request;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:documentation xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:oc10="http://www.ouranos.fr/ouranos/data/v10/coordinate" xmlns:tns="http://www.ouranos.fr/ouranos/data/v10/coordinate" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/&gt;
 * </pre>
 * 
 * 			
 * 
 * <p>Classe Java pour cartesianCoordinate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="cartesianCoordinate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="abscisse" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ordonnee" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="hauteur" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cartesianCoordinate", propOrder = {
    "abscisse",
    "ordonnee",
    "hauteur"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
public class CartesianCoordinate {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    protected double abscisse;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    protected double ordonnee;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    protected double hauteur;

    /**
     * Obtient la valeur de la propriété abscisse.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public double getAbscisse() {
        return abscisse;
    }

    /**
     * Définit la valeur de la propriété abscisse.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public void setAbscisse(double value) {
        this.abscisse = value;
    }

    /**
     * Obtient la valeur de la propriété ordonnee.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public double getOrdonnee() {
        return ordonnee;
    }

    /**
     * Définit la valeur de la propriété ordonnee.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public void setOrdonnee(double value) {
        this.ordonnee = value;
    }

    /**
     * Obtient la valeur de la propriété hauteur.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public double getHauteur() {
        return hauteur;
    }

    /**
     * Définit la valeur de la propriété hauteur.
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2017-05-01T04:19:02+02:00", comments = "JAXB RI v2.2.11")
    public void setHauteur(double value) {
        this.hauteur = value;
    }

}
