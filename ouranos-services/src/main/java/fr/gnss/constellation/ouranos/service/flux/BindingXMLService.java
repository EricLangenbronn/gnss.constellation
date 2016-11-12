package fr.gnss.constellation.ouranos.service.flux;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public class BindingXMLService {

	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BindingXMLService.class);

	private static BindingXMLService INSTANCE = null;

	private BindingXMLService() throws TechnicalException {

	}

	public static BindingXMLService getInstance() throws TechnicalException {
		if (INSTANCE == null) {
			INSTANCE = new BindingXMLService();
		}

		return INSTANCE;
	}

	public <T> T mapXml2Object(final InputStream p_xml, final Class<T> p_class) throws BusinessException {
		T l_Request = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(p_class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object oRequest = jaxbUnmarshaller.unmarshal(p_xml);
			l_Request = (T) oRequest;

			IOUtils.closeQuietly(p_xml);
		} catch (JAXBException l_e) {
			String l_message = "Impossible de transformer le flux en objet de type : " + p_class.getName();
			LOGGER.error(l_message, l_e);
			throw new BusinessException(l_message, l_e);
		}
		return l_Request;
	}

	public void mapObject2Xml(Object p_object, OutputStream p_output) throws BusinessException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(p_object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(p_object, p_output);

			// TODO : pense à fermer le outputStream
		} catch (JAXBException l_e) {
			String l_message = "Impossible de transformer l'ojet en flux de sortie : " + p_object.getClass();
			LOGGER.error(l_message, l_e);
			throw new BusinessException(l_message, l_e);
		}
	}

}
