package fr.gnss.constellation.ouranos.it.service;

import fr.gnss.constellation.ouranos.toolbox.ClientFtp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/moduleTest/ouranos-services-test.xml"})
public class ITDownloadSp3File {

    private static final String URL_FTP_SP3 = "igs.ensg.ign.fr";
    private static final String URL_FTP_SP3_DIRECTORY = "/pub/igs/products";

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
