package fr.gnss.constellation.ouranos.test.version;

import fr.gnss.constellation.ouranos.service.version.IVersionService;
import fr.gnss.constellation.ouranos.service.version.exception.VersionException;
import fr.gnss.constellation.ouranos.service.version.factory.VersionType;
import fr.gnss.constellation.ouranos.version.Version;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/moduleTest/ouranos-services-test.xml"})
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

        assertThrows(VersionException.class, () -> {
            versionService.getVersion(VersionType.PREFRIX, "Vblabla");
        });
    }

    @Test
    public void testVersionSupersionVersionMax() {

        assertThrows(VersionException.class, () -> {
            versionService.getVersion(VersionType.PREFRIX, "v10");
        });
    }

    @Test
    public void testCheckIfVersionOrPreviousIsContainsVersionNotExistBetween() {
        List<Version> versions = new ArrayList<Version>();
        versions.add(new Version(2));
        versions.add(new Version(5));

        Version findVersion = versionService.checkIfVersionOrPreviousIsContains(new Version(3), versions);
        assertNotNull(findVersion);
        assertEquals(2, findVersion.getVersion());
    }

    @Test
    public void testCheckIfVersionOrPreviousIsContainsVersionNotExistAtAll() {

        List<Version> versions = new ArrayList<Version>();
        versions.add(new Version(3));
        versions.add(new Version(5));

        assertThrows(VersionException.class, () -> {
            versionService.checkIfVersionOrPreviousIsContains(new Version(2), versions);
        });
    }

    @Test
    public void testCheckIfVersionOrPreviousIsContainsVersionNotExistMax() {

        List<Version> versions = new ArrayList<Version>();
        versions.add(new Version(3));
        versions.add(new Version(5));

        Version findVersion = versionService.checkIfVersionOrPreviousIsContains(new Version(10), versions);
        assertNotNull(findVersion);
        assertEquals(5, findVersion.getVersion());

    }

    @Test
    public void testCheckIfVersionOrPreviousIsContainsVersionEmptyList() {

        List<Version> versions = new ArrayList<Version>();

        assertThrows(VersionException.class, () -> {
            versionService.checkIfVersionOrPreviousIsContains(new Version(5), versions);
        });
    }

}
