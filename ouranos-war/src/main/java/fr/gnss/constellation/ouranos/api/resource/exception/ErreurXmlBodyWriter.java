package fr.gnss.constellation.ouranos.api.resource.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.resource.utils.BindingXMLUtils;
import fr.gnss.constellation.ouranos.xsd.response.error.Error;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class ErreurXmlBodyWriter implements MessageBodyWriter<Error> {

	/** le logger */
	private final static Logger LOGGER = LoggerFactory.getLogger(ErreurXmlBodyWriter.class);

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == Error.class;
	}

	@Override
	public long getSize(Error t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Error error, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		String l_resultatXml = "";

		try {
			BindingXMLUtils bindingXmlService = BindingXMLUtils.getInstance();
			ByteArrayOutputStream l_outs = new ByteArrayOutputStream();
			bindingXmlService.mapObject2Xml(error, l_outs);
			l_resultatXml = new String(l_outs.toString("UTF-8"));
			IOUtils.closeQuietly(l_outs);
		} catch (BusinessException l_e) {
			String l_message = "Erreur lors de la génération du xml d'erreur code=[" + error.getCode() + "], message=["
					+ error.getMessage() + "], status=[" + error.getStatus() + "]";
			LOGGER.error(l_message, l_e);
		}

		Writer writer = new PrintWriter(entityStream);
		writer.write(l_resultatXml.toCharArray());
		writer.flush();
		writer.close();

	}

}
