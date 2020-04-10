package fr.gnss.constellation.ouranos.test.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.service.version.IVersionService;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "/moduleTest/ouranos-services-test.xml" })
@TestPropertySource("classpath:ouranos-rest-api.properties")
public class TestVersionService {

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
	public void testVersionVblabla() {

		assertThrows(BusinessException.class, () -> {
			versionService.getVersion(VersionType.PREFRIX, "Vblabla");
		});
	}

	@Test
	public void testVersionSupersionVersionMax() {

		assertThrows(BusinessException.class, () -> {
			versionService.getVersion(VersionType.PREFRIX, "v10");
		});
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

		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(3));
		versions.add(new Version(5));

		assertThrows(BusinessException.class, () -> {
			versionService.checkIfVersionOrPreviousIsContains(new Version(2), versions);
		});
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

		List<Version> versions = new ArrayList<Version>();

		assertThrows(BusinessException.class, () -> {
			versionService.checkIfVersionOrPreviousIsContains(new Version(5), versions);
		});
	}

}
