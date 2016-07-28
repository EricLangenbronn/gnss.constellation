package fr.gnss.constellation.ouranos.services.impl;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.BindingXmlService;

public class BindingXMLServiceImpl implements BindingXmlService {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(BindingXMLServiceImpl.class);

	private static final String PACKAGE_XSD = "fr.gnss.constellation.ouranos.xsd";

	private BindingXMLServiceImpl INSTANCE = null;

	private JAXBContext jaxbContext = null;

	private BindingXMLServiceImpl() throws TechnicalException {
		try {
			jaxbContext = JAXBContext.newInstance(PACKAGE_XSD);
		} catch (JAXBException l_e) {
			String l_message = "Impossible de trouver le package : " + PACKAGE_XSD;
			LOGGER.info(l_message, l_e);
			throw new TechnicalException(l_message, l_e);
		}
	}

	public BindingXMLServiceImpl getInstance() throws TechnicalException {
		if (INSTANCE == null) {
			INSTANCE = new BindingXMLServiceImpl();
		}

		return INSTANCE;
	}

	@Override
	public <T> T mapXml2Object(final InputStream p_xml, final Class<T> p_class) throws BusinessException {
		T l_objet = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(p_class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setValidating(true);
			l_objet = (T) jaxbUnmarshaller.unmarshal(p_xml);
		} catch (JAXBException l_e) {
			String l_message = "Impossible de transoformer le flux en objet de type : " + p_class.getName();
			LOGGER.error(l_message, l_e);
			throw new BusinessException(l_message, l_e);
		}
		return l_objet;
	}

	@Override
	public void mapObject2Xml(Object p_object, OutputStream p_output) throws BusinessException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(p_object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(p_object, p_output);
		} catch (JAXBException l_e) {
			String l_message = "Impossible de transoformer l'ojet en flux de sortie : " + p_object.getClass();
			LOGGER.error(l_message, l_e);
			throw new BusinessException(l_message, l_e);
		}
	}

}
