package fr.gnss.constellation.ouranos.test.version;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.gnss.constellation.ouranos.version.Version;

public class TestVersion {

	@Test
	public void testComparatorV1InfV2() {
		Version version1 = new Version(1);
		Version version2 = new Version(2);

		int comparator = version1.compareTo(version2);
		assertEquals(-1, comparator);
	}

	@Test
	public void testComparatorV1EqualsV2() {
		Version version1 = new Version(2);
		Version version2 = new Version(2);

		int comparator = version1.compareTo(version2);
		assertEquals(0, comparator);
	}

	@Test
	public void testComparatorV1SupV2() {
		Version version1 = new Version(4);
		Version version2 = new Version(2);

		int comparator = version1.compareTo(version2);
		assertEquals(1, comparator);
	}

	@Test
	public void testEqualsTrue() {
		Version v1 = new Version(1);
		Version v2 = new Version(1);

		assertTrue(v1.equals(v2));
	}

	@Test
	public void testEqualsFalse() {
		Version v1 = new Version(1);
		Version v2 = new Version(2);

		assertFalse(v1.equals(v2));
	}

	@Test
	public void testVersionToStringInfTen() {
		Version version = new Version(1);
		assertEquals("v01", version.toString());
	}

	@Test
	public void testVersionToStringTen() {
		Version version = new Version(10);
		assertEquals("v10", version.toString());
	}

	@Test
	public void testVersionToStringOneHundred() {
		Version version = new Version(100);
		assertEquals("v100", version.toString());
	}

}
