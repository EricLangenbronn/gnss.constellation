package fr.gnss.constellation.ouranos.services;

import java.io.InputStream;
import java.io.OutputStream;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public interface IBindingXmlService {

	/**
	 * Map the XML to the object with type class
	 * 
	 * @param p_xml
	 *            XML
	 * @param p_class
	 *            Instances of the class
	 * @return An object of type class
	 * @throws BusinessException
	 */
	<T> T mapXml2Object(final InputStream p_xml, final Class<T> p_class) throws BusinessException;

	/**
	 * Write the object in the stream
	 * 
	 * @param p_object
	 *            object to write
	 * @param p_output
	 *            stream which get the object
	 * @throws BusinessException
	 */
	public void mapObject2Xml(Object p_object, OutputStream p_output) throws BusinessException;
}
