package fr.gnss.constellation.ouranos.test.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.version.ApiVersionUtil;
import fr.gnss.constellation.ouranos.version.Version;

public class TestApiVersion {

	private static ApiVersionUtil apiVersion = ApiVersionUtil.getInstance();

	@Test
	public void testVersionv01() throws BusinessException {
		Version version = apiVersion.getVersion("v01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionV01() throws BusinessException {
		Version version = apiVersion.getVersion("V01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionVblabla() {

		assertThrows(BusinessException.class, () -> {
			apiVersion.getVersion("Vblabla");
		});
	}

	@Test
	public void testVersionSupersionVersionMax() {

		assertThrows(BusinessException.class, () -> {
			apiVersion.getVersion("v10");
		});
	}

	@Test
	public void testVersionPreviousVersion10() {
		Version version = new Version(10);
		Version previous = apiVersion.getPreviousVersion(version);
		assertEquals(9, previous.getVersion());
	}

	@Test
	public void testVersionPreviousVersion0() {
		Version version = new Version(0);
		Version previous = apiVersion.getPreviousVersion(version);
		assertEquals(0, previous.getVersion());
	}

	@Test
	public void testVersionPreviousVersionNeg1() {
		Version version = new Version(-1);
		Version previous = apiVersion.getPreviousVersion(version);
		assertEquals(0, previous.getVersion());
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistBetween() throws BusinessException {
		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(2));
		versions.add(new Version(5));

		Version findVersion = apiVersion.checkIfVersionOrPreviousIsContains(new Version(3), versions);
		assertNotNull(findVersion);
		assertEquals(2, findVersion.getVersion());
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistAtAll() {

		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(3));
		versions.add(new Version(5));

		assertThrows(BusinessException.class, () -> {
			apiVersion.checkIfVersionOrPreviousIsContains(new Version(2), versions);
		});
	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionNotExistMax() throws BusinessException {

		List<Version> versions = new ArrayList<Version>();
		versions.add(new Version(3));
		versions.add(new Version(5));

		Version findVersion = apiVersion.checkIfVersionOrPreviousIsContains(new Version(10), versions);
		assertNotNull(findVersion);
		assertEquals(5, findVersion.getVersion());

	}

	@Test
	public void testCheckIfVersionOrPreviousIsContainsVersionEmptyList() {

		List<Version> versions = new ArrayList<Version>();

		assertThrows(BusinessException.class, () -> {
			apiVersion.checkIfVersionOrPreviousIsContains(new Version(5), versions);
		});
	}
}
