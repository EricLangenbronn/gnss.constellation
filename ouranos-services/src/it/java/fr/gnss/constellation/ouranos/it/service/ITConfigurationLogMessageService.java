package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.MessageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.service.IConfigurationLogMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-services-test.xml" })
@TestPropertySource("classpath:log-message.properties")
public class ITConfigurationLogMessageService {

	@Autowired
	private IConfigurationLogMessageService configurationLogMessageService;

	@Test
	public void testGFetDefautErrorMessage() {
		String messageErreur = configurationLogMessageService.getDefautErrorMessage("SFD.GLSF.TE.DNE");

		assertNotNull(messageErreur);

		String fullMesageErreur = MessageFormat.format(messageErreur, "/tmp/sp3");

		assertNotNull(fullMesageErreur);
		assertEquals("Impossible d'accéder au répertoire contenantles fichiers Sp3. [pathSp3Dir= /tmp/sp3]", fullMesageErreur);
	}

}
