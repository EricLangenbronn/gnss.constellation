package fr.gnss.constellation.ouranos.api.resource.request.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.resource.request.RequestResourceService;
import fr.gnss.constellation.ouranos.xsd.request.VisibleSateliteRequest;

@Provider
@Consumes(MediaType.APPLICATION_XML)
public class VisibleSatXmlReader implements MessageBodyReader<VisibleSateliteRequest> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// c'est pas top, mais pour le moment c'est bon
		return true;
	}

	@Override
	public VisibleSateliteRequest readFrom(Class<VisibleSateliteRequest> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {

		VisibleSateliteRequest request = null;

		try {
			RequestResourceService.bindXmlRequestToObject(entityStream, VisibleSateliteRequest.class);
		} catch (BusinessException e) {
			throw new IOException(e.getMessage(), e);
		}

		return request;
	}

}
