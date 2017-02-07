package fr.gnss.constellation.ouranos.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import fr.gnss.constellation.ouranos.service.template.TemplateUtils;
import fr.gnss.constellation.ouranos.version.Version;

public class TestTemplateUtils {

	@Test
	public void testGetAvailableVersionForResource() throws Exception {
		TemplateUtils templateUtils = new TemplateUtils();
		List<Version> availabelVersion = templateUtils.getAvailableVersionForResource("satellite-visible");

		assertNotNull(availabelVersion);
		// il va chercher dans deux répertoire différent, je sais pas trop pk
		assertEquals(2, availabelVersion.size());
	}

	@Test
	public void testResolveTemplateVersionInTermsOf() throws Exception {
		TemplateUtils templateUtils = new TemplateUtils();
		String templatePath = templateUtils.resolveTemplateVersionInTermsOf("satellite-visible", new Version(1));

		assertNotNull(templatePath);
		assertEquals("templates\\satellite-visible\\v01.vm", templatePath);

	}

}
