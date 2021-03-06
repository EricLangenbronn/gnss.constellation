package fr.gnss.constellation.ouranos.it.service;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gnss.constellation.ouranos.toolbox.ClientFtp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/moduleTest/ouranos-services-test.xml" })
public class ITDownloadSp3File {

	private static final String URL_FTP_SP3 = "ftp.igs.org";
	private static final String URL_FTP_SP3_DIRECTORY = "/pub/product";

	@Test
	public void testConnexion() throws Exception {
		InetAddress address = InetAddress.getByName(URL_FTP_SP3);

		ClientFtp client = new ClientFtp(address.getHostAddress());

		boolean isOpen = client.openConnection();
		assertTrue(isOpen);

		client.logoutAndCloseConnection();
	}

	@Test
	public void testConnexionDownloadFile() throws Exception {
		InetAddress address = InetAddress.getByName(URL_FTP_SP3);

		ClientFtp client = new ClientFtp(address.getHostAddress());

		boolean isOpen = client.openConnection();
		assertTrue(isOpen);

		Path accessOutputFile = Paths.get(".", "src/it/resources/Sp3File/igs12821.sp3.Z");
		client.downloadBinaryFile(URL_FTP_SP3_DIRECTORY + "/1282/igs12821.sp3.Z", accessOutputFile);

		client.logoutAndCloseConnection();
		assertTrue(accessOutputFile.toFile().exists());
		assertTrue(accessOutputFile.toFile().length() != 0);
	}

	@Test
	public void testConnexionCheckTimeOut() throws Exception {
		InetAddress address = InetAddress.getByName(URL_FTP_SP3);

		ClientFtp client = new ClientFtp(address.getHostAddress());
		client.setTimeOut(10); // 10 milliseconde

		boolean isOpen = client.openConnection();
		assertFalse(isOpen);
	}

}
