package fr.gnss.constellation.ouranos.test.version;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.version.IVersionService;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-services-test.xml" })
@TestPropertySource("classpath:ouranos-rest-api.properties")
public class TestVersionService {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private IVersionService versionService;

	@Test
	public void testVersionv01() throws Exception {
		Version version = versionService.getVersion(VersionType.PREFRIX, "v01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionV01() throws Exception {
		Version version = versionService.getVersion(VersionType.PREFRIX, "V01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionVblabla() throws Exception {
		thrown.expect(BusinessException.class);

		versionService.getVersion(VersionType.PREFRIX, "Vblabla");
	}

	@Test
	public void testVersionSupersionVersionMax() throws Exception {
		thrown.expect(BusinessException.class);

		versionService.getVersion(VersionType.PREFRIX, "v10");
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistBetween() throws BusinessException {
		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(2));
		versions.add(new Version(5));

		Version findVersion = versionService.checkIfVersionOrPreviousIsContains(new Version(3), versions);
		assertNotNull(findVersion);
		assertEquals(2, findVersion.getVersion());
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistAtAll() throws BusinessException {

		thrown.expect(BusinessException.class);

		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(3));
		versions.add(new Version(5));

		versionService.checkIfVersionOrPreviousIsContains(new Version(2), versions);
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistMax() throws BusinessException {

		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(3));
		versions.add(new Version(5));

		Version findVersion = versionService.checkIfVersionOrPreviousIsContains(new Version(10), versions);
		assertNotNull(findVersion);
		assertEquals(5, findVersion.getVersion());

	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionEmptyList() throws BusinessException {

		thrown.expect(BusinessException.class);

		List<Version> versions = new ArrayList<Version>();

		versionService.checkIfVersionOrPreviousIsContains(new Version(5), versions);
	}

}
