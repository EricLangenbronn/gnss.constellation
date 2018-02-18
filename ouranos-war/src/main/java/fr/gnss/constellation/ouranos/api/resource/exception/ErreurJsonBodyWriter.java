package fr.gnss.constellation.ouranos.api.resource.exception;

import fr.gnss.constellation.ouranos.xsd.response.error.Error;

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

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class ErreurJsonBodyWriter implements MessageBodyWriter<Error> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == Error.class;
	}

	@Override
	public long getSize(Error t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Error error, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException, WebApplicationException {
		String l_resultatJson = null;

		ObjectMapper mapper = new ObjectMapper();

		l_resultatJson = mapper.writeValueAsString(error);

		Writer writer = new PrintWriter(entityStream);
		writer.write(l_resultatJson.toCharArray());
		writer.flush();
		writer.close();
	}

}
