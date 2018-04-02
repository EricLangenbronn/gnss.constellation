package fr.gnss.constellation.ouranos.service.resource.util;

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

public class BindingXMLUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(BindingXMLUtil.class);

	private static BindingXMLUtil INSTANCE = null;

	private BindingXMLUtil() {

	}

	public static BindingXMLUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BindingXMLUtil();
		}

		return INSTANCE;
	}

	public <T> Object mapXml2Object(final InputStream p_xml, final Class<T> p_class) throws BusinessException {
		Object l_Request = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(p_class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			l_Request = jaxbUnmarshaller.unmarshal(p_xml);

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

			IOUtils.closeQuietly(p_output);
		} catch (JAXBException l_e) {
			String l_message = "Impossible de transformer l'ojet en flux de sortie : " + p_object.getClass();
			LOGGER.error(l_message, l_e);
			throw new BusinessException(l_message, l_e);
		}
	}

}
