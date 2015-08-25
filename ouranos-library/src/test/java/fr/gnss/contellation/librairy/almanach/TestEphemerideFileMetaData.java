package fr.gnss.contellation.librairy.almanach;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileNameFormat;

public class TestEphemerideFileMetaData {

	// The final (igs), rapid (igr),
	@Test
	public void TestFinalFileNameOk() throws Exception {
		String fileName = "igs12810.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFinalFileNameKoEphemeride() throws Exception {
		String fileName = "igw12810.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFinalFileNameKoGpsWeek() throws Exception {
		String fileName = "igsa2810.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFinalFileNameKoDay() throws Exception {
		String fileName = "igs1281x.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFinalFileNameKoOrbitType() throws Exception {
		String fileName = "igs12810.see.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFinalFileNameKoCompressType() throws Exception {
		String fileName = "igs12810.see.";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	// ultra-rapid (igu)
	@Test
	public void TestRapidFileNameOk() throws Exception {
		String fileName = "igu12810_00.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}

	@Test(expected = BusinessException.class)
	public void TestFileNameKoEphemeride() throws Exception {
		String fileName = "igw12810_hr.sp3.Z";
		Sp3FileNameFormat efmd = new Sp3FileNameFormat(fileName);
		assertNotNull(efmd);
	}
}
