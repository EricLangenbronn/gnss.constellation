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
 * 
 * 				
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xs:documentation xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://www.ouranos.fr/ouranos/data/v10/coordinate"/&gt;
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
@XmlType(name = "cartesianCoordinate", namespace = "http://www.ouranos.fr/ouranos/data/v10/coordinate", propOrder = {
    "abscisse",
    "ordonnee",
    "hauteur"
})
public class CartesianCoordinate {

    protected double abscisse;
    protected double ordonnee;
    protected double hauteur;

    /**
     * Obtient la valeur de la propriété abscisse.
     * 
     */
    public double getAbscisse() {
        return abscisse;
    }

    /**
     * Définit la valeur de la propriété abscisse.
     * 
     */
    public void setAbscisse(double value) {
        this.abscisse = value;
    }

    /**
     * Obtient la valeur de la propriété ordonnee.
     * 
     */
    public double getOrdonnee() {
        return ordonnee;
    }

    /**
     * Définit la valeur de la propriété ordonnee.
     * 
     */
    public void setOrdonnee(double value) {
        this.ordonnee = value;
    }

    /**
     * Obtient la valeur de la propriété hauteur.
     * 
     */
    public double getHauteur() {
        return hauteur;
    }

    /**
     * Définit la valeur de la propriété hauteur.
     * 
     */
    public void setHauteur(double value) {
        this.hauteur = value;
    }

}
