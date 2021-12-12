package fr.gnss.constellation.ouranos.it.service;

import fr.gnss.constellation.ouranos.config.OuranosConfiguration;
import fr.gnss.constellation.ouranos.service.logmessage.LogMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OuranosConfiguration.class})
@TestPropertySource("classpath:log-message.properties")
public class ITLogMessageService {

    @Autowired
    private LogMessageService logMessageService;

    @Test
    public void testGFetDefautErrorMessage() {
        String messageErreur = logMessageService.getDefautErrorMessage("SFD.GLSF.TE.DNE");

        assertNotNull(messageErreur);

        String fullMesageErreur = MessageFormat.format(messageErreur, "/tmp/sp3");

        assertNotNull(fullMesageErreur);
        assertEquals("Impossible d'accéder au répertoire contenantles fichiers Sp3. [pathSp3Dir= /tmp/sp3]", fullMesageErreur);
    }

}
