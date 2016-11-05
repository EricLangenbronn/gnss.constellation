//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.11.05 à 11:02:54 PM CET 
//


package fr.gnss.constellation.ouranos.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.gnss.constellation.ouranos.xsd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VisibleSateliteRequest_QNAME = new QName("http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest", "visibleSateliteRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.gnss.constellation.ouranos.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeodeticCoordinate }
     * 
     */
    public GeodeticCoordinate createGeodeticCoordinate() {
        return new GeodeticCoordinate();
    }

    /**
     * Create an instance of {@link CartesianCoordinate }
     * 
     */
    public CartesianCoordinate createCartesianCoordinate() {
        return new CartesianCoordinate();
    }

    /**
     * Create an instance of {@link VisibleSateliteRequest }
     * 
     */
    public VisibleSateliteRequest createVisibleSateliteRequest() {
        return new VisibleSateliteRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisibleSateliteRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ouranos.fr/ouranos/request/v10/visibleSateliteRequest", name = "visibleSateliteRequest")
    public JAXBElement<VisibleSateliteRequest> createVisibleSateliteRequest(VisibleSateliteRequest value) {
        return new JAXBElement<VisibleSateliteRequest>(_VisibleSateliteRequest_QNAME, VisibleSateliteRequest.class, null, value);
    }

}