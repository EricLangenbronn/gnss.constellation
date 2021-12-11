package fr.gnss.constellation.ouranos.service.resource.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.gnss.constellation.ouranos.service.resource.ConstanteFlux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class CommonBindingUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonBindingUtil.class);

    // -------------------- Methodes internes --------------------

    public static <T> T bindXmlRequestToObject(final InputStream p_xml, final Class<T> p_class) {
        T bindedObject = null;
        try {
            BindingXMLUtil bindingXmlService = BindingXMLUtil.getInstance();
            Object request = bindingXmlService.mapXml2Object(p_xml, p_class);

            bindedObject = (T) request;
        } catch (Exception e) {
            String l_message = "Erreur lors du mapping du flux XML en objet : " + p_class.getName();
            throw new RuntimeException(l_message, e);
        }

        return bindedObject;
    }

    public static <T> T bindXmlRequestToObject(final byte[] p_requete, final Class<T> p_class) {

        T bindedObject = null;
        InputStream is = new ByteArrayInputStream(p_requete);
        bindedObject = bindXmlRequestToObject(is, p_class);

        return bindedObject;
    }

    public static <T> T bindXmlRequestToObject(final String p_requete, final Class<T> p_class) {

        T bindedObject = bindXmlRequestToObject(p_requete.getBytes(Charset.forName(ConstanteFlux.CHARSET_UTF8)), p_class);

        return bindedObject;
    }

    public static <T> T bindJsonRequestToObject(final String p_requete, final Class<T> p_class) {
        T bindedObject = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            bindedObject = mapper.readValue(p_requete, p_class);
        } catch (IOException e) {
            String l_message = "Erreur lors du mapping du flux JSON en objet : " + p_class.getName();
            throw new RuntimeException(l_message, e);
        }

        return bindedObject;
    }

}
