package fr.gnss.constellation.ouranos.version;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;

public class TestApiVersion {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private static ApiVersionUtil apiVersion = ApiVersionUtil.getInstance();

	@Test
	public void testVersionv01() throws Exception {
		Version version = apiVersion.getVersion("v01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionV01() throws Exception {
		Version version = apiVersion.getVersion("V01");
		assertEquals(1, version.getVersion());
	}

	@Test
	public void testVersionVblabla() throws Exception {
		thrown.expect(BusinessException.class);

		apiVersion.getVersion("Vblabla");
	}

	@Test
	public void testVersionSupersionVersionMax() throws Exception {
		thrown.expect(BusinessException.class);

		apiVersion.getVersion("v10");
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
}
